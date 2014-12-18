package com.yingzt.invest.webview;


import com.yingzt.invest.YZTUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class YZTWebView extends WebView {
	private Context context;
	private String UA;
	private YZTWebViewClient yztWebClient;
	private YZTWebChromeClient yztWebChromeClient;
	
	public YZTWebView(Context context){
		super(context);
		this.context=context;
	}
	public YZTWebView(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public YZTWebView(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	/**
	 * 自定义webview
	 */
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	public void customWebView(){
		setUA();
		setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   
		setHorizontalScrollBarEnabled(false);
		//webview支持页面缩放，一般通过以下设置就可满足大部分的要求
		getSettings().setSupportZoom(false); 
		//设置是否可缩放
		getSettings().setBuiltInZoomControls(false);  
		//设置webview默认缩放比例：
		setInitialScale(70);   
		setHorizontalScrollbarOverlay(true);  
		
		//setBackgroundResource(R.drawable.ic_launcher);//然后设置背景图片 
		
		//设置webViewClient
		this.yztWebClient=new YZTWebViewClient(context);
		setWebViewClient(this.yztWebClient);
		//设置webChromeClient
		this.yztWebChromeClient=new YZTWebChromeClient(context);
		setWebChromeClient(this.yztWebChromeClient);
	}
	/**
	 * 设置ua，用于预加载的webview
	 */
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	public void setUA(){
		//设置UA
		String ua = getSettings().getUserAgentString();
		UA=YZTUtils.getUA(context);
		getSettings().setUserAgentString(ua+"; "+UA);
		//设置支持js
		getSettings().setJavaScriptEnabled(true);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
