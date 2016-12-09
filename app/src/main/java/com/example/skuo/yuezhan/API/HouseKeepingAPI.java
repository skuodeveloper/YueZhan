package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Keeping.AppointmentDetail;
import com.example.skuo.yuezhan.Entity.Keeping.HousekeepSetList;
import com.example.skuo.yuezhan.Entity.Keeping.KeepInfoList;
import com.example.skuo.yuezhan.Entity.Keeping.ServiceOrderList;

import java.util.Date;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-8.
 */
public interface HouseKeepingAPI {
    /**
     * 获取预约详细信息
     *
     * @param ID
     * @return
     */
    @GET("HouseKeeping/GetAppointmentDetail_V2_Json/")
    Observable<BaseEntity<AppointmentDetail>> httpsGetAppointmentDetailRx(@Query("ID") int ID);

    /**
     * 获取服务项目信息列表
     *
     * @param PropertyID
     * @param EstateID
     * @param UserID
     * @return
     */
    @GET("HouseKeeping/GetHousekeepingInfo_Json/")
    Observable<BaseEntity<KeepInfoList>> httpsGetHousekeepingInfoRx(@Query("PropertyID") int PropertyID,
                                                                    @Query("EstateID") int EstateID,
                                                                    @Query("UserID") int UserID);

    /**
     * 获取家政服务列表
     * @param page
     * @param pageSize
     * @param UserAccountID
     * @return
     */
    @GET("HouseKeeping/GetAppointmentList_Json/")
    Observable<BaseEntity<ServiceOrderList>> httpsGetAppointmentListRx(@Query("page") int page,
                                                                       @Query("pageSize") int pageSize,
                                                                       @Query("UserAccountID") int UserAccountID);
    /**
     * 获取服务时长列表
     * @param ServiceID
     * @return
     */
    @GET("HouseKeeping/GetHousekeepingSet_Json/")
    Observable<BaseEntity<HousekeepSetList>> httpsGetHousekeepingSetRx(@Query("ServiceID") int ServiceID);

    /**
     * 提交家政服务预约
     *
     * @param AppointmentTime
     * @param UserAccountID
     * @param ServiceID
     * @param PropertyID
     * @param EstateID
     * @param Contacts
     * @param Tel
     * @param Address
     * @param Price
     * @param Duration
     * @param Remark
     * @return
     */
    @FormUrlEncoded
    @POST("Register/SubmitAppointmnet_Json/")
    Observable<BaseEntity> httpsSubmitAppointmnetRx(@Field("AppointmentTime") Date AppointmentTime,
                                                    @Field("UserAccountID") int UserAccountID,
                                                    @Field("ServiceID") int ServiceID,
                                                    @Field("PropertyID") int PropertyID,
                                                    @Field("EstateID") int EstateID,
                                                    @Field("Contacts") String Contacts,
                                                    @Field("Tel") String Tel,
                                                    @Field("Address") String Address,
                                                    @Field("Price") double Price,
                                                    @Field("Duration") int Duration,
                                                    @Field("Remark") String Remark);

    /**
     * 取消家政服务预约
     * @param ID
     * @return
     */
    @FormUrlEncoded
    @POST("Register/CancelAppointmnet_Json/")
    Observable<BaseEntity> httpsCancelAppointmnetRx(@Field("ID") int ID);

    /**
     * 确认完成及提交评论
     * @param ID
     * @param Level
     * @param Content
     * @return
     */
    @FormUrlEncoded
    @POST("Register/ConfirmAppointmnet_Json/")
    Observable<BaseEntity> httpsCancelAppointmnetRx(@Field("ID") int ID,
                                                    @Field("Level") int Level,
                                                    @Field("Content") String Content);
}
