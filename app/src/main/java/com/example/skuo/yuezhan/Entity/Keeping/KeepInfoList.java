package com.example.skuo.yuezhan.Entity.Keeping;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class KeepInfoList {
    private int TotalCount;
    private ArrayList<KeepInfo> HousekeepingInfoExs = new ArrayList<KeepInfo>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<KeepInfo> getHousekeepingInfoExs() {
        return HousekeepingInfoExs;
    }
}
