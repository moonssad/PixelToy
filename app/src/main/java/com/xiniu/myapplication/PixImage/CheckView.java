package com.xiniu.myapplication.PixImage;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiniu.myapplication.MyApplication;
import com.xiniu.myapplication.R;
import com.xiniu.myapplication.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 创建者：wyz
 * 创建时间：2020-06-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CheckView extends SurfaceView implements SurfaceHolder.Callback {

    private Paint mPaint;
    private Paint itemPaint;
    private SurfaceHolder mHolder;
    private int width;
    private int height;
    private int radius = 50;
    private int widthNum, heightNum;
    public ColorItemPool colorItem;
    public ColorItemTask itemTasks;
    public ColorItemTask popItemTasks;
    private List<ColorItem> items;
    private int orientation = 3;
    private Bitmap bitmap;


    public CheckView(Context context) {
        this(context, null);

    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        widthNum = width / radius;
        heightNum = height / radius;
        colorItem.init(widthNum + 1, heightNum + 1);
        Log.e("onSizeChanged:", "111" + widthNum + "|" + heightNum);
        if (orientation != 3) {
            Draw();
            Log.e("onSizeChanged:", "111" + widthNum + "|" + heightNum);
        }

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.ezled_black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        itemPaint = new Paint();
        itemPaint.setAntiAlias(true);
        itemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        colorItem = new ColorItemPool();
        itemTasks = new ColorItemTask();
        popItemTasks = new ColorItemTask();
        items = new ArrayList<>();
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Draw();
        Log.e("surfaceCreated:", "onsurfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("surfaceCreated:", "surfaceDestroyed");
    }


    private void Draw() {
        Canvas canvas = mHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < widthNum; i++) {
            for (int i1 = 0; i1 < heightNum; i1++) {
                canvas.drawRect(i * radius, i1 * radius, i * radius + radius, i1 * radius + radius, mPaint);
            }
        }
        for (ColorItem[] items : colorItem.getColorItems()) {
            for (ColorItem item : items) {
                if (item != null) {
                    itemPaint.setColor(item.getColor());
                    canvas.drawRect(item.getWidth(), item.getHeight(), item.getWidth() + radius, item.getHeight() + radius, itemPaint);
                }
            }
        }
        getHolder().unlockCanvasAndPost(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("onTouchEvent:", "onTouchEvent");
        ColorItem color = new ColorItem();
        int xPosition = (int) (event.getX() / radius);
        int yPosition = (int) (event.getY() / radius);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                Log.e("onTouchEvent:", "down+move");
                if (xPosition >= 0 && xPosition < widthNum && yPosition >= 0 && yPosition < heightNum) {
                    color.setWidth(xPosition * radius);
                    color.setHeight(yPosition * radius);
                    color.setColor(PixUtils.getInstance().getPixBean().getColor());
                    colorItem.setColorItems(xPosition, yPosition, color);
                    items.add(color);
                    popItemTasks.clear();
                    Draw();
                }
                return true;
            case MotionEvent.ACTION_UP:
                Log.e("onTouchEvent:", "up");
                ColorItem[] itemss = items.toArray(new ColorItem[items.size()]);
                itemTasks.push(itemss);
                items.clear();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        orientation = newConfig.orientation;
        super.onConfigurationChanged(newConfig);
    }

    //保存图片那就再画一遍.
    public void saveImage(boolean ShareFileUtils) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.GRAY);
        for (int i = 0; i < widthNum; i++) {
            for (int i1 = 0; i1 < heightNum; i1++) {
                canvas.drawRect(i * radius, i1 * radius, i * radius + radius, i1 * radius + radius, mPaint);
            }
        }
        for (ColorItem[] items : colorItem.getColorItems()) {
            for (ColorItem item : items) {
                if (item != null) {
                    itemPaint.setColor(item.getColor());
                    canvas.drawRect(item.getWidth(), item.getHeight(), item.getWidth() + radius, item.getHeight() + radius, itemPaint);
                }
            }
        }
        util utils = new util();
        utils.saveImageToGallery(bitmap,ShareFileUtils);
    }

    public void previous() {
        try {
            ColorItem[] items = itemTasks.pop();
            if (items.length > 0) {
                for (ColorItem item : items) {
                    Log.e("previous:", "item");
                    colorItem.removeColorItems(item.getWidth() / radius, item.getHeight() / radius);
                }
                popItemTasks.push(items);
                Draw();
            }
        } catch (Exception e) {
            ToastUtils.showToast(MyApplication.getContext(), MyApplication.getContext().getString(R.string.not_item_task));
        }
    }

    public void next() {
        try {
            ColorItem[] items = popItemTasks.pop();
            if (items.length > 0) {
                for (ColorItem item : items) {
                    colorItem.setColorItems(item.getWidth() / radius, item.getHeight() / radius,item);
                }
                itemTasks.push(items);
                Draw();
            }
        } catch (Exception e) {
            ToastUtils.showToast(MyApplication.getContext(), MyApplication.getContext().getString(R.string.not_item_task));
        }

    }
}
