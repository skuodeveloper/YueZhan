package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class HouseList {
    private int TotalCount;
    private ArrayList<House> HouseExs = new ArrayList<House>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<House> getHouseExs() {
        return HouseExs;
    }
}
