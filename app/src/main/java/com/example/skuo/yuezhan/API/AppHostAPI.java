package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.AppHot.AppHotList;
import com.example.skuo.yuezhan.Entity.BaseEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface AppHostAPI {
    /**
     * 获取热点列表
     *
     * @param EstateID
     * @return
     */
    @GET("AppHotList/GetAppHotListNew_Json/")
    Observable<BaseEntity<AppHotList>> httpsGetAppHotListRx(@Query("EstateID") int EstateID);
}
