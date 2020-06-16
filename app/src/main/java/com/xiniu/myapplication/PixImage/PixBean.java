package com.xiniu.myapplication.PixImage;

/**
 * 创建者：wyz
 * 创建时间：2020-06-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class PixBean {
    int color; //color =-1；表示删除

    public PixBean() {
    }

    public PixBean(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
