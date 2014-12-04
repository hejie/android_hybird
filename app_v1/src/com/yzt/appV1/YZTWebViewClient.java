package com.yzt.appV1;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class YZTWebViewClient extends WebViewClient {
	private Context context;
	private String errorPage = "file:///android_asset/www/error.html";

	public YZTWebViewClient(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		// YZTUtils.showToast(context,
		// errorCode+":"+description+":"+failingUrl);
		view.loadUrl(errorPage);
	}

	/*
	 * 这个事件，将在用户点击链接时触发。 通过判断url，可确定如何操作， 如果返回true，表示我们已经处理了这个request，
	 * 如果返回false，表 示没有处理， 那么浏览器将会根据url获取网页
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// 直接拉起webview切换的接口,格式为jsbridge://+要打开的url
		if (url.toLowerCase().startsWith("jsbridge://")) {
			jsBridge(url.substring("jsbridge://".length()));
			// js很本地交互的接口
		} else if (url.toLowerCase().startsWith("jsinterface://")) {

		} else {
			view.loadUrl(url);
		}

		return true; // 表 示已经处理了这次URL的请求
	}

	@SuppressLint("NewApi")
	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
		YZTUtils.log(1, "url=" + url);
		WebResourceResponse response = null;
		Uri uri = Uri.parse(url);
		String path = uri.getEncodedPath();
		String autoResponse = uri.getQueryParameter("autoResponse");// 引用静态资源的时候如果带上这个参数，则做本地替换
		if (autoResponse != null) {
			// Log.v("autoResponse",autoResponse);
			try {
				InputStream localCopy = context.getAssets().open("www" + path);
				// InputStream localCopy = context.getAssets().open(
				// "www/yzt.html");
				if (path.toLowerCase().contains(".js")) {// js
					response = new WebResourceResponse("text/javascript",
							"UTF-8", localCopy);
					Log.v("autoResponse js", url);
				} else if (path.toLowerCase().contains(".css")) {// css
					response = new WebResourceResponse("text/css", "UTF-8",
							localCopy);
					Log.v("autoResponse css", url);
				} else if (path.toLowerCase().contains(".png")) {// png
																	// 图片
					response = new WebResourceResponse("image/png", "UTF-8",
							localCopy);
					Log.v("autoResponse png", url);
				} else if (path.toLowerCase().contains(".gif")) {// gif
					// 图片
					response = new WebResourceResponse("image/gif", "UTF-8",
							localCopy);
					Log.v("autoResponse png", url);
				} else {// html
					response = new WebResourceResponse("text/html", "UTF-8",
							localCopy);
					Log.v("autoResponse html", url);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.v("error", e.getMessage());
				return null;
			}

		}

		return response;
	}

	/**
	 * 处理js接口
	 * 
	 * @param url
	 */
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

}
