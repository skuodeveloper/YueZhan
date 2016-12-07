package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Register.CityList;
import com.example.skuo.yuezhan.Entity.Register.EstateList;
import com.example.skuo.yuezhan.Entity.Register.ProvinceList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-6.
 */
public interface RegisterAPI {
    @GET("Register/GetProvinceList_json/")
    Observable<BaseEntity<ProvinceList>> httpsGetProvinceRx();

    @GET("Register/GetCityListByProvinceID_json/")
    Observable<BaseEntity<CityList>> httpsGetCityRx(@Query("ProvinceID") int ProvinceID);

    @GET("Register/GetEstateListByCityID_json/")
    Observable<BaseEntity<EstateList>> httpsGetEstateRx(@Query("CityID") int CityID);
}
