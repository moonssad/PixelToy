package com.xiniu.myapplication;

import android.app.Application;
import android.content.Context;


/**
 * 创建者：wyz
 * 创建时间：2020-06-02
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        this.context = this;
        super.onCreate();

    }

    public static Context getContext() {
        return context;
    }

}
