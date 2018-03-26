package com.anna.sent.soft.logging;

import android.content.Context;
import android.util.Log;

import com.anna.sent.soft.BuildConfig;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

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

    private static void noFirebaseLogcat(int level, String tag, String msg) {
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

        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        Fabric.with(context.getApplicationContext(), crashlyticsKit);
        mIsInitialized = true;
    }

    public void logcat(int level, String msg) {
        if (mIsInitialized) {
            Crashlytics.log(level, tag, msg);
        } else {
            noFirebaseLogcat(level, tag, msg);
        }
    }

    public void logcat(int level, Throwable throwable) {
        if (mIsInitialized) {
            Crashlytics.log(level, tag, Log.getStackTraceString(throwable));
        } else {
            noFirebaseLogcat(level, tag, Log.getStackTraceString(throwable));
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
            noFirebaseLogcat(Log.ERROR, tag, Log.getStackTraceString(throwable));
        }
    }
}
