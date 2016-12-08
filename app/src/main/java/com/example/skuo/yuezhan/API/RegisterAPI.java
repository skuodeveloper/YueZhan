package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Register.BuildingList;
import com.example.skuo.yuezhan.Entity.Register.CellList;
import com.example.skuo.yuezhan.Entity.Register.CityList;
import com.example.skuo.yuezhan.Entity.Register.EstateList;
import com.example.skuo.yuezhan.Entity.Register.HouseList;
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

    @GET("Register/GetBuildingListByEstateID_json/")
    Observable<BaseEntity<BuildingList>> httpsGetBuildingRx(@Query("EstateID") int EstateID);

    @GET("Register/GetCellListByBuildingID_json/")
    Observable<BaseEntity<CellList>> httpsGetCellRx(@Query("BuildingID") int BuildingID);

    @GET("Register/GetHouseListByCellID_json/")
    Observable<BaseEntity<HouseList>> httpsGetHouseRx(@Query("CellID") int CellID);
}
