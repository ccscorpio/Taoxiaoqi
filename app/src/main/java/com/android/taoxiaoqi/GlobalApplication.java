package com.android.taoxiaoqi;



import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * 自定义一个本application，用来完成一些数据的初始化工作
 * 
 * @author zhu
 * @date 2014-10-15
 */
public class GlobalApplication extends Application {
	private static Stack<Activity> activityStack;
	private static GlobalApplication singleton;
	
	
	public String webmessage="";
	

	public String userid;//用户ID

	public boolean isMainProcess() {
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = android.os.Process.myPid();
		for (RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//初始化更新下载服务
		//FIR.init(this);
		//初始化图片引入
		initImageLoader(this);
	
		//ButterKnife.setDebug(BuildConfig.DEBUG);
		singleton = this;
		

		
		
	}

	// Returns the application instance
	public static GlobalApplication getInstance() {
		return singleton;
	}

	public void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

	}
	/**
	 * 添加activity到栈中
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity
	 */
	public Activity currentActivity() {
		return activityStack.lastElement();
	}

	/**
	 * 结束当前Activity
	 */
	public void finishActivity() {
		finishActivity(activityStack.lastElement());
	}

	/**
	 * 结束某个Activity
	 */
	public void finishActivity(Class<?> cls) {
		
		if(cls!=null&&activityStack!=null){
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	  }
	}

	/**
	 * 结束某个Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}
	
	/**
	 * 退出程序
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
		System.exit(0);
	}
	
	/**
	 * 查看该Activity是否存在在堆栈中
	 * 
	 * @param cls
	 * @return
	 */
	public boolean findActivity(Class<?> cls) {
		boolean l=false;
		if(cls!=null&&activityStack!=null){
			
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					l=true;
					break;
				}
			}
		}
	
		return l;
	}

	/**
	 * 当前任务栈是否存在activity
	 * @return
	 */
	public boolean exitActivity(){
		if (activityStack == null) 
			return false;
		return activityStack.size() == 0 ? false:true;
	}
}
