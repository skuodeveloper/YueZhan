package com.example.skuo.yuezhan.Entity.Repair;

/**
 * Created by Administrator on 16-12-9.
 */
public class RepairType {
    public int ID;
    public String Name;

    @Override
    public String toString() {
        // 为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
        // TODO Auto-generated method stub
        return Name;
    }

    public int GetID() {
        return ID;
    }

    public String GetValue() {
        return Name;
    }
}
