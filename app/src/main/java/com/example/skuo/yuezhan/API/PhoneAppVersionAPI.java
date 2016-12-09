package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.PhoneAppVersion.AppVersion;
import com.example.skuo.yuezhan.Entity.PhoneAppVersion.AppVersionInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface PhoneAppVersionAPI {
    /**
     * 获取APP最新版本号，带是否需强制更新信息
     * @return
     */
    @GET("PhoneAppVersion/GetPhoneAppVersionCodeUpdate_Json/")
    Observable<BaseEntity<AppVersion>> httpsPhoneAppVersionRx();


    /**
     * 获取APP最新版本信息
     * @param AppVersion
     * @return
     */
    @GET("PhoneAppVersion/GetPhoneAppVersionInfo_Json/")
    Observable<BaseEntity<AppVersionInfo>> httpsGetPhoneAppVersionInfoRx(@Query("AppVersion") String AppVersion);
}
