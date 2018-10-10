package com.anna.sent.soft.strategy.lifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anna.sent.soft.strategy.base.BaseStrategy;

public class LifecycleStrategy extends BaseStrategy {
    private Listener mListener = null;
    private boolean mIsPaused, mIsResumed;
    private boolean mIsStopped;
    private boolean mIsDestroyed;
    private boolean mIsInstanceStateSaved;

    public LifecycleStrategy() {
        this(null);
    }

    public LifecycleStrategy(Listener listener) {
        mListener = listener;
    }

    public boolean isPaused() {
        return mIsPaused;
    }

    public boolean isResumed() {
        return mIsResumed;
    }

    public boolean isStopped() {
        return mIsStopped;
    }

    public boolean isStarted() {
        return !mIsStopped;
    }

    public boolean isDestroyed() {
        return mIsDestroyed;
    }

    public boolean isInstanceStateSaved() {
        return mIsInstanceStateSaved;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.log("onCreate");
        }

        mIsPaused = false;
        mIsResumed = false;
        mIsStopped = false;
        mIsDestroyed = false;
        mIsInstanceStateSaved = false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mListener != null) {
            mListener.log("onActivityResult("
                    + requestCode
                    + ", "
                    + resultCode
                    + ", "
                    + (data == null
                    ? "data is null"
                    : (data.getExtras() == null ? "bundle is null" : data.getExtras()))
                    + ")");
        }
    }

    @Override
    public void onRestart() {
        if (mListener != null) {
            mListener.log("onRestart");
        }
    }

    @Override
    public void onStart() {
        if (mListener != null) {
            mListener.log("onStart");
        }

        mIsPaused = false;
        mIsResumed = false;
        mIsStopped = false;
        mIsInstanceStateSaved = false;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.log("onRestoreInstanceState");
        }
    }

    @Override
    public void onResume() {
        if (mListener != null) {
            mListener.log("onResume");
        }

        mIsPaused = false;
        mIsResumed = true;
        mIsInstanceStateSaved = false;
    }

    @Override
    public void onPause() {
        if (mListener != null) {
            mListener.log("onPause");
        }

        mIsPaused = true;
        mIsResumed = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mListener != null) {
            mListener.log("onSaveInstanceState");
        }

        mIsInstanceStateSaved = true;
    }

    @Override
    public void onStop() {
        if (mListener != null) {
            mListener.log("onStop");
        }

        mIsStopped = true;
    }

    @Override
    public void onDestroy() {
        if (mListener != null) {
            mListener.log("onDestroy");
        }

        mIsDestroyed = true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mListener != null) {
            mListener.log("onConfigurationChanged");
        }
    }

    @Override
    public void release() {
        mListener = null;
    }

    public interface Listener {
        void log(String msg);
    }
}
