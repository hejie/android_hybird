package com.yingzt.invest;


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

}
