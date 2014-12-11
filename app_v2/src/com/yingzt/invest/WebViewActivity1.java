package com.yingzt.invest;


import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;
import com.yzt.invest.webview.YZTWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public class WebViewActivity1 extends Activity {
	private long exitTime = 0;// 点击退出的时间

	private YZTWebView webView;
	private String lauchUrl = "http://www.yingzt.com/invest";// 启动url

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		//注册信鸽
		registerXG();
		YZTUtils.log(1, "onCreate");
		YZTUtils.log(1, lauchUrl);
		webView = (YZTWebView) findViewById(R.id.webView);
		webView.customWebView();
		webView.loadUrl(lauchUrl);
		

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		try {
			super.onConfigurationChanged(newConfig);
			if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				// land
			} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				// port
			}
		} catch (Exception ex) {
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - exitTime) > 5000) {// 5s内点击2次退出才可以
				YZTUtils.showToast(getApplicationContext(), "再按一次退出程序");
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 注册信鸽
	 */
	public void registerXG(){
		//开启logcat输出，方便debug，发布时请关闭
		XGPushConfig.enableDebug(this, true);
		Context context = getApplicationContext();
		XGPushManager.registerPush(context);	
		Intent service = new Intent(context, XGPushService.class);
		context.startService(service);
	}
}
