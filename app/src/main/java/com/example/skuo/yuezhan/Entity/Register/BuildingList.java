package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class BuildingList {
    private int TotalCount;
    private ArrayList<Building> BuildingExs = new ArrayList<Building>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Building> getBuildingExs() {
        return BuildingExs;
    }
}
