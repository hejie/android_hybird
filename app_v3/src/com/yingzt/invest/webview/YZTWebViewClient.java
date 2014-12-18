package com.yingzt.invest.webview;

import java.io.InputStream;

import com.ant.liao.GifView;
import com.yingzt.invest.R;
import com.yingzt.invest.YZTUtils;
import com.yingzt.invest.R.anim;
import com.yingzt.invest.R.drawable;
import com.yingzt.invest.R.id;
import com.yingzt.invest.activity.WebViewActivity2;
import com.yingzt.invest.activity.WebViewActivity3;
import com.yingzt.invest.activity.WebViewActivity4;
import com.yingzt.invest.activity.WebViewActivity5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class YZTWebViewClient extends WebViewClient {
	private Context context;
	private String errorPage = "file:///android_asset/www/error.html";
	private String localScheme="app";//本地资源协议
	private String jsScheme="jsbridge";//js接口协议
	private String webRoot="www";//本地资源根目录
	private GifView gif;//loading图片


	public YZTWebViewClient(Context context) {
		super();
		this.context = context;
		gif=(GifView) ((Activity) context).findViewById(R.id.loading);
		if(gif!=null){
			gif.setGifImage(R.drawable.loading);
			gif.setShowDimension(200, 80);
		}
	}

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		 YZTUtils.showToast(context, errorCode+":"+description+":"+failingUrl);
		view.loadUrl(errorPage);
	}

	/*
	 * 这个事件，将在用户点击链接时触发。 通过判断url，可确定如何操作， 如果返回true，表示我们已经处理了这个request，
	 * 如果返回false，表 示没有处理， 那么浏览器将会根据url获取网页
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// 直接拉起webview切换的接口,格式为jsbridge://www.yingzt.com?jumpUrl=要打开的url&interfaceName=xx&callBack=xx&num=xx
		//接口分为2中，一种带jumpUrl的，表示拉起webview打开链接；另外一种是不带jumpUrl的，需要callback的
		Uri uri = Uri.parse(url);
		String scheme=uri.getScheme();
		if (scheme.equalsIgnoreCase(jsScheme)) {
			String jumpUrl=uri.getQueryParameter("jumpUrl");
			if(jumpUrl==null||jumpUrl.equals("")){
				String interfaceName=uri.getQueryParameter("interfaceName");
				String callBack=uri.getQueryParameter("callback");
				String num=uri.getQueryParameter("num");
				jsInterface(view,interfaceName,callBack,num);
			}else{
				jsBridge(jumpUrl);
			}
			
		} else {
			view.loadUrl(url);
		}

		return true; // 表 示已经处理了这次URL的请求
	}

	@SuppressLint("NewApi")
	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
		YZTUtils.log(1, "shouldInterceptRequest url=" + url);
		WebResourceResponse response = null;
		Uri uri = Uri.parse(url);
		String path = uri.getEncodedPath();
		String scheme=uri.getScheme();
		//本地资源
		if (scheme.equalsIgnoreCase(localScheme)) {
			try {
				InputStream localCopy = context.getAssets().open(webRoot + path);
				 //InputStream localCopy = context.getAssets().open(
				 //"test.js");
				if (path.toLowerCase().contains(".js")) {// js
					response = new WebResourceResponse("text/javascript",
							"UTF-8", localCopy);
					YZTUtils.log(1,"autoResponse js:"+url);
				} else if (path.toLowerCase().contains(".css")) {// css
					response = new WebResourceResponse("text/css", "UTF-8",
							localCopy);
					YZTUtils.log(1,"autoResponse css:"+url);
				} else if (path.toLowerCase().contains(".png")) {// png
																	// 图片
					response = new WebResourceResponse("image/png", "UTF-8",
							localCopy);
					YZTUtils.log(1,"autoResponse png:"+url);

				} else if (path.toLowerCase().contains(".gif")) {// gif
					// 图片
					response = new WebResourceResponse("image/gif", "UTF-8",
							localCopy);
					YZTUtils.log(1,"autoResponse gif:"+url);

				} else {// html
					response = new WebResourceResponse("text/html", "UTF-8",
							localCopy);
					YZTUtils.log(1,"autoResponse html:"+url);

				}
			} catch (Exception e) {
				e.printStackTrace();
				YZTUtils.log(5,"autoResponse error:"+e.getMessage());

				return null;
			}

		}

		return response;
	}
	@Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        gif.setVisibility(View.VISIBLE);
        	
        
    }

    @Override
    public void onPageFinished(WebView view, String url) {
    	gif.setVisibility(View.GONE);
    	
    }
    @Override
    public void onLoadResource(WebView view, String url) {
    	Uri uri = Uri.parse(url);
    	String path=uri.getPath();
    	//开始加载js的时候就干掉loading
    	if(path!=null&&path.toLowerCase().contains(".js")){
    		gif.setVisibility(View.GONE);
    	}
      }

	/**
	 * 处理js接口
	 * 
	 * @param url
	 */
	@SuppressLint("NewApi")
	public void jsBridge(String url) {
		YZTUtils.log(1, url);
		String activityName = ((Activity) context).getLocalClassName();// 切换的源activity名称
		Class<?> toActivityClass = null;// 切换的目的activity
		int level = Integer.valueOf(activityName.substring(activityName
				.length() - 1));
		switch (level) {
		case 1:
			toActivityClass = WebViewActivity2.class;
			break;
		case 2:
			toActivityClass = WebViewActivity3.class;
			break;
		case 3:
			toActivityClass = WebViewActivity4.class;

			break;
		case 4:
			toActivityClass = WebViewActivity5.class;

			break;
		case 5:
			toActivityClass = WebViewActivity4.class;
			break;
		}

		Intent intent = new Intent();
		intent.setClass(context, toActivityClass);
		intent.putExtra("url", url);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);

		// 设置切换动画，从右边进入，左边退出
		((Activity) context).overridePendingTransition(R.anim.in_from_right,
				R.anim.out_to_left);
	}
	/**
	 * @param view 当前的webview
	 * @param interfaceName 接口名称
	 * @param callBack  回调方法
	 * @param num  调用的系列号
	 */
	public void jsInterface(WebView view,String interfaceName,String callBack,String num){
		if(interfaceName==null||interfaceName.equals("")){
			YZTUtils.log(5, "interfaceName is null");
		}else{
			if(interfaceName.equals("test")){
				view.loadUrl("javascript:"+callBack+"('" + num+ "')");
			}
		}
		
	}

}
