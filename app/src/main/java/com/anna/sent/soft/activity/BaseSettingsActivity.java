package com.anna.sent.soft.activity;

import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import com.anna.sent.soft.logging.MyLog;
import com.anna.sent.soft.settings.SettingsLanguage;
import com.anna.sent.soft.settings.SettingsTheme;
import com.anna.sent.soft.utils.LanguageUtils;
import com.anna.sent.soft.utils.NavigationUtils;
import com.anna.sent.soft.utils.ThemeUtils;

@SuppressWarnings("deprecated")
public abstract class BaseSettingsActivity extends PreferenceActivity implements
        OnPreferenceChangeListener, OnSharedPreferenceChangeListener {
    protected SettingsLanguage settingsLanguage;
    protected SettingsTheme settingsTheme;

    private String wrapMsg(String msg) {
        return toString() + ": " + msg;
    }

    protected void log(String msg) {
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
        MyLog.getInstance().init(this, getAppTag(), enableCrashReporting());
        settingsLanguage = createSettingsLanguage();
        settingsTheme = createSettingsTheme();
        setupTheme();
        super.onCreate(savedInstanceState);
        setupLanguage();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavigationUtils.navigateUp(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
