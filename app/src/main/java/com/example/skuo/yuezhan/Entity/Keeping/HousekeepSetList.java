package com.example.skuo.yuezhan.Entity.Keeping;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class HousekeepSetList {
    private int TotalCount;
    private ArrayList<HousekeepingSet> HousekeepingSetExs = new ArrayList<HousekeepingSet>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<HousekeepingSet> getHousekeepingSetExs() {
        return HousekeepingSetExs;
    }
}
