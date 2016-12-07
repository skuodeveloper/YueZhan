package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class EstateList {
    private int TotalCount;
    private ArrayList<Estate> EstateExs  = new ArrayList<Estate>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Estate> getEstateExs() {
        return EstateExs ;
    }
}
