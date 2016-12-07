package com.example.skuo.yuezhan.HttpUtils;

import com.example.skuo.yuezhan.API.OnProgressResponseListener;
import com.example.skuo.yuezhan.HttpUtils.Converter.AvatarConverter;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.skuo.yuezhan.HttpUtils.OkHttpHelper.addLogClient;
import static com.example.skuo.yuezhan.HttpUtils.OkHttpHelper.addProgressClient;

/**
 * Created by LiCola on  2016/04/16  0:08
 */
public class RetrofitClient {

    //所有的联网地址 统一成https
    private static String mBaseUrl = "http://www.yuezhan.co:801/";
    private static Gson gson = new Gson();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为jason的支持
            .addConverterFactory(AvatarConverter.create(gson));

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .client(addLogClient(httpClient).build())
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder
                .baseUrl(mBaseUrl)
                .client(addLogClient(httpClient).build())
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, OnProgressResponseListener listener) {
        Retrofit retrofit = builder
                .client(addProgressClient(httpClient, listener).build())
                .build();
        return retrofit.create(serviceClass);
    }
}
