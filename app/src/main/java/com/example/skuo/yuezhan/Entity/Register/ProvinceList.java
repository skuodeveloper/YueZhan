package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class ProvinceList {
    private int TotalCount;
    private ArrayList<Province> ProvinceExs = new ArrayList<Province>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Province> getProvinceExs() {
        return ProvinceExs;
    }
}
