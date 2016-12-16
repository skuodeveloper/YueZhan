package com.example.skuo.yuezhan.Base;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.StrictMode;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.example.skuo.yuezhan.Location.Service.LocationService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by LiCola on  2015/12/02  13:25
 */
public class YueZhanApplication extends Application {
    private static final String TAG = "YueZhanApplication";
    private static YueZhanApplication instance;
    private RefWatcher refWatcher;

    public LocationService locationService;
    private Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //refWatcher = LeakCanary.install(this);//初始化 内存检测工具
        Fresco.initialize(this);//初始化Fresco图片加载框架

        CrashReport.initCrashReport(getApplicationContext(), "900037004", false);

        //检查内存泄漏
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());

        //chrome 调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        /***
         * 初始化百度地图定位sdk
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }


    /**
     * 获得内存监视器 监视任何对象
     * 使用 refWatcher.watch(object);
     *
     * @return 全局的refWatcher
     */
    public static RefWatcher getRefwatcher(Context context) {
        YueZhanApplication huaBanApplication = (YueZhanApplication) context.getApplicationContext();
        return huaBanApplication.refWatcher;
    }
}