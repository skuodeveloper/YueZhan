package com.example.skuo.yuezhan.Entity.Repair;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class RepairImageList {
    private int TotalCount;
    private ArrayList<RepairImage> RepairImages = new ArrayList<RepairImage>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<RepairImage> getRepairImages() {
        return RepairImages;
    }
}
