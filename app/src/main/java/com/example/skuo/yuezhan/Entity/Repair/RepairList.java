package com.example.skuo.yuezhan.Entity.Repair;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class RepairList {
    private int TotalCount;
    private ArrayList<Repair> Repairs = new ArrayList<Repair>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Repair> getRepairs() {
        return Repairs;
    }
}
