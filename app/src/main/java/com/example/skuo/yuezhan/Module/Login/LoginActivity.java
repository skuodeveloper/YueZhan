package com.example.skuo.yuezhan.Module.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.skuo.yuezhan.API.LoginAPI;
import com.example.skuo.yuezhan.Base.BaseActivity;
import com.example.skuo.yuezhan.Entity.Login.User;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.Module.Main.MainActivity;
import com.example.skuo.yuezhan.Module.Register.RegisterStep1Activity;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.Constant;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.MD5;
import com.example.skuo.yuezhan.Util.NetUtils;
import com.example.skuo.yuezhan.Util.PermissionsUtil;
import com.example.skuo.yuezhan.Util.SPBuild;
import com.example.skuo.yuezhan.Util.SPUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private static final String MESSAGE = "MESSAGE";
    // UI references.
    @BindView(R.id.layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.progress_login)
    ProgressBar mProgressLogin;
    @BindView(R.id.Phone)
    EditText mEditPhone;
    @BindView(R.id.password)
    EditText mEditPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegedit)
    Button btnRegedit;
    @BindView(R.id.showpwd)
    TextView showpwd;
    @BindView(R.id.forgetpwd)
    TextView forgetpwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, String message) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra(MESSAGE, message);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String message = getIntent().getStringExtra(MESSAGE);

        if (!TextUtils.isEmpty(message)) {
            NetUtils.showSnackBar(relativeLayout, message);
        }

        //获取权限
        PermissionsUtil.checkAndRequestPermissions(this);
    }

    @Override
    protected void initResAndListener() {
        //软键盘 确定按钮 监听
        RxTextView.editorActions(mEditPassword, new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer == EditorInfo.IME_ACTION_DONE;
            }
        }).throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        //// TODO: 2016/3/20 0020 需要隐藏软键盘
                        attemptLogin();
                    }
                });

        RxView.clicks(btnLogin)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        attemptLogin();
                    }
                });

        RxView.clicks(btnRegedit)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        RegisterStep1Activity.launch(LoginActivity.this);
                    }
                });

        RxView.clicks(forgetpwd)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ResetPasswordActivity.launch(LoginActivity.this);
                        //finish();
                    }
                });

        RxView.clicks(showpwd)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mEditPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_NORMAL) {
                            showpwd.setText(R.string.login_show_password);
                            mEditPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            showpwd.setText(R.string.login_hint_password);
                            mEditPassword.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                        }
                    }
                });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     * 输入检查 提示错误 正确开始联网登录
     */
    private void attemptLogin() {

        // Reset errors.
        mEditPhone.setError(null);
        mEditPassword.setError(null);

        // Store values at the time of the login attempt.
        String phone = mEditPhone.getText().toString();
        String password = mEditPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid phone.
        if (TextUtils.isEmpty(phone)) {
            mEditPhone.setError(getString(R.string.error_phone_required));
            focusView = mEditPhone;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            mEditPhone.setError(getString(R.string.error_invalid_phone));
            focusView = mEditPhone;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mEditPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEditPassword;
            cancel = true;
        }

        //所有的检查完成 判断是否能开始联网 还是弹出提示
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            httpLogin(phone, password);
        }
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    private void httpLogin(final String username, final String password) {
        RetrofitClient.createService(LoginAPI.class)
                .httpsUserInfoRx(username, MD5.Encryption(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                        showProgress(true);
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        showProgress(false);
                        NetUtils.checkHttpException(mContext, e, relativeLayout);
                    }

                    @Override
                    public void onNext(User user) {
                        Logger.d();
                        showProgress(false);

                        if (getString(R.string.login_failed).equals(user.ErrorMsg)) {
                            NetUtils.showSnackBar(relativeLayout, getString(R.string.login_failed));
                        } else {
                            NetUtils.showSnackBar(relativeLayout, getString(R.string.snack_message_login_success));
                            saveUserInfo(username, password);
                            MainActivity.launch(LoginActivity.this);
                            finish();
                        }
                    }
                });

    }

    /**
     * 验证是否有效手机号
     *
     * @param phone
     * @return
     */
    private boolean isPhoneValid(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 验证是否有效密码
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * 保存登陆用户信息
     *
     * @param mPhone
     * @param mUserPassword
     */
    private void saveUserInfo(String mPhone, String mUserPassword) {
        //保存先清空内容
        SPUtils.clear(getApplicationContext());

        new SPBuild(getApplicationContext())
                .addData(Constant.ISLOGIN, Boolean.TRUE)//登陆志位
                .addData(Constant.LOGINTIME, System.currentTimeMillis())//登陆时间
                .addData(Constant.USERACCOUNT, mPhone)//账号
                .addData(Constant.USERPASSWORD, mUserPassword)//密码
                .build();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressLogin.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            mProgressLogin.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressLogin.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressLogin.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
