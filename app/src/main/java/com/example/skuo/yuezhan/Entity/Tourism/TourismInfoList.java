package com.example.skuo.yuezhan.Entity.Tourism;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class TourismInfoList {
    private int TotalCount;
    private ArrayList<TourismInfo> TourismInfos = new ArrayList<TourismInfo>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<TourismInfo> getTourismInfos() {
        return TourismInfos;
    }
}
