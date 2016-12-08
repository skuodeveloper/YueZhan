package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class GroupList {
    private int TotalCount;
    private ArrayList<Group> GroupExs = new ArrayList<Group>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Group> getGroupExs() {
        return GroupExs;
    }
}
