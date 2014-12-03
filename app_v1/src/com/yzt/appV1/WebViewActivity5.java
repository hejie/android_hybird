package com.yzt.appV1;



import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import android.webkit.WebView;


public class WebViewActivity5 extends Activity {
	private WebView webView;
	private String lauchUrl="https://www.yingzt.com/yzt5.html?autoResponse=1";//启动url
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1_web_view);
		Log.v("lauchUrl",lauchUrl);
		webView=(WebView) findViewById(R.id.webView);
		//设置支持js
		webView.getSettings().setJavaScriptEnabled(true);
		//设置UA
		//String ua = webView.getSettings().getUserAgentString();
		//webView.getSettings().setUserAgentString(ua+"; "+ToolsUtils.customUA());
		//webViewClient
		YZTWebViewClient yztWebClient=new YZTWebViewClient(WebViewActivity5.this);
		webView.setWebViewClient(yztWebClient);
		//webChromeClient
		YZTWebChromeClient yztWebChromeClient=new YZTWebChromeClient(WebViewActivity5.this);
		webView.setWebChromeClient(yztWebChromeClient);
				
		webView.loadUrl(lauchUrl);
			
	}
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			if(webView.canGoBack()){
				webView.goBack();
				return true;
			}else{
				super.onBackPressed();
				overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right); 
			}
			
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onPause() {  
	    // TODO Auto-generated method stub  
	    super.onPause();  
	    //overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right); 
	}
}
