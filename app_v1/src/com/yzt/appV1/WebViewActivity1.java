package com.yzt.appV1;


import com.ant.liao.GifView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

public class WebViewActivity1 extends Activity {
	private long exitTime=0;//点击退出的时间

	private YZTWebView webView;
	private String lauchUrl="http://www.yingzt.com/invest?autoResponse=1";//启动url
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		YZTUtils.log(1, "onCreate");
		YZTUtils.log(1, lauchUrl);
		webView=(YZTWebView) findViewById(R.id.webView);
		webView.customWebView();
		webView.loadUrl(lauchUrl);
		
			
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - exitTime) > 5000) {//5s内点击2次退出才可以
				YZTUtils.showToast(getApplicationContext(), "再按一次退出程序");
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
