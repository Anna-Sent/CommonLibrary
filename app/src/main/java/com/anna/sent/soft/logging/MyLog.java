package com.anna.sent.soft.logging;

import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

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

    public void init(String tag) {
        this.tag = tag;
        if (mIsInitialized) {
            return;
        }

        mIsInitialized = true;
    }

    public void logcat(int level, String msg) {
        if (mIsInitialized) {
            FirebaseCrash.logcat(level, tag, msg);
        } else {
            noFirebaseLogcat(level, tag, msg);
        }
    }

    public void logcat(int level, Throwable throwable) {
        if (mIsInitialized) {
            FirebaseCrash.logcat(level, tag, Log.getStackTraceString(throwable));
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
            FirebaseCrash.logcat(Log.ERROR, tag, Log.getStackTraceString(throwable));
            FirebaseCrash.report(throwable);
        } else {
            noFirebaseLogcat(Log.ERROR, tag, Log.getStackTraceString(throwable));
        }
    }
}
