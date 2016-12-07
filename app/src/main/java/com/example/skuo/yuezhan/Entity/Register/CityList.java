package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class CityList {
    private int TotalCount;
    private ArrayList<City> CityExs = new ArrayList<City>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<City> getCityExs() {
        return CityExs;
    }
}
