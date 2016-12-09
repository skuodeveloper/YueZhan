package com.example.skuo.yuezhan.Entity.Keeping;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class ServiceOrderList {
    private int TotalCount;
    private ArrayList<ServiceOrder> NeighborhoodServiceOrderExs = new ArrayList<ServiceOrder>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<ServiceOrder> getServiceOrderExs() {
        return NeighborhoodServiceOrderExs;
    }
}
