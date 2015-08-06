package com.android.taoxiaoqi.base.ui;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
/**
 * 
 * @zhujinming pc
 *
 */
public abstract class BasePopupWindow extends PopupWindow {

	protected View mContentView;
	protected onSubmitClickListener mOnSubmitClickListener;

	
	protected  Context context;
	

	
	public int[] time = {0,1,2,3};
	public ArrayList<String> groups = new ArrayList<String>();
	
	public ArrayList<String> Distancegroups = new ArrayList<String>();
	
	public ArrayList<String> groupsort = new ArrayList<String>();
	
	public BasePopupWindow() {
		super();
	}

	public BasePopupWindow(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);	
	}

	public BasePopupWindow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public BasePopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);	
	}
	public BasePopupWindow(Context context) {
		super(context);	
	}
	public BasePopupWindow(int width, int height) {
		super(width, height);
	}
	public BasePopupWindow(View contentView, int width, int height,
			boolean focusable) {
		super(contentView, width, height, focusable);
	}
	public BasePopupWindow(View contentView) {
		super(contentView);
	}

	@SuppressWarnings("deprecation")
	public BasePopupWindow(View contentView, int width, int height,Context context) {
		super(contentView, width, height, true);
		this.context=context;
		mContentView = contentView;
		setBackgroundDrawable(new BitmapDrawable());//响应返回键，响应触摸周边消失
		setOutsideTouchable(true);
		setFocusable(true);// 响应回退按钮事件
		setTouchable(true);
		initExtraSeller();
		initViews();
		initEvents();
		init();
		
	}
	//public abstract void initExtraSeller();
	


	public abstract void initViews();

	public abstract void initEvents();

	public abstract void init();
	
	public abstract void initExtraSeller();

	public View findViewById(int id) {
		return mContentView.findViewById(id);
	}
	/**
	 * 显示在parent的上部并水平居中
	 * 
	 * @param parent
	 */
	public void showViewTopCenter(View parent) {
		/*showAtLocation(parent, Gravity.TOP | Gravity.CENTER_VERTICAL, 0,300);*/
		showAsDropDown(parent);  //触发形式
	}
	/**
	 * 显示在parent的中心
	 * 
	 * @param parent
	 */
	public void showViewCenter(View parent) {
		showAtLocation(parent, Gravity.CENTER, 0, 0);
	}
	/**
	 * 添加确认单击监听
	 * 
	 * @param l
	 */
	public void setOnSubmitClickListener(onSubmitClickListener l) {
		mOnSubmitClickListener = l;
	}

	public interface onSubmitClickListener {
		void onClick(String result);
		
		
	}
	
	

}
