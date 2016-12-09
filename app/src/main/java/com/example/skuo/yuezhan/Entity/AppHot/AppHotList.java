package com.example.skuo.yuezhan.Entity.AppHot;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class AppHotList {
    private int TotalCount;
    private ArrayList<AppHot> AppHotListExs = new ArrayList<AppHot>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<AppHot> getHousekeepingInfoExs() {
        return AppHotListExs;
    }
}
