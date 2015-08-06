package com.android.taoxiaoqi.base.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.taoxiaoqi.GlobalApplication;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	public DisplayImageOptions options;
	protected BaseActivity mContext;// 上下文
	protected GlobalApplication baseApplication;
	public SharedPreferences cacheSp;
	public float screenWidth;
	public float screenHight;
	protected View ll_hidden;
	protected TextView tv_show;
	protected Button bt_again;
	protected boolean showTitleLayout = true;
	protected LinearLayout ll_back, ll_tel;
	/** 图片引入 */
	protected ImageLoader imageLoader;
	/** 进度条 */

	//protected LoadingDialog mLoadingDialog;

	protected Toast toast;
	protected TextView tv_toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 添加Activity到堆
		super.onCreate(savedInstanceState);
		
		GlobalApplication.getInstance().addActivity(this);
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;
		imageLoader = ImageLoader.getInstance();
		mContext = this;
		baseApplication = (GlobalApplication) getApplication();
		
		cacheSp = getSharedPreferences("cache", Context.MODE_PRIVATE);
	/*	mLoadingDialog=new LoadingDialog(this);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading_default)
		.showImageForEmptyUri(R.drawable.loading_default)
		.showImageOnFail(R.drawable.loading_default).cacheInMemory(true)
		.cacheOnDisc(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();*/
		//initToastView();
	}
	/**
	 * 初始化土司视图
	 */
	/*private void initToastView() {
		View view = getLayoutInflater().inflate(R.layout.item_toast, null);
		tv_toast = (TextView) view.findViewById(R.id.tv_toast);
		toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setView(view);
	}

	public void showToast(String content) {
		tv_toast.setText(content);
		toast.show();
	}
	
	*//**
	 * 
	 * @param 显示进度条和内容
	 *//*
	public void showLoadingDialog() {
		mLoadingDialog.show();
	}

	*//**
	 * 关闭进度条
	 *//*
	public void dismissLoadingDialog() {
		if (mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}*/




	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 结束Activity&从栈中移除该Activity
		GlobalApplication.getInstance().finishActivity(this);
		
	}

	/**
	 * 初始化view对象
	 */
	public abstract void initView();

/*	*//**
	 * 初始化数据
	 *//*
	public abstract void initData();*/

	/**
	 * 数据提取
	 */
	/*public String getSharedPreferenceValue(String key) {
		if (sp == null) {
			sp = getSharedPreferences(ConstantValue.config,
					Context.MODE_PRIVATE);
		}
		return sp.getString(key, null);
	}*/





/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition( 0,R.anim.push_right_out);
		
		} 
		return super.onKeyDown(keyCode, event);
	}*/
	
	 
}