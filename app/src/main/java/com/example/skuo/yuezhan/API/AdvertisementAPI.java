package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.Advertisement.AdvertisementList;
import com.example.skuo.yuezhan.Entity.BaseEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface AdvertisementAPI {

    /**
     * 获取广告列表
     * @param EstateID
     * @return
     */
    @GET("Advertisement/GetAdvertisementList_V3_Json/")
    Observable<BaseEntity<AdvertisementList>> httpsGetAdvertisementListRx(@Query("EstateID") int EstateID);
}
