package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Login.User;

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
    //获取个人信息
    //http://www.yuezhan.co:809/Login/Verification_json?Phone=13677778888&password=e10adc3949ba59abbe56e057f20f883e
    @GET("Login/Verification_json/")
    Observable<User> httpsUserInfoRx(@Query("Phone") String Phone, @Query("password") String password);

    @FormUrlEncoded
    @POST("Login/ResetPassword_json/")
    Observable<BaseEntity<User>> httpsResetPwdRx(@Field("Phone") String Phone, @Field("NewPassword") String password);
}
