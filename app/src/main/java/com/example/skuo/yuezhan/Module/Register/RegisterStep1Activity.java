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
import com.example.skuo.yuezhan.Entity.Register.City;
import com.example.skuo.yuezhan.Entity.Register.CityList;
import com.example.skuo.yuezhan.Entity.Register.Estate;
import com.example.skuo.yuezhan.Entity.Register.EstateList;
import com.example.skuo.yuezhan.Entity.Register.Province;
import com.example.skuo.yuezhan.Entity.Register.ProvinceList;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.NetUtils;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterStep1Activity extends BaseActivity {
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.spProvince)
    Spinner spProvince;

    @BindView(R.id.spCity)
    Spinner spCity;

    @BindView(R.id.spEstate)
    Spinner spEstate;

    private static ArrayAdapter<Province> provinceAdapter;
    private static ArrayAdapter<City> cityAdapter;
    private static ArrayAdapter<Estate> estateAdapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RegisterStep1Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_step1;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SysApplication.getInstance().addActivity(this);

        initProvince();
    }

    /**
     * 初始化省份
     */
    private void initProvince() {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetProvinceRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<ProvinceList>>() {
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
                    public void onNext(BaseEntity<ProvinceList> provinceList) {
                        Logger.d();

                        if (provinceList.getData() != null && provinceList.getCode() == 0) {
                            ArrayList<Province> provinces = provinceList.getData().getProvinceExs();

                            //适配器
                            provinceAdapter = new ArrayAdapter<Province>(mContext, R.layout.spinner_item_layout, provinces);
                            //设置样式
                            provinceAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spProvince.setAdapter(provinceAdapter);
                            //默认选中第一项
                            spProvince.setSelection(0, true);  //spinner会重新layout

                            if (provinceList.getData().getTotalCount() > 0)
                                initCity(provinceList.getData().getProvinceExs().get(0).GetID());
                        }
                    }
                });
    }

    /**
     * 初始化城市
     */
    private void initCity(int provinceid) {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetCityRx(provinceid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<CityList>>() {
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
                    public void onNext(BaseEntity<CityList> cityList) {
                        Logger.d();

                        if (cityList.getData() != null && cityList.getCode() == 0) {
                            ArrayList<City> cities = cityList.getData().getCityExs();

                            //适配器
                            cityAdapter = new ArrayAdapter<City>(mContext, R.layout.spinner_item_layout, cities);
                            //设置样式
                            cityAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spCity.setAdapter(cityAdapter);
                            //默认选中第一项
                            spCity.setSelection(0, true);  //spinner会重新layout

                            if (cityList.getData().getTotalCount() > 0)
                                initEstate(cityList.getData().getCityExs().get(0).GetID());
                        }
                    }
                });
    }

    /**
     * 初始化小区
     */
    private void initEstate(int cityid) {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetEstateRx(cityid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<EstateList>>() {
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
                    public void onNext(BaseEntity<EstateList> estateList) {
                        Logger.d();

                        if (estateList.getData() != null && estateList.getCode() == 0) {
                            ArrayList<Estate> estates = estateList.getData().getEstateExs();

                            //适配器
                            estateAdapter = new ArrayAdapter<Estate>(mContext, R.layout.spinner_item_layout, estates);
                            //设置样式
                            estateAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spEstate.setAdapter(estateAdapter);
                            //默认选中第一项
                            spEstate.setSelection(0, true);  //spinner会重新layout
                        }
                    }
                });
    }

    @Override
    protected void initResAndListener() {
        spProvince.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Logger.d();

                        initCity(((Province) spProvince.getSelectedItem()).GetID());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Logger.d();
                    }
                });

        spCity.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Logger.d();

                        initEstate(((City) spCity.getSelectedItem()).GetID());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Logger.d();
                    }
                });
    }
}
