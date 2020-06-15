package com.xiniu.myapplication.PixImage;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建者：wyz
 * 创建时间：2020-06-12
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class util {

    //保存bitmap图片

    public util(){}

    public  void saveImageToGallery( Bitmap bmp) {
        Log.e("saveImageToGallery:", "bmp|");
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "jx");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
