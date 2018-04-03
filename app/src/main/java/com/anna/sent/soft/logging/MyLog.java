package com.anna.sent.soft.logging;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MyLog {
    private static MyLog sInstance;
    private String tag = "MY_LOG";
    private boolean mIsInitialized;

    public static MyLog getInstance() {
        if (sInstance == null) {
            sInstance = new MyLog();
        }

        return sInstance;
    }

    private static void notInitializedLogcat(int level, String tag, String msg) {
        switch (level) {
            case Log.ASSERT:
                Log.wtf(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.INFO:
                Log.i(tag, msg);
                break;
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            default:
                Log.println(level, tag, msg);
                break;
        }
    }

    public void init(Context context, String tag) {
        this.tag = tag;
        if (mIsInitialized) {
            return;
        }

        Fabric.with(context.getApplicationContext(), new Crashlytics());
        mIsInitialized = true;
    }

    public void logcat(int level, String msg) {
        if (mIsInitialized) {
            Crashlytics.log(level, tag, msg);
        } else {
            notInitializedLogcat(level, tag, msg);
        }
    }

    public void logcat(int level, Throwable throwable) {
        if (mIsInitialized) {
            Crashlytics.log(level, tag, Log.getStackTraceString(throwable));
        } else {
            notInitializedLogcat(level, tag, Log.getStackTraceString(throwable));
        }
    }

    public void logcat(int level, String msg, Throwable throwable) {
        logcat(level, msg);
        logcat(level, throwable);
    }

    public void report(Throwable throwable) {
        if (mIsInitialized) {
            Crashlytics.log(Log.ERROR, tag, Log.getStackTraceString(throwable));
            Crashlytics.logException(throwable);
        } else {
            notInitializedLogcat(Log.ERROR, tag, Log.getStackTraceString(throwable));
        }
    }
}
