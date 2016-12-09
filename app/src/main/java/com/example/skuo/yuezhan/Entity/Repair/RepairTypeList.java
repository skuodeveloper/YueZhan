package com.example.skuo.yuezhan.Entity.Repair;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class RepairTypeList {
    private int TotalCount;
    private ArrayList<RepairType> RepairTypes = new ArrayList<RepairType>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<RepairType> getRepairTypes() {
        return RepairTypes;
    }
}
