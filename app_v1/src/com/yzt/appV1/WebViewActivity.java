package com.yzt.appV1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private WebView webView;
	private String lauchUrl="https://www.yingzt.com";//启动url
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		Log.v("lauchUrl",lauchUrl);
		webView=(WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true); //设置支持javascript
		webView.getSettings().setBuiltInZoomControls(true); //设置支持缩放
		webView.loadUrl(lauchUrl);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;
			}
			@Override
			public void onReceivedError(WebView view,int errorCode,String description,String failingUrl){
				Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
			}
			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view,  String url) {
				 //Log.v("debug",url);
			      WebResourceResponse response = null;
			      Uri uri = Uri.parse(url);
			      String path=uri.getEncodedPath();
			      String autoResponse=uri.getQueryParameter("autoResponse");//引用静态资源的时候如果带上这个参数，则做本地替换
			      if(path.toLowerCase().contains("index.css")||autoResponse!=null){
			    	  //Log.v("autoResponse",autoResponse);
			    	  try{
			    		  //InputStream localCopy = getAssets().open("www"+path);
			    		  InputStream localCopy = getAssets().open("www/css/index.css");
				    	  if(path.toLowerCase().contains(".js")){//js
				    		  response = new WebResourceResponse("text/javascript", "UTF-8", localCopy);
				    		  Log.v("autoResponse js",url);
				    	  }else if(path.toLowerCase().contains(".css")){//css
				    		  response = new WebResourceResponse("text/css", "UTF-8", localCopy);
				    		  Log.v("autoResponse css",url);
				    	  }else if(path.toLowerCase().contains(".png")){//png 图片
				    		  response = new WebResourceResponse("image/png", "UTF-8", localCopy);
				    		  Log.v("autoResponse png",url);
				    	  }else{
				    		Log.v("error autoResponse",url);
				    	  }
			    	  }catch(Exception e){
			    		  e.printStackTrace();
			              Log.v("error",e.getMessage());
			              return null;
			    	  }
			    	  
			      }
			      
			      return response;
			  }    
			});
			
	}
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK && webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
