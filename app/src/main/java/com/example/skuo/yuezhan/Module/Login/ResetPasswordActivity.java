package com.example.skuo.yuezhan.Module.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skuo.yuezhan.API.CaptchaAPI;
import com.example.skuo.yuezhan.API.LoginAPI;
import com.example.skuo.yuezhan.Base.BaseActivity;
import com.example.skuo.yuezhan.Base.SMS_Receiver;
import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Login.User;
import com.example.skuo.yuezhan.HttpUtils.RetrofitClient;
import com.example.skuo.yuezhan.R;
import com.example.skuo.yuezhan.Util.CountDownTimerUtils;
import com.example.skuo.yuezhan.Util.Logger;
import com.example.skuo.yuezhan.Util.MD5;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ResetPasswordActivity extends BaseActivity {
    private final static String SMS_KEY = "cf_Skuo";
    private final static String SMS_PWD = "110405";

    //短信监听
    private static final String ACTION_SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";

    private int captChhaCode;
    private SMS_Receiver smsReceiver;

    @BindView(R.id.telphone)
    EditText mEditPhone;

    @BindView(R.id.captcha)
    EditText mEditCaptcha;

    @BindView(R.id.btnCaptcha)
    Button btnCaptcha;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.newPassword)
    EditText mEditNewPassword;

    @BindView(R.id.confirmPassword)
    EditText mEditConfirmPassword;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String capthca = (String) msg.obj;
                mEditCaptcha.setText(capthca);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加返回箭头
    }

    @Override
    protected void initResAndListener() {
        RxView.clicks(btnCaptcha)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (!attemptGetCaptcha()) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                            imm.hideSoftInputFromWindow(mEditPhone.getWindowToken(), 0); //强制隐藏键盘

                            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btnCaptcha, 30000, 1000);
                            mCountDownTimerUtils.start();

                        } else
                            Toast.makeText(mContext, getString(R.string.send_captcha_failed), Toast.LENGTH_SHORT).show();
                    }
                });

        RxView.clicks(btnSubmit)
                .throttleFirst(500, TimeUnit.MILLISECONDS)//设置500毫秒的间隔 防止抖动 用户点击太快登录多次
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (!attemptSubmit()) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                            imm.hideSoftInputFromWindow(mEditPhone.getWindowToken(), 0); //强制隐藏键盘

                            resetPwd();
                        } else
                            Toast.makeText(mContext, getString(R.string.edit_password_failed), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        smsReceiver = new SMS_Receiver(this, mHandler);
        IntentFilter receiverFilter = new IntentFilter(ACTION_SMS_RECEIVER);
        registerReceiver(smsReceiver, receiverFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(smsReceiver);
    }

    /**
     * 输入项检测
     *
     * @return
     */
    private boolean attemptGetCaptcha() {
        // Reset errors.
        mEditPhone.setError(null);

        // Store values at the time of the login attempt.
        String phone = mEditPhone.getText().toString();
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

        //所有的检查完成 判断是否能开始联网 还是弹出提示
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            getCaptcha();
        }

        return cancel;
    }

    /**
     * 输入项检测
     *
     * @return
     */
    private boolean attemptSubmit() {
        // Reset errors.
        mEditPhone.setError(null);
        mEditCaptcha.setError(null);
        mEditNewPassword.setError(null);
        mEditConfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        String phone = mEditPhone.getText().toString();
        String captcha = mEditCaptcha.getText().toString();
        String newPassword = mEditNewPassword.getText().toString();
        String confirmPassword = mEditConfirmPassword.getText().toString();

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

        // Check for a valid captcha.
        if (TextUtils.isEmpty(captcha)) {
            mEditCaptcha.setError(getString(R.string.input_captcha));
            focusView = mEditCaptcha;
            cancel = true;
        }

        // Check for a valid newPassword.
        if (TextUtils.isEmpty(newPassword)) {
            mEditNewPassword.setError(getString(R.string.input_newpassword));
            focusView = mEditNewPassword;
            cancel = true;
        }

        // Check for a valid newPassword.
        if (TextUtils.isEmpty(confirmPassword)) {
            mEditConfirmPassword.setError(getString(R.string.input_confirmpassword));
            focusView = mEditConfirmPassword;
            cancel = true;
        }

        //判断两次密码输入是否一致
        if (!newPassword.equals(confirmPassword)) {
            mEditCaptcha.setError(getString(R.string.error_notconfirm));
            focusView = mEditCaptcha;
            cancel = true;
        }

        //判断验证码输入是否正确
        if (captChhaCode != Integer.parseInt(mEditCaptcha.getText().toString())) {
            mEditCaptcha.setError(getString(R.string.error_captcha));
            focusView = mEditCaptcha;
            cancel = true;
        }

        if (cancel)
            focusView.requestFocus();

        return cancel;
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
     * 获取验证码
     */
    private void getCaptcha() {
        captChhaCode = (int) (Math.random() * 9000 + 1000);

        RetrofitClient.createService(CaptchaAPI.class,"http://106.ihuyi.cn/")
                .httpsCaptchaRx(SMS_KEY,
                        SMS_PWD,
                        mEditPhone.getText().toString(),
                        String.format(getString(R.string.sms_message), captChhaCode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        Toast.makeText(mContext, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String returnXml) {
                        Logger.d();
                        boolean isSucess = returnXml.contains("提交成功");

                        if (isSucess)
                            Toast.makeText(mContext, getString(R.string.send_captcha_success), Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(mContext, getString(R.string.send_captcha_failed), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 重置密码
     */
    private void resetPwd() {
        RetrofitClient.createService(LoginAPI.class)
                .httpsResetPwdRx(
                        mEditPhone.getText().toString(),
                        MD5.Encryption(mEditNewPassword.getText().toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseEntity<User>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Logger.d();
                    }

                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        Toast.makeText(mContext, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseEntity<User> returnValue) {
                        Logger.d();

                        if(returnValue.getCode() == 0){
                            Toast.makeText(mContext, getString(R.string.edit_password_success), Toast.LENGTH_SHORT).show();
                            LoginActivity.launch(ResetPasswordActivity.this);
                            finish();
                        }else{
                            Toast.makeText(mContext, getString(R.string.edit_password_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
