package com.xiniu.myapplication.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 创建者：wyz
 * 创建时间：2020-06-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
 public class ToastUtils {


    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
