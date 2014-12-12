package com.yingzt.invest;

import com.ant.liao.GifView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class YZTWebChromeClient extends WebChromeClient {
	private Context context;
	public YZTWebChromeClient(Context context){
		super();
		this.context=context;
		
	}
	@Override
	public void onProgressChanged(WebView view,int progress){
		
		
	}
}
