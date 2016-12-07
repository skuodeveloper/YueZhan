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
import com.example.skuo.yuezhan.Entity.BaseEntity;
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

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.spProvince)
    Spinner spProvince;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                            ArrayAdapter<Province> provinceAdapter;

                            ArrayList<Province> provinces = provinceList.getData().getProvinceExs();

                            //适配器
                            provinceAdapter = new ArrayAdapter<Province>(mContext, R.layout.spinner_item_layout, provinces);
                            //设置样式
                            provinceAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spProvince.setAdapter(provinceAdapter);
                            //默认选中第一项
                            spProvince.setSelection(0,true);  //spinner会重新layout
                        }
                    }
                });
    }

    /**
     * 初始化城市
     */
    private void initProvince(int provinceid) {
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
                            ArrayAdapter<Province> provinceAdapter;

                            ArrayList<Province> provinces = provinceList.getData().getProvinceExs();

                            //适配器
                            provinceAdapter = new ArrayAdapter<Province>(mContext, R.layout.spinner_item_layout, provinces);
                            //设置样式
                            provinceAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                            //加载适配器
                            spProvince.setAdapter(provinceAdapter);
                            //默认选中第一项
                            spProvince.setSelection(0,true);  //spinner会重新layout
                        }
                    }
                });
    }

    @Override
    protected void initResAndListener() {
        spProvince.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        Logger.d();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Logger.d();
                    }
                });

    }
}
