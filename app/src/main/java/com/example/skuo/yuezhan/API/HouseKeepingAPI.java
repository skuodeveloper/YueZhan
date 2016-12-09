package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Keeping.KeepingAppointment;
import com.example.skuo.yuezhan.Entity.Keeping.KeepInfo;
import com.example.skuo.yuezhan.Entity.Keeping.KeepingStaff;

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

    /**
     * 获取家政服务人员列表
     *
     * @param page
     * @param pageSize
     * @param EstateID
     * @param AppointmentTime
     * @return
     */
    @GET("HouseKeeping/GetKeepingStaff/")
    Observable<BaseEntity<KeepingStaff>> httpsGetKeepingStaffRx(@Query("page") int page,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("EstateID") int EstateID,
                                                                @Query("AppointmentTime") Date AppointmentTime);

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

    @FormUrlEncoded
    @POST("Register/CancelAppointmnet_Json/")
    Observable<BaseEntity> httpsCancelAppointmnetRx(@Field("AppointmentTime") Date AppointmentTime,
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
     * 获取家政服务预约列表
     * @param page
     * @param pageSize
     * @param userID
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("HouseKeeping/GetMyHouseKeeping/")
    Observable<BaseEntity<KeepingAppointment>> httpsGetMyHouseKeepingRx(@Query("page") int page,
                                                                        @Query("pageSize") int pageSize,
                                                                        @Query("userID") int userID,
                                                                        @Query("startTime") Date startTime,
                                                                        @Query("endTime") Date endTime);


}
