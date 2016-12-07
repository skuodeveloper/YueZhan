package com.example.skuo.yuezhan.API;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-2.
 */
public interface CaptchaAPI {
    @POST("webservice/sms.php/?method=Submit")
    Observable<String> httpsCaptchaRx(@Query("account") String account,
                                      @Query("password") String password,
                                      @Query("mobile") String mobile,
                                      @Query("content") String content);
}
