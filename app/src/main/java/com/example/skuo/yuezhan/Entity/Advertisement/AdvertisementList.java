package com.example.skuo.yuezhan.Entity.Advertisement;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class AdvertisementList {
    private int TotalCount;
    private ArrayList<Advertisement> AdvertisementExs = new ArrayList<Advertisement>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Advertisement> getAdvertisementExs() {
        return AdvertisementExs;
    }
}
