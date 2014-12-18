package com.yingzt.invest.guide;

import java.util.ArrayList;
import java.util.List;

import com.yingzt.invest.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class GuideActivity extends Activity {
	private ViewPager mPager;
	private LinearLayout mDotsLayout;
	private ImageButton mBtn;
	
	private List<View> viewList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		needGuide();
		setContentView(R.layout.activity_guide);
		
		mPager = (ViewPager)findViewById(R.id.guide_viewpager);
		mDotsLayout = (LinearLayout)findViewById(R.id.guide_dots);
		mBtn = (ImageButton)findViewById(R.id.guide_btn);
		
		initPager();
		mPager.setAdapter(new ViewPagerAdapter(viewList));
		mPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
					if(i == arg0){
						mDotsLayout.getChildAt(i).setSelected(true);
					} else {
						mDotsLayout.getChildAt(i).setSelected(false);
					}
				}
				if(arg0 == mDotsLayout.getChildCount()-1){
					mBtn.setVisibility(View.VISIBLE);
				} else {
					mBtn.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mBtn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openHome();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_guide, menu);
		return true;
	}
	
	private void initPager(){
		viewList = new ArrayList<View>();
		int[] images = new int[] { R.drawable.new01, R.drawable.new02, R.drawable.new03, R.drawable.new04 };
		int[] texts = new int[] { R.drawable.new_text1, R.drawable.new_text2, R.drawable.new_text3, R.drawable.new_text4 };
		for (int i = 0; i < images.length; i++) {
			viewList.add(initView(images[i],texts[i]));
		}
		initDots(images.length);
	}
	
	private void initDots(int count){
		for (int j = 0; j < count; j++) {
			mDotsLayout.addView(initDot());
		}
		mDotsLayout.getChildAt(0).setSelected(true);
	}
	
	private View initDot(){
		return LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_guide_dot, null);
	}
	
	private View initView(int res,int text){
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_guide_item, null);
		ImageView imageView = (ImageView)view.findViewById(R.id.iguide_img);
		ImageView textview = (ImageView)view.findViewById(R.id.iguide_text);
		imageView.setImageResource(res);
		textview.setImageResource(text);
		return view;
	}
	
	private void openHome(){
		Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
		
		startActivity(intent);
		finish();
	}
	/**
	 * 判断是否是第一次打开app，第一次的话出现引导页面
	 */
	private void needGuide(){
		SharedPreferences spf = getSharedPreferences("com.yingzt.invest", Context.MODE_PRIVATE);  
		boolean b = spf.getBoolean("isfirst", true);  
		if (b) {  
			Editor editor = spf.edit();  
		    editor.putBoolean("isfirst", false);  
		    editor.commit();  
		} else {
		    openHome(); 
		} 
	}
	class ViewPagerAdapter extends PagerAdapter{

		private List<View> data;
		
		
		public ViewPagerAdapter(List<View> data)
		{
			super();
			this.data = data;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(data.get(position));
			return data.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(data.get(position));
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
