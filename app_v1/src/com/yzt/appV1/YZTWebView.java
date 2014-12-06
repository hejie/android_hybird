package com.yzt.appV1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class YZTWebView extends WebView {
	private Context context;
	private String UA="yingztWebView/1.0";
	private YZTWebViewClient yztWebClient;
	private YZTWebChromeClient yztWebChromeClient;
	
	public YZTWebView(Context context){
		super(context);
		this.context=context;
		customWebView();
	}
	public YZTWebView(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
		customWebView();
	}
	public YZTWebView(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
		// TODO Auto-generated constructor stub
		this.context=context;
		//customWebView();
	}
	/**
	 * 自定义webview
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public void customWebView(){
		//设置支持js
		getSettings().setJavaScriptEnabled(true);
		setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   
		setHorizontalScrollBarEnabled(false);   
		getSettings().setSupportZoom(true);   
		getSettings().setBuiltInZoomControls(true);   
		setInitialScale(70);   
		setHorizontalScrollbarOverlay(true);  
		//设置UA
		String ua = getSettings().getUserAgentString();
		getSettings().setUserAgentString(ua+"; "+UA);
		//设置webViewClient
		this.yztWebClient=new YZTWebViewClient(context);
		setWebViewClient(this.yztWebClient);
		//设置webChromeClient
		this.yztWebChromeClient=new YZTWebChromeClient(context);
		setWebChromeClient(this.yztWebChromeClient);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
