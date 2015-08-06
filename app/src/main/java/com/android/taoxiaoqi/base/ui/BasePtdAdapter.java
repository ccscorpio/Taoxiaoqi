package com.android.taoxiaoqi.base.ui;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.taoxiaoqi.model.Entity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public abstract class BasePtdAdapter extends BaseAdapter{
	protected Activity mActivity;
	/**
	 * 加载图片的工具类
	 */
	protected ImageLoader imageLoader;
	protected DisplayImageOptions defaultOptions,newsOptions;
	public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	protected List<? extends Entity> mDatas;
	
	protected SimpleDateFormat    formatter    =   new    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	protected Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间     
	
	protected int selectPostion=-1;
	public BasePtdAdapter(Activity mActivity) {
		this.mActivity = mActivity;
		
		imageLoader = ImageLoader.getInstance();
	/*	defaultOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading_default)
		//		.showImageForEmptyUri(R.drawable.ic_launcher)
		//		.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(false)
				.cacheOnDisc(true)
				.considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();*/
		
	
		

		
		
		
		
		
		
	}
	public BasePtdAdapter(Activity mActivity,List<? extends Entity> datas) {
		this.mDatas=datas;
		this.mActivity = mActivity;
		imageLoader = ImageLoader.getInstance();
		defaultOptions = new DisplayImageOptions.Builder()
		//		.showImageOnLoading(R.drawable.ic_launcher)
		//		.showImageForEmptyUri(R.drawable.ic_launcher)
		//		.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}
	
	
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
			static final List<String> displayedImages = Collections
					.synchronizedList(new LinkedList<String>());
			
			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				if (loadedImage != null) {
					ImageView imageView = (ImageView) view;
					boolean firstDisplay = !displayedImages.contains(imageUri);
					if (firstDisplay) {
						FadeInBitmapDisplayer.animate(imageView, 500);
						displayedImages.add(imageUri);
					}
				}
			}
		}
	
	public BasePtdAdapter(Activity mActivity,List<? extends Entity> datas,int i) {
		this.selectPostion=1;
		this.mDatas=datas;
		this.mActivity = mActivity;
		imageLoader = ImageLoader.getInstance();
		defaultOptions = new DisplayImageOptions.Builder()
		//		.showImageOnLoading(R.drawable.ic_launcher)
		//		.showImageForEmptyUri(R.drawable.ic_launcher)
		//		.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}

}
	

   
