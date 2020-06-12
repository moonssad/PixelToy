package com.xiniu.myapplication.PixImage;

import android.util.Log;

/**
 * 创建者：wyz
 * 创建时间：2020-06-11
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ColorItemPool {
    public ColorItem[][] items;
    public ColorItemPool(){}

    public void init (int widthNum,int heightNum){
            items =new ColorItem[widthNum][heightNum];
            Log.e("init:", ""+widthNum+"|"+heightNum);
    }
    public void setColorItems(int X,int Y,ColorItem colorItem){
        items[X][Y] = colorItem;
        Log.e("setColorItems:", "X:"+X+"Y:"+Y);
    }

    public ColorItem[][] getColorItems(){
        return items;
    }



}
