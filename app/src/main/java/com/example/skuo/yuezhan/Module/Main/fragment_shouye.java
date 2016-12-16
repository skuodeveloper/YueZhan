package com.example.skuo.yuezhan.Module.Main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skuo.yuezhan.API.LoginAPI;
import com.example.skuo.yuezhan.Base.BaseFragment;
import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Login.UserInfo;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.BadgeView;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 2016/12/12.
 */

public class fragment_shouye extends BaseFragment {


    private String msg;
    private Context context;
    private String TAG = "TAG";
    private Handler handler = new Handler();

    @BindView(R.id.tv_fragment1)
    ImageView target;

    @BindView(R.id.tv_test)
    TextView mTextView;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_shouye;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        context = getActivity();
        BadgeView badge = new BadgeView(getActivity(), target);
        badge.setText("1");
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badge.setBadgeBackgroundColor(context.getResources().getColor(R.color.red));
        badge.setTextColor(Color.WHITE);
        badge.setTextSize(10);
        badge.show();

        RxView.clicks(target)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                    }
                });

        textRetrofix();

    }

    private void textRetrofix() {
        Log.i("TAG", "textRetrofix");
        RetrofitClient.createService(LoginAPI.class)
                .httpsUserInfoRx("123", "123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<UserInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                        msg = userInfoBaseEntity.getMessage();
                        mTextView.getId();
                        mTextView.setText(msg);
                    }
                });

    }
}
