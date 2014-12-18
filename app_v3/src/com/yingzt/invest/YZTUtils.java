package com.yingzt.invest;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class YZTUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * log日志
	 * @param level 级别
	 * @param msg  内容
	 */
	public static void log(int level,String msg){
		switch(level){
		case 1:
			Log.d("debug", msg);
			break;
		case 2:
			Log.d("info", msg);
			break;
		case 3:
			Log.d("warn", msg);
			break;
		case 4:
			Log.d("error", msg);
			break;
		case 5:
			Log.d("assert", msg);
			break;
		default:
				Log.d("debug", msg);
				break;
		}
	}
	/**
	 * 显示toast
	 * @param context
	 * @param description 显示的内容
	 */
	public static void showToast(Context context,String description){
		Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 从配置中获取启动url
	 * @param context
	 * @return
	 */
	public static String getLaunchUrl(Context context){
		String launchUrl="";
		InputStream is =  context.getResources().openRawResource( 
                R.raw.system); 
        Properties properties = new Properties(); 
        try { 
            properties.load(is); 
            launchUrl = properties.getProperty("launchUrl", ""); 
            
        } catch (IOException e) { 
            e.getMessage(); 
        } 
        if(launchUrl.equals("")){
        	launchUrl="https://www.yingzt.com/invest";
        }
        return launchUrl;
	}
	/**
	 * 获取UA信息
	 * @param context
	 * @return
	 */
	public static String getUA(Context context){
		String ua="";
		InputStream is =  context.getResources().openRawResource( 
                R.raw.system); 
        Properties properties = new Properties(); 
        try { 
            properties.load(is); 
            ua = properties.getProperty("ua", ""); 
            
        } catch (IOException e) { 
            e.getMessage(); 
        } 
        if(ua.equals("")){
        	ua="yingztApp/1.0";
        }
        return ua;
	}

}
