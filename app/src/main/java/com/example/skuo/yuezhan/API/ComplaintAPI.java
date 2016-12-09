package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Complaint.Complaint;
import com.example.skuo.yuezhan.Entity.Complaint.ComplaintList;
import com.example.skuo.yuezhan.Entity.Complaint.ComplaintTypeList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface ComplaintAPI {
    /**
     * 获取投诉类型列表
     *
     * @param PropertyID
     * @param EstateID
     * @param UserID
     * @return
     */
    @GET("Complaint/GetComplaintTypeList_Json/")
    Observable<BaseEntity<ComplaintTypeList>> httpsGetComplaintTypeListRx(@Query("PropertyID") int PropertyID,
                                                                          @Query("EstateID") int EstateID,
                                                                          @Query("UserID") int UserID);

    /**
     * 查询投诉列表
     * @param page
     * @param pageSize
     * @param UserAccountID
     * @return
     */
    @GET("Complaint/GetComplaintList_Json/")
    Observable<BaseEntity<ComplaintList>> httpsGetComplaintListRx(@Query("page") int page,
                                                                  @Query("pageSize") int pageSize,
                                                                  @Query("UserAccountID") int UserAccountID);

    /**
     * 查询投诉详细信息
     * @param CommplaintID
     * @return
     */
    @GET("Complaint/GetComplaintDetail_V2_Json/")
    Observable<BaseEntity<Complaint>> httpsGetComplaintDetailRx(@Query("CommplaintID") int CommplaintID);

    /**
     * 查询投诉照片列表
     * @param CommplaintID
     * @return
     */
    @GET("Complaint/GetComplaintImageList_Json/")
    Observable<BaseEntity<Complaint>> httpsGetComplaintImageListRx(@Query("CommplaintID") int CommplaintID);

    /**
     * 提交图片
     *
     * @param ID
     * @param base64string
     * @return
     */
    @FormUrlEncoded
    @POST("Complaint/UploadPhoto_Json/")
    Observable<BaseEntity> httpsUploadPhotoRx(@Field("ID") int ID,
                                              @Field("base64string") String base64string);

    /**
     * 提交投诉
     * @param CltType
     * @param CltInfo
     * @param UserAccountID
     * @param EstateID
     * @return
     */
    @FormUrlEncoded
    @POST("Complaint/SubmitComplaint_Json/")
    Observable<BaseEntity> httpsSubmitComplaintRx(@Field("CltType") int CltType,
                                               @Field("CltInfo") String CltInfo,
                                               @Field("UserAccountID") int UserAccountID,
                                               @Field("EstateID") int EstateID);

    /**
     * 删除投诉
     * @param ID
     * @return
     */
    @FormUrlEncoded
    @POST("Complaint/DeleteComplaint_Json/")
    Observable<BaseEntity> httpsDeleteComplaintRx(@Field("ID") int ID);

    /**
     * 投诉确认
     * @param ID
     * @param Comment
     * @param Score
     * @param ResponseSpeed
     * @return
     */
    @FormUrlEncoded
    @POST("Complaint/ComplaintComfirm_V2_Json/")
    Observable<BaseEntity> httpsComplaintComfirmRx(@Field("ID") int ID,
                                               @Field("Comment") String Comment,
                                               @Field("Score") int Score,
                                               @Field("ResponseSpeed") int ResponseSpeed);
}
