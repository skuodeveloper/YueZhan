package com.example.skuo.yuezhan.Entity.Complaint;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class ComplaintList {
    private int TotalCount;
    private ArrayList<Complaint> Complaints = new ArrayList<Complaint>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Complaint> getRepairTypes() {
        return Complaints;
    }
}
