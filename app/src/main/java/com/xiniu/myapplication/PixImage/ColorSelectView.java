package com.xiniu.myapplication.PixImage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.xiniu.myapplication.PixImage.ColorPick.MyCustomDialog;
import com.xiniu.myapplication.R;


/**
 * 创建者：wyz
 * 创建时间：2020-06-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ColorSelectView extends View {
    private int[] colors;//colors的前两个是黑色和白色，不可修改，其他的后面设计为可修改模式。
    private int num;
    private int height;
    private int width;
    private int itemWidth;
    private int widthDimission = 10;
    private Paint mPaint;
    private Paint circlePaint;
    private int chooseNum;
    private long previous_time;
    private long time_dimission;
    private Context context;
    private Handler handler;

    public ColorSelectView(Context context) {
        this(context, null);
    }

    public ColorSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        handler = new Handler();
        this.context = context;
        colors = context.getResources().getIntArray(R.array.pix_color);
        num = colors.length;
        PixUtils.getInstance().getPixBean().setColor(colors[0]);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widths = MeasureSpec.getSize(widthMeasureSpec);
        int heights = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widths, heights);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        itemWidth = (width - widthDimission * (num - 1)) / (num);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(0, getHeight());
        for (int i = 0; i < num; i++) {
            mPaint.setColor(colors[i]);

            canvas.drawRect((itemWidth + widthDimission) * i, -height, (itemWidth + widthDimission) * i + itemWidth, 0, mPaint);
            if (i == chooseNum) {
                mPaint.setAlpha(255);
                canvas.drawCircle((itemWidth + widthDimission) * i + itemWidth / 2, -height / 2, 10, circlePaint);
            }
        }
        super.onDraw(canvas);
    }

    //短按设置成选择颜色，长按设置成修改颜色。黑白两种颜色不要修改， 其他的颜色可以采用颜色修改器急性修改。
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                previous_time = System.currentTimeMillis();
                int x = (int) event.getX();
                int num = x / (itemWidth + widthDimission);
                chooseNum = num;
                PixUtils.getInstance().getPixBean().setColor(colors[num]);
                postInvalidate();
                handler.postDelayed(myRunnable, 500);
                return true;
            //修改方法在down里面监听延时操作。
            case MotionEvent.ACTION_UP:
                time_dimission = System.currentTimeMillis() - previous_time;
                if (time_dimission < 500) {
                    handler.removeCallbacks(myRunnable);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            MyCustomDialog.Builder builder = new MyCustomDialog.Builder(context);
            builder.setOnSeekColorListener(new MyCustomDialog.Builder.onColorChangedListener() {
                @Override
                public void chooseColor(Dialog dialog, int color) {
                    PixUtils.getInstance().getPixBean().setColor(color);
                    dialog.dismiss();
                    colors[chooseNum] = color;
                    postInvalidate();
                    Log.e("chooseColor:", "color" + color);
                }
            });
            builder.create().show();
            Log.e("onTouchEvent:", "弹出颜色选择器");
        }
    };

}
