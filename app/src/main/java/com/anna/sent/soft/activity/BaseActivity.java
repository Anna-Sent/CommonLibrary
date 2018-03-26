package com.anna.sent.soft.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anna.sent.soft.logging.MyLog;
import com.anna.sent.soft.settings.SettingsLanguage;
import com.anna.sent.soft.settings.SettingsTheme;
import com.anna.sent.soft.strategy.activity.StrategyFragmentActivity;
import com.anna.sent.soft.strategy.lifecycle.LifecycleStrategy;
import com.anna.sent.soft.utils.LanguageUtils;
import com.anna.sent.soft.utils.ThemeUtils;

public abstract class BaseActivity extends StrategyFragmentActivity
        implements LifecycleStrategy.Listener {
    private SettingsLanguage settingsLanguage;
    private SettingsTheme settingsTheme;

    private String wrapMsg(String msg) {
        return toString() + ": " + msg;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MyLog.getInstance().init(this, getAppTag(), enableCrashReporting());
        settingsLanguage = createSettingsLanguage();
        settingsTheme = createSettingsTheme();
        setupTheme();
        super.onCreate(savedInstanceState);
        setupLanguage();
    }

    @Override
    protected void addStrategies() {
        super.addStrategies();
        addStrategy(new LifecycleStrategy(this));
    }

    protected void setupTheme() {
        ThemeUtils.setupThemeBeforeOnActivityCreate(this, settingsTheme.getStyle());
    }

    protected void setupLanguage() {
        LanguageUtils.setupLanguageAfterOnActivityCreate(this,
                settingsLanguage.isLanguageSetByUser(), settingsLanguage.getLocale());
    }

    protected abstract String getAppTag();

    protected abstract boolean enableCrashReporting();

    protected abstract SettingsLanguage createSettingsLanguage();

    protected abstract SettingsTheme createSettingsTheme();
}
