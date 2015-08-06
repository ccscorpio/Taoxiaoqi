package com.android.taoxiaoqi.network;

public interface ResponseResult<T> {

	  public void onSuccess(T data);
	  

	  public void onFailure(String error, String message);

}
