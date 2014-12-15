package com.yingzt.invest.guide;

import java.util.Timer;
import java.util.TimerTask;

import com.yingzt.invest.R;
import com.yingzt.invest.activity.WebViewActivity1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 闪屏，App第一个界面
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //初始化UserAuth全局sharedInstance
        

        /**
         * 延迟三秒
         */
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                
                 Intent intent = new Intent(SplashActivity.this, WebViewActivity1.class);
                  startActivity(intent);

                finish();
            }
        };

        new Timer(false).schedule(timerTask, 2000);
    }

}
