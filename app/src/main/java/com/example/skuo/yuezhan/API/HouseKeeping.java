package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Keeping.KeepInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-8.
 */
public interface HouseKeeping {
    /**
     * 获取家政服务列表
     *
     * @param page
     * @param pageSize
     * @param EstateID
     * @param UserID
     * @return
     */
    @GET("HouseKeeping/GetHouseKeeping/")
    Observable<BaseEntity<KeepInfo>> httpsGetHouseKeepingRx(@Query("page") int page,
                                                            @Query("pageSize") int pageSize,
                                                            @Query("EstateID") int EstateID,
                                                            @Query("UserID") int UserID);


}
