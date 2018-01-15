package com.anna.sent.soft.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anna.sent.soft.logging.MyLog;
import com.anna.sent.soft.strategy.activity.StrategyFragment;
import com.anna.sent.soft.strategy.lifecycle.LifecycleStrategy;

public abstract class BaseFragment extends StrategyFragment
        implements LifecycleStrategy.Listener {
    private String wrapMsg(String msg) {
        return getClass().getSimpleName() + '@' + Integer.toHexString(hashCode()) + ": " + msg;
    }

    @Override
    public void log(String msg) {
        MyLog.getInstance().logcat(Log.DEBUG, wrapMsg(msg));
    }

    protected void log(Throwable throwable) {
        MyLog.getInstance().logcat(Log.ERROR, throwable);
    }

    protected void report(Throwable throwable) {
        MyLog.getInstance().report(throwable);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MyLog.getInstance().init(getAppTag());
        super.onCreate(savedInstanceState);
    }

    protected abstract String getAppTag();
}
