package com.android.taoxiaoqi.network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.http.Header;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

public class GetCommonEngine<T> extends BaseJsonHttpResponseHandler<T> {

	  private ResponseResult<T> mHandler;
	  private  Class<T> clazz; 
	  public GetCommonEngine(ResponseResult<T> mHandler,Class<T> clazz) {
	  
	    this.mHandler = mHandler;
	    this.clazz=clazz;
	  }
	 

	@Override
	public void onFailure(int arg0, Header[] arg1, Throwable arg2, String arg3,T arg4) {
		onFailure(String.valueOf(arg0),arg3);
	}
	
	
	
	
	@Override
	public void onSuccess(int statusCode, Header[] arg1, String content, T arg3) {
		// TODO Auto-generated method stub
			Log.d("qiqi","!!"+content);
		    System.out.println("得到的返回码" + statusCode);
		 try {
			T object = null;
             Gson gson = new Gson();
			object= gson.fromJson(content.toString(),clazz);
			System.out.println("返回的内容" + new String(URLDecoder.decode(content, "UTF-8")));
			if (mHandler != null) {
				mHandler.onSuccess(object);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			Log.d("qiqi","服务器错误"+e1.getMessage());
			e1.printStackTrace();
		} 
	}
	  /**
	   * 出错
	   */
	public void onFailure(String error, String errorMessage) {
	/*    if (errorMessage != null) {
	    	if (mHandler != null) {*/
				Log.d("qiqi","服务器错误");
	    		 mHandler.onFailure(error, errorMessage);
	//		}
	//    }
	  }
	@Override
	protected T parseResponse(String arg0, boolean arg1) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
