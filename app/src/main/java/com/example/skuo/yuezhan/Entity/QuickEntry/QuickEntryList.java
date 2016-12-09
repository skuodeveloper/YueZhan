package com.example.skuo.yuezhan.Entity.QuickEntry;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class QuickEntryList {
    private int TotalCount;
    private ArrayList<QuickEntry> QuickEntryInfo = new ArrayList<QuickEntry>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<QuickEntry> getRepairTypes() {
        return QuickEntryInfo;
    }
}
