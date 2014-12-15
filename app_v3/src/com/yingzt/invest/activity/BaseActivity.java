package com.yingzt.invest.activity;

import com.yingzt.invest.R;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * App的Activity基类，处理导航等统一的东西
 */
public class BaseActivity extends Activity {

    protected TextView mNavBackTitleTextView = null;  //顶部导航左边返回位置的文字
    protected TextView mNavTitleTextView = null;      //顶部导航中间title
    protected TextView mNavRightTextView = null;      //顶部导航右边文字
    protected ImageView mNavLeftPersonImagView = null; //顶部导航左边个人头像

    /**
     * 设置顶部导航居中显示的title
     * @param title
     */
    protected void setNavTitle(int title) {
        if (mNavTitleTextView == null) {
            mNavTitleTextView = (TextView)findViewById(R.id.nav_title);
        }

        mNavTitleTextView.setText(title);
        mNavTitleTextView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置顶部导航返回按钮旁边的文字
     * @param backTitle
     */
    protected void setNavBackTitle(int backTitle) {
        if (mNavBackTitleTextView == null) {
            mNavBackTitleTextView = (TextView)findViewById(R.id.btn_nav_back_title);

            mNavBackTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        mNavBackTitleTextView.setText(backTitle);
        mNavBackTitleTextView.setVisibility(View.VISIBLE);

    }


    /**
     * 设置顶部导航右边按钮文字
     * @param rightTitle
     */
    protected void setNavRightText(int rightTitle) {
        if (mNavRightTextView == null) {
            mNavRightTextView = (TextView)findViewById(R.id.btn_nav_right_title);

            mNavRightTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        mNavRightTextView.setText(rightTitle);
        mNavRightTextView.setVisibility(View.VISIBLE);
    }


    /**
     * 设置顶部导航右边的个人icon的listener，并显示这个icon
     */
    protected void setNavLeftPersonViewOnClickListener(android.view.View.OnClickListener l) {
        if (mNavLeftPersonImagView == null) {
            mNavLeftPersonImagView = (ImageView)findViewById(R.id.btn_nav_person);

            mNavLeftPersonImagView.setOnClickListener(l);
        }

        mNavLeftPersonImagView.setVisibility(View.VISIBLE);
    }


    /**
     * 给个机会重设返回的事件处理
     * @param listener
     */
    public void setNavBackOnClickListener(android.view.View.OnClickListener listener) {
        mNavBackTitleTextView.setOnClickListener(listener);
    }

    /**
     * 给个机会重设右边按钮的事件处理
     * @param listener
     */
    public void setNavRightTextOnClickListener(android.view.View.OnClickListener listener) {
        mNavRightTextView.setOnClickListener(listener);
    }


}
