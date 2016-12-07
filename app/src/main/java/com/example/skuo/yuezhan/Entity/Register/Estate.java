package com.example.skuo.yuezhan.Entity.Register;

/**
 * Created by Administrator on 16-12-6.
 */
public class Estate {
    private int EstateID;
    private String EstateName;

    @Override
    public String toString() {
        // 为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
        // TODO Auto-generated method stub
        return EstateName;
    }

    public int GetID() {
        return EstateID;
    }

    public String GetValue() {
        return EstateName;
    }
}
