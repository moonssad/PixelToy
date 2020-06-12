package com.xiniu.myapplication.PixImage;

/**
 * 创建者：wyz
 * 创建时间：2020-06-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ColorItem {

    private int width;//左上角X
    private int height;//左上角Y
    private int color;

    public ColorItem() {
    }

    public ColorItem(int width, int height, int color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
