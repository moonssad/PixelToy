package com.xiniu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
                checkView.saveImage();
                break;
            default:
                break;
        }
    }

}
