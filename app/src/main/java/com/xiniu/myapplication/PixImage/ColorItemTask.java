package com.xiniu.myapplication.PixImage;

import com.xiniu.myapplication.PixImage.ColorItem;

import java.util.Arrays;

/**
 * 创建者：wyz
 * 创建时间：2020-06-16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ColorItemTask {

    private ColorItem[][] ColorItemTask;
    /**
     * 栈的容量
     */
    private int capacity;
    /**
     * 栈中元素数量
     */
    private int count;
    /**
     * 扩容栈的容量常量
     * 栈的初始容量
     */
    private static final int GROW_FACTOR = 2;
    private static final int defaultCount = 5;

    public ColorItemTask() {
        this.ColorItemTask = new ColorItem[defaultCount][];
        this.capacity = defaultCount;
        count = 0;
    }


    public ColorItemTask(int initalCapacity) {
        if (initalCapacity < 1) {
            throw new IllegalArgumentException("给定容量太小");
        }
        this.ColorItemTask = new ColorItem[initalCapacity][];
        this.capacity = initalCapacity;
        this.count = 0;
    }


    /**
     * 扩充数组容量，每次乘以2
     */
    public void extendCapacity() {
        int newCapacity = capacity * GROW_FACTOR;
        //扩容之后按照扩充后的容量对原数组进行数组拷贝
        ColorItemTask = Arrays.copyOf(ColorItemTask, newCapacity);
        capacity = newCapacity;
    }


    /**
     * 入栈的方法，也就是存栈
     *
     * @param value
     */
    public void push(ColorItem[] value) {
        if (value.length > 0) {
            //首先判断索引count是否等于容量capacity,如果等于就进行扩容
            if (count == capacity) {
                extendCapacity();
            }
            //否则进行存栈
            ColorItemTask[count++] = value;
        }
    }

    /**
     * 返回栈顶元素，并出栈
     *
     * @return
     */
    public ColorItem[] pop() {
        //为什么出栈，原因就在count--每次调用pop方法之后count就要减一，索引就会指向下一个元素，故而是出栈
        count--;
        //当count值等于-1时，说明已经完全取出数组里面的栈，抛出异常，此栈是空的
        if (count == -1) {
            count = 0;
            throw new IllegalArgumentException("此栈是空的");
        }
        //否则直接返回栈
        return ColorItemTask[count];
    }

    /**
     * 返回栈顶元素，不出栈
     *
     * @return
     */
    public ColorItem[] peek() {
        //为什么不出栈，因为每次只是返回栈顶元素并没有对count值也就相当于是索引做什么，所以是不出栈
        if (count == 0) {
            throw new IllegalArgumentException("此栈是空的");
        } else {
            return ColorItemTask[count - 1];
        }
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 返回栈中元素个数
     *
     * @return
     */
    public int size() {
        return capacity;
    }

    //直接把索引位置置于0处
    public void clear() {
        count = 0;
    }


}
