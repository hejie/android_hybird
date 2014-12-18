package com.yingzt.invest.activity;


import com.yingzt.invest.R;
import com.yingzt.invest.YZTUtils;
import com.yingzt.invest.R.anim;
import com.yingzt.invest.R.id;
import com.yingzt.invest.R.layout;
import com.yingzt.invest.webview.YZTWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import android.webkit.WebView;

public class WebViewActivity5 extends BaseActivity {
	private YZTWebView webView;

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		YZTUtils.log(1, "onCreate");
		webView = (YZTWebView) findViewById(R.id.webView);
		webView.customWebView();
		Intent intent = getIntent();
		String launchUrl = intent.getStringExtra("url");
		webView.loadUrl(launchUrl);
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

	@SuppressLint("NewApi")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
				return true;
			} else {
				super.onBackPressed();
				overridePendingTransition(R.anim.in_from_left,
						R.anim.out_to_right);
			}

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}
}
