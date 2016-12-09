package com.example.skuo.yuezhan.Entity.Complaint;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class ComplaintImageList {
    private int TotalCount;
    private ArrayList<ComplaintImage> ComplaintImages = new ArrayList<ComplaintImage>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<ComplaintImage> getRepairTypes() {
        return ComplaintImages;
    }
}
