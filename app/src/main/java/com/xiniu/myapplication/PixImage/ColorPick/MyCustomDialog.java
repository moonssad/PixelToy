package com.xiniu.myapplication.PixImage.ColorPick;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.xiniu.myapplication.R;


/**
 * 创建者：wyz
 * 创建时间：2020-06-11
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MyCustomDialog extends Dialog {
    public MyCustomDialog(@NonNull Context context) {
        super(context);
    }

    public MyCustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder implements SeekBar.OnSeekBarChangeListener {
        public String title;
        private Context context;
        private onColorChangedListener listener;
        private SeekBar mSbColor, mSbSat, mSbValue;
        public NestedScrollView mNestedScrollView;
        private ColorPicker mColorPicker;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOnSeekColorListener(onColorChangedListener listener) {
            this.listener = listener;
            return this;
        }

        public MyCustomDialog create() {
            final MyCustomDialog dialog = new MyCustomDialog(context, R.style.Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.activity_pick, null);
            dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            mSbColor = (SeekBar) view.findViewById(R.id.sb_color);
            mSbSat = (SeekBar) view.findViewById(R.id.sb_sat);
            mSbValue = (SeekBar) view.findViewById(R.id.sb_value);
            mColorPicker = (ColorPicker) view.findViewById(R.id.cp);
            mNestedScrollView = (NestedScrollView) view.findViewById(R.id.nest_scroll);
            mSbColor.setOnSeekBarChangeListener(this);
            mSbSat.setOnSeekBarChangeListener(this);
            mSbValue.setOnSeekBarChangeListener(this);

            mColorPicker.setOnSeekColorListener(new OnSeekColorListener() {
                @Override
                public void onSeekColorListener(int color) {
                    mNestedScrollView.setBackgroundColor(color);
                }

                @Override
                public void onColorChoosed(int color) {
                    mNestedScrollView.setBackgroundColor(color);
                    if (listener != null) {
                        listener.chooseColor(dialog, color);
                    }
                }
            });
            return dialog;
        }


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.sb_color:
                    mColorPicker.setHSVColor(progress);
                    break;
                case R.id.sb_sat:
                    mColorPicker.setHSVSaturation((float) progress / 100);
                    break;
                case R.id.sb_value:
                    mColorPicker.setHSVValue((float) progress / 100);
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public interface onColorChangedListener {
            void chooseColor(Dialog dialog, int color);
        }
    }

}
