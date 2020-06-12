package com.xiniu.myapplication.PixImage;

/**
 * 创建者：wyz
 * 创建时间：2020-06-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class PixUtils {

    private static PixUtils instance = new PixUtils();
    private PixBean pixBean;

    public static PixUtils getInstance() {
        return instance;
    }

    public PixBean getPixBean(){
        if (pixBean==null){
            pixBean = new PixBean();
        }
        return pixBean;
    }
}
