package com.android.taoxiaoqi.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class RequstClient {
	/**
	  * 定义一个异步网络客户端 默认超时未10秒 当超过，默认重连次数为5次 默认最大连接数为10个
	  */
	  private static AsyncHttpClient mClient = new AsyncHttpClient();
	  static {
	    mClient.setTimeout(10000);
	  }
	  /**
	   * post 请求
	   * 
	   * @param url
	   *            API 地址
	   * @param params
	   *            请求的参数
	   * @param handler
	   *            数据加载句柄对象
	   */
	  public static void get(String url, RequestParams params,
	      AsyncHttpResponseHandler handler,Context context) {
		 /*   PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
			mClient.setCookieStore(myCookieStore);*/
		    mClient.get(url, params, handler);
	  }
	  
	  public static void postLogin(String url, RequestParams params,
		    AsyncHttpResponseHandler handler,Context context) {
		    PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
			mClient.setCookieStore(myCookieStore);
		    mClient.post(url, params, handler);
		  }
	  
	  public static void get(String url, AsyncHttpResponseHandler handler) {

	  }
	  public static void get(String url, RequestParams params,AsyncHttpResponseHandler handler) {
		  mClient.get(url, params, handler);  
		  System.out.println("进入get");
	  }
	}
