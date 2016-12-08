package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Login.ButlerUser;
import com.example.skuo.yuezhan.Entity.Login.UserInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LiCola on  2016/05/23  21:06
 */

public interface LoginAPI {
    /**
     * 用户登陆
     *
     * @param Phone
     * @param password
     * @return
     */
    @GET("Login/Verification_json/")
    Observable<BaseEntity<UserInfo>> httpsUserInfoRx(@Query("Phone") String Phone, @Query("password") String password);

    /**
     * 重设密码
     *
     * @param Phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Login/ResetPassword_json/")
    Observable<BaseEntity> httpsResetPwdRx(@Field("Phone") String Phone, @Field("NewPassword") String password);

    /**
     * 修改用户信息
     *
     * @param UserID
     * @param NickName
     * @param Phone
     * @param Sex
     * @param OldPassword
     * @param NewPassword
     * @return
     */
    @FormUrlEncoded
    @POST("Login/ModifyUserAccount_json/")
    Observable<BaseEntity> httpsModifyUserAccountRx(@Field("UserID") int UserID,
                                                          @Field("NickName") String NickName,
                                                          @Field("Phone") String Phone,
                                                          @Field("Sex ") int Sex,
                                                          @Field("OldPassword") String OldPassword,
                                                          @Field("NewPassword") String NewPassword);

    /**
     * 悦管家用户登录
     *
     * @param Phone
     * @param password
     * @return
     */
    @GET("Login/ButlerUserLogin_json/")
    Observable<BaseEntity<ButlerUser>> httpsButlerUserLoginRx(@Query("Phone") String Phone, @Query("password") String password);

    /**
     * 上传头像
     *
     * @param UserID
     * @param base64string
     * @return
     */
    @FormUrlEncoded
    @POST("Login/UploadPhoto_json/")
    Observable<BaseEntity> httpsUploadPhotoRx(@Field("UserID") int UserID, @Field("base64string") String base64string);


}
