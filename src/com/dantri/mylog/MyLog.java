package com.dantri.mylog;

import android.util.Log;

public class MyLog {
	private final static boolean showLog = false;
	
	public static void i(String tag, String msg) {
		if(showLog){
			Log.i(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if(showLog){
			Log.e(tag, msg);
		}
	}
}
