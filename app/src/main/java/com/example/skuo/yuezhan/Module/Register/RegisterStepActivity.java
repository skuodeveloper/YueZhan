package com.example.skuo.yuezhan.Module.Register;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.example.skuo.yuezhan.API.RegisterAPI;
import com.example.skuo.yuezhan.Base.BaseActivity;
import com.example.skuo.yuezhan.Base.YueZhanApplication;
import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Register.CityAndEstate;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.Location.Service.LocationService;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.NetUtils;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class RegisterStepActivity extends BaseActivity {
    @BindView(R.id.layout)
    RelativeLayout layout;

    private final int SDK_PERMISSION_REQUEST = 100;

    private StickyListHeadersListView list;
    private CityAndEstate mEstateListDatas;

    private LocationService locationService;
    private Context context;
    private boolean flag = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_step;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        // after andrioid m,must request Permiision on runtime
        getPersimmions();

        list = (StickyListHeadersListView) findViewById(R.id.list);

        initDatas();
    }

    /**
     * 获取城市小区列表
     */
    private void initDatas() {
        RetrofitClient.createService(RegisterAPI.class)
                .httpsGetAllEstatesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<CityAndEstate>>() {
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
                    public void onNext(BaseEntity<CityAndEstate> estateAndCityList) {
                        Logger.d();
                        mEstateListDatas.setEstateInfos(estateAndCityList.getData().getEstateInfos());


                    }
                });
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        locationService = ((YueZhanApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        // 定位SDK
        locationService.start();
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    /*****
     * to you project
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (flag && null != location && location.getLocType() != BDLocation.TypeServerError) {
                flag = false;

                String cityname = location.getCity();
                Toast.makeText(context, cityname, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
