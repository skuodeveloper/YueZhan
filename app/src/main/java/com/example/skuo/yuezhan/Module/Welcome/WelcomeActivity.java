package com.example.skuo.yuezhan.Module.Welcome;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.skuo.yuezhan.API.LoginAPI;
import com.example.skuo.yuezhan.Base.BaseActivity;
import com.example.skuo.yuezhan.Entity.Login.User;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.Module.Login.LoginActivity;
import com.example.skuo.yuezhan.Module.Main.MainActivity;
import com.example.skuo.yuezhan.Observable.MyRxObservable;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.Constant;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.MD5;
import com.example.skuo.yuezhan.Util.SPUtils;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.img_welcome)
    ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    /**
     * 启动欢迎页面
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, WelcomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLogin = (Boolean) SPUtils.get(getApplicationContext(), Constant.ISLOGIN, isLogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animator animation = AnimatorInflater.loadAnimator(mContext, R.animator.welcome_animator);
        //observeOn() 指定的是它之后的操作所在的线程
        //subscribeOn() 作用于Observable对象
        animation.setTarget(mImageView);
        MyRxObservable.add(animation)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())//指定订阅的Observable对象的call方法运行在ui线程中
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        isLogin = (Boolean) SPUtils.get(getApplicationContext(), Constant.ISLOGIN, false);

                        isLogin = false;
                        return isLogin;
                    }
                })
                .flatMap(new Func1<Void, Observable<User>>() {
                    @Override
                    public Observable<User> call(Void aVoid) {
                        Logger.d("flatMap");
                        String userAccount = (String) SPUtils.get(getApplicationContext(), Constant.USERACCOUNT, "");
                        String userPassword = (String) SPUtils.get(getApplicationContext(), Constant.USERPASSWORD, "");

                        return getUserInfo(userAccount, userPassword);
                    }
                })
                //.retryWhen(new RetryWithConnectivityIncremental(WelcomeActivity.this, 4, 15, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())//最后统一回到UI线程中处理
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onStart() {
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();

                        //是否第一次登陆
                        if (isLogin)
                            MainActivity.launch(WelcomeActivity.this);
                        else
                            LoginActivity.launch(WelcomeActivity.this, null);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        LoginActivity.launch(WelcomeActivity.this, e.getMessage());
                        finish();
                    }

                    @Override
                    public void onNext(User userAccount) {
                        Logger.d();
                    }
                });
        //* subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
        // * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
    }

    private Observable<User> getUserInfo(String username, String password) {
        password = MD5.Encryption(password);

        return RetrofitClient.createService(LoginAPI.class)
                .httpsUserInfoRx(username, password);
    }
}
