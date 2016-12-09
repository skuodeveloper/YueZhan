package com.example.skuo.yuezhan.Entity.Complaint;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class ComplaintTypeList {
    private int TotalCount;
    private ArrayList<ComplaintType> ComplaintTypes = new ArrayList<ComplaintType>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<ComplaintType> getRepairTypes() {
        return ComplaintTypes;
    }
}
