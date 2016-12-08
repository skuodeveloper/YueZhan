package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Register.BuildingList;
import com.example.skuo.yuezhan.Entity.Register.CellList;
import com.example.skuo.yuezhan.Entity.Register.CityList;
import com.example.skuo.yuezhan.Entity.Register.EstateList;
import com.example.skuo.yuezhan.Entity.Register.GroupList;
import com.example.skuo.yuezhan.Entity.Register.HouseList;
import com.example.skuo.yuezhan.Entity.Register.ProvinceList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-6.
 */
public interface RegisterAPI {
    /**
     * 获取省份列表
     *
     * @return
     */
    @GET("Register/GetProvinceList_json/")
    Observable<BaseEntity<ProvinceList>> httpsGetProvinceRx();

    /**
     * 获取城市列表
     *
     * @param ProvinceID
     * @return
     */
    @GET("Register/GetCityListByProvinceID_json/")
    Observable<BaseEntity<CityList>> httpsGetCityRx(@Query("ProvinceID") int ProvinceID);

    /**
     * 获取小区列表
     *
     * @param CityID
     * @return
     */
    @GET("Register/GetEstateListByCityID_json/")
    Observable<BaseEntity<EstateList>> httpsGetEstateRx(@Query("CityID") int CityID);

    /**
     * 根据小区ID获取楼幢列表(返回“组团名称/楼幢名称”)
     *
     * @param EstateID
     * @return
     */
    @GET("Register/GetBuildingListByEstateID_json/")
    Observable<BaseEntity<BuildingList>> httpsGetBuildingRx(@Query("EstateID") int EstateID);

    /**
     * 获取单元列表
     *
     * @param BuildingID
     * @return
     */
    @GET("Register/GetCellListByBuildingID_json/")
    Observable<BaseEntity<CellList>> httpsGetCellRx(@Query("BuildingID") int BuildingID);

    /**
     * 获取房号列表
     *
     * @param CellID
     * @return
     */
    @GET("Register/GetHouseListByCellID_json/")
    Observable<BaseEntity<HouseList>> httpsGetHouseRx(@Query("CellID") int CellID);

    /**
     * 注册(老版本接口)
     *
     * @param UserName
     * @param NickName
     * @param Password
     * @param Phone
     * @param EstateID
     * @param BuildingID
     * @param CellID
     * @param HouseID
     * @param Relations
     * @return
     */
    @FormUrlEncoded
    @POST("Register/RegisterUser_json/")
    Observable<BaseEntity> httpsRegisterUserRx(@Field("UserName") String UserName,
                                               @Field("NickName") String NickName,
                                               @Field("Password") String Password,
                                               @Field("Phone") String Phone,
                                               @Field("EstateID") int EstateID,
                                               @Field("BuildingID") int BuildingID,
                                               @Field("CellID") int CellID,
                                               @Field("HouseID") int HouseID,
                                               @Field("Relations") String Relations);

    /**
     * 注册(新版本)
     *
     * @param UserName
     * @param NickName
     * @param Password
     * @param Phone
     * @param EstateID
     * @param BuildingID
     * @param CellID
     * @param HouseID
     * @param Relations
     * @return
     */
    @FormUrlEncoded
    @POST("Register/RegisterUser_V2_json/")
    Observable<BaseEntity> httpsRegisterUser_V2Rx(@Field("UserName") String UserName,
                                                  @Field("NickName") String NickName,
                                                  @Field("Password") String Password,
                                                  @Field("Phone") String Phone,
                                                  @Field("EstateID") int EstateID,
                                                  @Field("BuildingID") int BuildingID,
                                                  @Field("CellID") int CellID,
                                                  @Field("HouseID") int HouseID,
                                                  @Field("Relations") int Relations,
                                                  @Field("Sex") Integer Sex,
                                                  @Field("Phone") Integer InvitePhone);


    @GET("Register/IsPhoneRegisted_json/")
    Observable<BaseEntity> httpsIsPhoneRegistedRx(@Query("Phone") String Phone);

    /**
     * 悦管家用户注册
     *
     * @param Phone
     * @param Password
     * @return
     */
    @FormUrlEncoded
    @POST("Register/RegisterButlerUser_Json/")
    Observable<BaseEntity> httpsRegisterButlerUserRx(@Field("Phone") String Phone,
                                                     @Field("Password") String Password);

    /**
     * 根据小区ID获取组团列表
     *
     * @param EstateID
     * @return
     */
    @GET("Register/GetGroupListByEstateID_json/")
    Observable<BaseEntity<GroupList>> httpsGetGroupListByEstateIDRx(@Query("EstateID") int EstateID);

    /**
     * 根据组团ID获取楼幢列表
     *
     * @param GroupID
     * @return
     */
    @GET("Register/GetBuildingListByGroupID_json/")
    Observable<BaseEntity<GroupList>> httpsGetBuildingListByGroupIDRx(@Query("GroupID") int GroupID);

    /**
     * 根据小区ID获取楼幢列表(返回“楼幢名称”)
     *
     * @param EstateID
     * @return
     */
    @GET("Register/GetBuildingListByEstateIDEx_json/")
    Observable<BaseEntity<BuildingList>> httpsGetBuildingExRx(@Query("EstateID") int EstateID);

    /**
     * 用户注销
     *
     * @param UserID
     * @return
     */
    @FormUrlEncoded
    @POST("Register/CancelUser_Json/")
    Observable<BaseEntity> httpsCancelUserRx(@Field("UserID") int UserID);
}
