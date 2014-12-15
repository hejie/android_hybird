package com.yingzt.invest;

import java.util.HashMap;

import android.app.Application;


public class YZTApplication extends Application {
	
	private HashMap<String, Object> map = new HashMap<String, Object>(); 
    
    public void put(String key,Object object){ 
        map.put(key, object); 
    } 
         
    public Object get(String key){ 
        return map.get(key); 
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
