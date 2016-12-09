package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.QuickEntry.QuickEntryList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface QuickEntryAPI {
    /**
     * 可变模块设置
     * @param EstateID
     * @return
     */
    @GET("QuickEntry/GetQuickEntryImages_V3_Json/")
    Observable<BaseEntity<QuickEntryList>> httpsGetQuickEntryImagesRx(@Query("EstateID") int EstateID);
}
