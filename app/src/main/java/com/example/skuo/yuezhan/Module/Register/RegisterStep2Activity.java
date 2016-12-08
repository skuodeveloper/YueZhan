package com.example.skuo.yuezhan.Module.Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.skuo.yuezhan.API.RegisterAPI;
import com.example.skuo.yuezhan.Base.BaseActivity;
import com.example.skuo.yuezhan.Base.SysApplication;
import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Register.Building;
import com.example.skuo.yuezhan.Entity.Register.BuildingList;
import com.example.skuo.yuezhan.Entity.Register.Cell;
import com.example.skuo.yuezhan.Entity.Register.CellList;
import com.example.skuo.yuezhan.Entity.Register.House;
import com.example.skuo.yuezhan.Entity.Register.HouseList;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.NetUtils;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterStep2Activity extends BaseActivity {
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.spBuilding)
    Spinner spBuilding;

    @BindView(R.id.spCell)
    Spinner spCell;

    @BindView(R.id.spHouse)
    Spinner spHouse;

    private static ArrayAdapter<Building> buildingAdapter;
    private static ArrayAdapter<Cell> cellAdapter;
    private static ArrayAdapter<House> houseAdapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RegisterStep2Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_step2;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SysApplication.getInstance().addActivity(this);

        initBuilding(1);
    }

    /**
     * 初始化楼幢
     */
    private void initBuilding(int EstateID) {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetBuildingExRx(EstateID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<BuildingList>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        NetUtils.checkHttpException(mContext, e, layout);
                    }

                    @Override
                    public void onNext(BaseEntity<BuildingList> buildingList) {
                        Logger.d();

                        if (buildingList.getData() != null && buildingList.getCode() == 0) {
                            ArrayList<Building> buildings = buildingList.getData().getBuildingExs();

                            //适配器
                            buildingAdapter = new ArrayAdapter<Building>(mContext, R.layout.spinner_item_layout, buildings);
                            //设置样式
                            buildingAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spBuilding.setAdapter(buildingAdapter);
                            //默认选中第一项
                            spBuilding.setSelection(0, true);  //spinner会重新layout

                            if (buildingList.getData().getTotalCount() > 0)
                                initCell(buildingList.getData().getBuildingExs().get(0).GetID());
                        }
                    }
                });
    }

    /**
     * 初始化城市
     */
    private void initCell(int building) {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetCellRx(building)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<CellList>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        NetUtils.checkHttpException(mContext, e, layout);
                    }

                    @Override
                    public void onNext(BaseEntity<CellList> cellList) {
                        Logger.d();

                        if (cellList.getData() != null && cellList.getCode() == 0) {
                            ArrayList<Cell> cells = cellList.getData().getCellExs();

                            //适配器
                            cellAdapter = new ArrayAdapter<Cell>(mContext, R.layout.spinner_item_layout, cells);
                            //设置样式
                            cellAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spCell.setAdapter(cellAdapter);
                            //默认选中第一项
                            spCell.setSelection(0, true);  //spinner会重新layout

                            if (cellList.getData().getTotalCount() > 0)
                                initHouse(cellList.getData().getCellExs().get(0).GetID());
                        }
                    }
                });
    }

    /**
     * 初始化小区
     */
    private void initHouse(int cellid) {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetHouseRx(cellid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<HouseList>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        NetUtils.checkHttpException(mContext, e, layout);
                    }

                    @Override
                    public void onNext(BaseEntity<HouseList> houseList) {
                        Logger.d();

                        if (houseList.getData() != null && houseList.getCode() == 0) {
                            ArrayList<House> houses = houseList.getData().getHouseExs();

                            //适配器
                            houseAdapter = new ArrayAdapter<House>(mContext, R.layout.spinner_item_layout, houses);
                            //设置样式
                            houseAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spHouse.setAdapter(houseAdapter);
                            //默认选中第一项
                            spHouse.setSelection(0, true);  //spinner会重新layout
                        }
                    }
                });
    }

    @Override
    protected void initResAndListener() {
        spBuilding.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Logger.d();

                        initCell(((Building) spBuilding.getSelectedItem()).GetID());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Logger.d();
                    }
                });

        spCell.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Logger.d();

                        initHouse(((Cell) spCell.getSelectedItem()).GetID());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Logger.d();
                    }
                });
    }
}