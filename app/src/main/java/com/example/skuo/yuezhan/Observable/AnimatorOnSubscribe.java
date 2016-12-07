package com.example.skuo.yuezhan.Observable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import com.example.skuo.yuezhan.Util.Logger;

import rx.Observable;
import rx.Subscriber;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by wuwenqi on  2016/11/28  15:39
 */
public class AnimatorOnSubscribe implements Observable.OnSubscribe<Void> {
    final Animator animator;

    //构造器传入Animator
    public AnimatorOnSubscribe(Animator animator) {
        this.animator = animator;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        checkUiThread();
        AnimatorListenerAdapter adapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                subscriber.onNext(null);
                Logger.d("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                subscriber.onCompleted();
                Logger.d("onAnimationEnd");
            }
        };

        animator.addListener(adapter);
        animator.start();
    }
}
