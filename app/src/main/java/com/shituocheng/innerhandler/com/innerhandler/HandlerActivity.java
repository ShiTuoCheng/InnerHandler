package com.shituocheng.innerhandler.com.innerhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by shituocheng on 2016/11/22.
 */

public class HandlerActivity extends AppCompatActivity {

    //使用时需将此类改为静态，并持有外部类的弱用
    private static class InnerHandler extends Handler{

        private final WeakReference<HandlerActivity> mActivity;

        public InnerHandler(HandlerActivity activity){
            mActivity = new WeakReference<HandlerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mActivity.get();

            if (activity != null){
                // do something
            }
        }
    }

    private final InnerHandler innerHandler = new InnerHandler(this);

    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //模拟线程耗时
        innerHandler.postDelayed(runnable, 2000);
    }
}
