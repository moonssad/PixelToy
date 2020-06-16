package com.xiniu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.xiniu.myapplication.PixImage.CheckView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView previous, next, more;
    CheckView checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous = (ImageView) findViewById(R.id.button_previous);
        next = (ImageView) findViewById(R.id.button_next);
        more = (ImageView) findViewById(R.id.more);
        checkView = (CheckView) findViewById(R.id.check_view);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_previous:
                checkView.previous();
                break;
            case R.id.button_next:
                checkView.next();
                break;
            case R.id.more:
                addPopWindow();
                break;
            default:
                break;
        }
    }


    //myPop.showAsDropDown(cvMain, (cvMain.getWidth() - myPop.getContentView().getMeasuredWidth()) / 2,
    //
    //-(cvMain.getHeight() + myPop.getContentView().getMeasuredHeight()));
    public void addPopWindow() {
        final PopupWindow popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_save_share, null);
        ImageView saveImage = (ImageView) view.findViewById(R.id.save);
        ImageView shareImage = (ImageView) view.findViewById(R.id.share);
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.saveImage(false);
                popupWindow.dismiss();
            }
        });
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.saveImage(true);
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.anim_slide_in);
        popupWindow.showAsDropDown(more,(more.getWidth()-popupWindow.getContentView().getMeasuredWidth()),
                -(more.getHeight()+popupWindow.getContentView().getMeasuredHeight()));
    }
}
