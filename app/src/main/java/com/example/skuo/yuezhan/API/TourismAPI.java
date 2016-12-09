package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Tourism.TourismInfoList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface TourismAPI {
    /**
     * 获取旅游信息列表
     *
     * @param page
     * @param EstateID
     * @param pageSize
     * @return
     */
    @GET("Tourism/GetTourismInfoList_Json/")
    Observable<BaseEntity<TourismInfoList>> httpsGetTourismInfoListRx(@Query("page") int page,
                                                                      @Query("EstateID") int EstateID,
                                                                      @Query("pageSize") int pageSize);
}
