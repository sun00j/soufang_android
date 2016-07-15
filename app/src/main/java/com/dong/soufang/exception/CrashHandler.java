package com.dong.soufang.exception;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dong.soufang.activity.WelcomeActivity;
import com.dong.soufang.util.LogUtils;

/**
 * Description:
 * <p>
 * Author: dong
 * Date: 7/15/16
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    private CrashHandler() {

    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtils.e(Log.getStackTraceString(ex));
        Intent intent = new Intent(mContext, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
