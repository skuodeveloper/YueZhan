package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Repair.RepairDetail;
import com.example.skuo.yuezhan.Entity.Repair.RepairImageList;
import com.example.skuo.yuezhan.Entity.Repair.RepairList;
import com.example.skuo.yuezhan.Entity.Repair.RepairTypeList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface RepairAPI {
    /**
     * 获取报修类型列表
     *
     * @param PropertyID
     * @param EstateID
     * @param UserID
     * @return
     */
    @GET("Repair/GetRepairTypeList_Json/")
    Observable<BaseEntity<RepairTypeList>> httpsGetRepairTypeListRx(@Query("PropertyID") int PropertyID,
                                                                    @Query("EstateID") int EstateID,
                                                                    @Query("UserID") int UserID);

    /**
     * 查询报修列表
     *
     * @param page
     * @param pageSize
     * @param UserAccountID
     * @return
     */
    @GET("Repair/GetRepairList_Json/")
    Observable<BaseEntity<RepairList>> httpsGetRepairListRx(@Query("page") int page,
                                                            @Query("pageSize") int pageSize,
                                                            @Query("UserAccountID") int UserAccountID);

    /**
     * 查询报修详细信息
     *
     * @param RepairID
     * @return
     */
    @GET("Repair/GetRepairNewDetail_V2_Json/")
    Observable<BaseEntity<RepairDetail>> httpsGetRepairDetailRx(@Query("RepairID") int RepairID);

    /**
     * 查询报修照片列表
     *
     * @param RepairID
     * @return
     */
    @GET("Repair/GetRepairImageList_Json/")
    Observable<BaseEntity<RepairImageList>> httpsGetRepairImageListRx(@Query("RepairID") int RepairID);


    /**
     * 提交图片
     *
     * @param ID
     * @param base64string
     * @return
     */
    @FormUrlEncoded
    @POST("Repair/UploadPhoto_Json/")
    Observable<BaseEntity> httpsUploadPhotoRx(@Field("ID") int ID,
                                              @Field("base64string") String base64string);

    /**
     * 提交报修
     * @param TypeID
     * @param Info
     * @param UserAccountID
     * @param EstateID
     * @param TimeRequest
     * @return
     */
    @FormUrlEncoded
    @POST("Repair/SubmitRepair_V2_Json/")
    Observable<BaseEntity> httpsSubmitRepairRx(@Field("TypeID") int TypeID,
                                               @Field("Info") String Info,
                                               @Field("UserAccountID") int UserAccountID,
                                               @Field("EstateID") int EstateID,
                                               @Field("TimeRequest") String TimeRequest);

    /**
     * 删除报修
     * @param ID
     * @return
     */
    @FormUrlEncoded
    @POST("Repair/DeleteRepair_Json/")
    Observable<BaseEntity> httpsDeleteRepairRx(@Field("ID") int ID);

    /**
     * 报修确认
     * @param ID
     * @param Comment
     * @param Score
     * @param ResponseSpeed
     * @return
     */
    @FormUrlEncoded
    @POST("Repair/RepairComfirm_V2_Json/")
    Observable<BaseEntity> httpsDeleteRepairRx(@Field("ID") int ID,
                                               @Field("Comment") String Comment,
                                               @Field("Score") int Score,
                                               @Field("ResponseSpeed") int ResponseSpeed);
}
