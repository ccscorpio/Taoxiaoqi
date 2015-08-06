package com.android.taoxiaoqi.util;



import com.android.taoxiaoqi.GlobalApplication;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @description Json解析工具类
 */
public class JsonResolveUtils {
	

	private static final String NEARBY_GROUP = "webmessage.json";
	/**
	 * @return
	 */
	public static boolean resolveNearbyGroup(GlobalApplication application) {
		if (application.webmessage != null
				&& application.webmessage.isEmpty()) {
			String json = TextUtils.getJson(
                    application.getApplicationContext(), NEARBY_GROUP);
			if (json != null) {
				try {
					
					application.webmessage=json;
				
				
				} catch (Exception e) {
					application.webmessage="";
				}
			}
		}
		if (application.webmessage.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
