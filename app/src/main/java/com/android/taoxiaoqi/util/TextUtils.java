package com.android.taoxiaoqi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;


/**
 * @fileName TextUtils.java
 * @version 1.0
 */
public class TextUtils {
	/**
	 * 添加下划线
	 * 
	 * @param context
	 *            上下文
	 * @param textView
	 *            添加下划线的TextView
	 * @param start
	 *            添加下划线开始的位置
	 * @param end
	 *            添加下划线结束的位置
	 *//*
	public static void addUnderlineText(final Context context,
			final HandyTextView textView, final int start, final int end) {
		textView.setFocusable(true);
		textView.setClickable(true);
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(
				textView.getText().toString().trim());
		spannableStringBuilder.setSpan(new UnderlineSpan(), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(spannableStringBuilder);
	}*/

	/**
	 * 获取括号中的国家区号
	 * 
	 * @param text
	 *            带有括号的国家区号
	 * @param defaultText
	 *            默认的国家区号(在获取错误时返回该值)
	 * @return
	 */
	public static String getCountryCodeBracketsInfo(String text,
			String defaultText) {
		if (text.contains("(") && text.contains(")")) {
			int leftBrackets = text.indexOf("(");
			int rightBrackets = text.lastIndexOf(")");
			if (leftBrackets < rightBrackets) {
				return "+" + text.substring(leftBrackets + 1, rightBrackets);
			}
		}
		if (defaultText != null) {
			return defaultText;
		} else {
			return text;
		}
	}


	/**
	 * 根据月日获取星座
	 * 
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return
	 */
	public static String getConstellation(int month, int day) {
		if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
			return "水瓶座";
		} else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
			return "双鱼座";
		} else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
			return "白羊座";
		} else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
			return "金牛座";
		} else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
			return "双子座";
		} else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
			return "巨蟹座";
		} else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
			return "狮子座";
		} else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
			return "处女座";
		} else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
			return "天秤座";
		} else if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) {
			return "天蝎座";
		} else if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) {
			return "射手座";
		} else if ((((month != 12) || (day < 22)))
				&& (((month != 1) || (day > 19)))) {
			return "魔蝎座";
		}
		return "";
	}

	/**
	 * 根据年月日获取年龄
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return
	 */
	public static int getAge(int year, int month, int day) {
		int age = 0;
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.YEAR) == year) {
			if (calendar.get(Calendar.MONTH) == month) {
				if (calendar.get(Calendar.DAY_OF_MONTH) >= day) {
					age = calendar.get(Calendar.YEAR) - year + 1;
				} else {
					age = calendar.get(Calendar.YEAR) - year;
				}
			} else if (calendar.get(Calendar.MONTH) > month) {
				age = calendar.get(Calendar.YEAR) - year + 1;
			} else {
				age = calendar.get(Calendar.YEAR) - year;
			}
		} else {
			age = calendar.get(Calendar.YEAR) - year;
		}
		if (age < 0) {
			return 0;
		}
		return age;
	}

	/**
	 * 获取Assets中的json文本
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            文本名称
	 * @return
	 */
	public static String getJson(Context context, String name) {
		if (name != null) {
			String path = "json/" + name;
			InputStream is = null;
			try {
				is = context.getAssets().open(path);
				return readTextFile(is);
			} catch (IOException e) {
				return null;
			} finally {
				try {
					if (is != null) {
						is.close();
						is = null;
					}
				} catch (IOException e) {

				}
			}
		}
		return null;
	}

	/**
	 * 从输入流中获取文本
	 * 
	 * @param inputStream
	 *            文本输入流
	 * @return
	 */
	public static String readTextFile(InputStream inputStream) {
		String readedStr = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}
			br.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		return readedStr;
	}
	/**验证手机号国际字段*/
	public static boolean isPhoneNumber(String input){
		String regex="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		return Pattern.matches(regex,input);
	}	
	/**--------获取androidManifest.xml版本编号-------------*/
	public static  int getVersionCode(Context context)  
	{  
	    int versionCode = 0;  
	    try  
	    {  
	        // 获取软件版本号，对应AndroidManifest.xml下android:versionCode  
	        versionCode = context.getPackageManager().getPackageInfo("com.haoyun.android", 0).versionCode;  
	    } catch (NameNotFoundException e)  
	    {  
	        e.printStackTrace();  
	    }  
	    return versionCode;  
	} 
	/**获取DeviceId*/
	public static String getDeviceID(Context context) {
		StringBuilder deviceId = new StringBuilder();
		try {
			    TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
			    String DEVICE_ID = tm.getDeviceId(); 	
			    if(DEVICE_ID!=null&&DEVICE_ID.trim().length()!=0){
			    	deviceId.append(DEVICE_ID);
			    	return deviceId.toString();
			    }
				//IMEI  imei  
				String imei = tm.getDeviceId();
				if(imei!=null&&imei.trim().length()!=0){
					deviceId.append(imei);
					return deviceId.toString();
				}
				//   кţ sn  
				String sn = tm.getSimSerialNumber();
				if(sn!=null&&sn.trim().length()!=0){
					deviceId.append(sn);
					return deviceId.toString();
				}
				//      涼û У        һ  id       
				String uuid = getUUID();
				if(uuid!=null&&uuid.trim().length()!=0){
					deviceId.append(uuid);
					return deviceId.toString();
				}
		} catch (Exception e) {
			e.printStackTrace();
			deviceId.append(getUUID());
		}
		return deviceId.toString();
	}
	public static String getUUID(){ 
		String s = UUID.randomUUID().toString(); 	
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	} 
	
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
	/**
	 * 对象转整数
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if(obj==null) return 0;
		return toInt(obj.toString(),0);
	}
	/**
	 * 字符串转整数
	 * @param str
	 * @param defValue
	 * @return
	 */
	
	public static int toInt(String str, int defValue) {
		try{
			return Integer.parseInt(str);
		}catch(Exception e){}
		return defValue;
	}
	/**
	 * 获取时间
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getTime(Date currentTime){
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	}
	
	

    public static boolean isEmail(String email) {

    	String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    	Pattern p = Pattern.compile(strPattern);
    	Matcher m = p.matcher(email);
    	if (m.matches()) {
    	return true;
    	}else {
    	return false;
    	} 

    }
    
    /**
     * 计算时间差
     */
    @SuppressLint("SimpleDateFormat") 
    public static String timeCompare(String nowtime,String yestertime){
    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  long hour = 0;
    	  long min=0;
		try {
			    Date now;
				now =df.parse(nowtime);
			    Date date=df.parse(yestertime);
			    
			    
			   long l=now.getTime()-date.getTime();
	    	
	    	   hour=l/(60*60*1000);
	    	   min=l/(60*1000);
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(min>0&&min<60){
			return String.valueOf(min)+"分钟前";
		}
		if(min<=0){
			return "1分钟前";
		}
		if(hour<24&&hour>0){
			return String.valueOf(hour)+"小时前";
		}else{
			return yestertime.substring(0, yestertime.length()-8);
		}
    }
    
    public static boolean accountFuhao(String account){
    	  
    	    String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
    	    Pattern pattern = Pattern.compile(regex);
    	    Matcher match=pattern.matcher(account);
    	    boolean b=match.matches();
    	    if(b)
    	    {
    	    	return b;
    	    }
    	    else
    	    {
    	    	return b;
    	    }    
    }
    
    public static byte[] getRequestBytes(String requestPath, String paramContent) {
    	   byte[] resultBytes=null;
    	   try {
			  byte[] pathBytes = requestPath.getBytes("utf-8");
		        byte[] paramBytes;
			  paramBytes = paramContent.getBytes("utf-8");
			  int pathLen = pathBytes.length;
		        int totalLen = pathLen + paramBytes.length + 9;
		       resultBytes = new byte[totalLen];

		        resultBytes[0] = (byte) 'o';
		        resultBytes[1] = (byte) 'c';
		        resultBytes[2] = (byte)(totalLen & 0xF);
		        resultBytes[3] = (byte)(totalLen >> 8 & 0xF);
		        resultBytes[4] = (byte)(totalLen >> 16 & 0xF);
		        resultBytes[5] = (byte)(totalLen >> 24 & 0xF);
		        resultBytes[6] = (byte)5;
		        resultBytes[7] = (byte)(pathLen & 0xF);
		        resultBytes[8] = (byte)(pathLen >> 8 & 0xF);

		        int startPos = 9;
		        for (byte pathByte : pathBytes) {
		            resultBytes[startPos++] = pathByte;
		        }

		        for(byte paramByte : paramBytes){
		            resultBytes[startPos++] = paramByte;
		        }

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        return resultBytes;
    }

    public static byte[] getHeartBeatBytes(String requestPath, String paramContent) throws Exception{
        int totalLen = 7;
        byte[] resultBytes = new byte[totalLen];

        resultBytes[0] = (byte) 'o';
        resultBytes[1] = (byte) 'c';
        resultBytes[2] = (byte)(totalLen & 0xF);
        resultBytes[3] = (byte)(totalLen >> 8 & 0xF);
        resultBytes[4] = (byte)(totalLen >> 16 & 0xF);
        resultBytes[5] = (byte)(totalLen >> 24 & 0xF);
        resultBytes[6] = (byte)2;

        return resultBytes;
    }
}
