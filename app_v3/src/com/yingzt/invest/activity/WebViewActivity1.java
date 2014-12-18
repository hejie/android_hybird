package com.yingzt.invest.activity;

import com.ant.liao.GifView;
import com.igexin.sdk.PushManager;
import com.yingzt.invest.R;
import com.yingzt.invest.YZTApplication;
import com.yingzt.invest.YZTUtils;
import com.yingzt.invest.R.id;
import com.yingzt.invest.R.layout;
import com.yingzt.invest.webview.YZTWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

public class WebViewActivity1 extends BaseActivity {
	private long exitTime = 0;// 点击退出的时间

	private YZTWebView webView;
	private String launchUrl;// 启动url

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_web_view);
		
		setNavTitle(R.string.home_nav_title);
		
		launchUrl=YZTUtils.getLaunchUrl(getBaseContext());
		launchUrl="app://www.yingzt.com/iscroll.html";
		YZTUtils.log(1, "onCreate");
		YZTUtils.log(1, "lauchUrl:"+launchUrl);
		webView = (YZTWebView) findViewById(R.id.webView);
		webView.customWebView();
		webView.loadUrl(launchUrl);
		PushManager.getInstance().initialize(this.getApplicationContext());
		YZTUtils.log(1, "clinetid:"+PushManager.getInstance().getClientid(this.getApplicationContext()));
		YZTUtils.log(1,"get Payload:"+((YZTApplication)getApplication()).get("notifactionData"));

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
}
