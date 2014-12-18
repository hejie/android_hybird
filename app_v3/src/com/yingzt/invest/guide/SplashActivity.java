package com.yingzt.invest.guide;

import java.util.Timer;
import java.util.TimerTask;

import com.yingzt.invest.R;
import com.yingzt.invest.YZTUtils;
import com.yingzt.invest.activity.WebViewActivity1;
import com.yingzt.invest.webview.YZTWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 闪屏，App第一个界面
 */
public class SplashActivity extends Activity {
	private int delayTime=3000;//延迟时间
	private YZTWebView webView;

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //预加载首页
        //preload(YZTUtils.getLaunchUrl(getBaseContext()));
        
        /**
         * 延迟三秒
         */
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                 Intent intent = new Intent(SplashActivity.this, WebViewActivity1.class);
                 startActivity(intent);
                 overridePendingTransition(R.anim.in_from_right,
         				R.anim.out_to_left);

                finish();
            }
        };

        new Timer(false).schedule(timerTask, delayTime);
        
        
    }
    /**
     * 预加载url
     * @param url
     */
    private void preload(final String url){
    	YZTUtils.log(1, "preload url=:"+url);
    	webView= new YZTWebView(getBaseContext());
    	webView.setUA();
    	runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }

}
