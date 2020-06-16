package com.xiniu.myapplication.Utils;

import java.io.File;

/**
 * 创建者：wyz
 * 创建时间：2020-06-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public  class MyFileUtils {

    public static boolean isFile(String path) {
        File file  =new File(path);
        return file.exists();
    }
}
