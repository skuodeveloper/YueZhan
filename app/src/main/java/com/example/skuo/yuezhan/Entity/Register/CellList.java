package com.example.skuo.yuezhan.Entity.Register;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-12-6.
 */
public class CellList {
    private int TotalCount;
    private ArrayList<Cell> CellExs = new ArrayList<Cell>();

    public int getTotalCount() {
        return TotalCount;
    }

    public ArrayList<Cell> getCellExs() {
        return CellExs;
    }
}
