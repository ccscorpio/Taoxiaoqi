package com.android.taoxiaoqi.base.ui;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseDialog extends Dialog{
	protected Toast toast;
	protected TextView tv_toast;
	
	protected Context context;
	public BaseDialog(Context context) {
		super(context);
		this.context=context;
		
	}

	
}
