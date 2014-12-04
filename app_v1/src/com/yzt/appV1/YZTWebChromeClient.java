package com.yzt.appV1;

import com.ant.liao.GifView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class YZTWebChromeClient extends WebChromeClient {
	private Context context;
	private GifView gif;//loading图片
	public YZTWebChromeClient(Context context){
		super();
		this.context=context;
		gif=(GifView) ((Activity) context).findViewById(R.id.loading);
		if(gif!=null){
			gif.setGifImage(R.drawable.loading);
			gif.setShowDimension(200, 80);
		}
		
	}
	@Override
	public void onProgressChanged(WebView view,int progress){
		if(gif!=null){
			gif.setVisibility(View.VISIBLE);
			if(progress==100){
				gif.setVisibility(View.GONE);
			}
		}
		
	}
}
