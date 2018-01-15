package com.anna.sent.soft.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.StyleRes;

public abstract class SettingsTheme {
    protected final Context context;

    public SettingsTheme(Context context) {
        this.context = context;
    }

    public int getThemeId() {
        int defaultValue = getDefaultThemeId();

        SharedPreferences settings = getSettings();
        String value = settings.getString(getThemeKey(), null);
        if (value == null) {
            return defaultValue;
        }

        try {
            int id = Integer.parseInt(value);
            int index = getThemeIndex(id);
            if (index == -1) {
                return defaultValue;
            }

            return id;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setThemeId(int value) {
        SharedPreferences settings = getSettings();
        Editor editor = settings.edit();
        editor.putString(getThemeKey(), String.valueOf(value));
        editor.apply();
    }

    @StyleRes
    public int getStyle() {
        TypedArray styles = context.getResources().obtainTypedArray(getStyles());
        int id = getThemeId();
        int index = getThemeIndex(id);
        int resourceId = styles.getResourceId(index, -1);
        styles.recycle();
        return resourceId;
    }

    private int getThemeIndex(int id) {
        String[] themes = context.getResources().getStringArray(getThemeIds());
        for (int i = 0; i < themes.length; ++i) {
            if (themes[i].equals(String.valueOf(id))) {
                return i;
            }
        }

        return -1;
    }

    public boolean isDefaultTheme() {
        return isDefaultTheme(getThemeId());
    }

    public boolean isDefaultTheme(int id) {
        return id == getDefaultThemeId();
    }

    protected abstract SharedPreferences getSettings();

    public abstract String getThemeKey();

    @ArrayRes
    protected abstract int getThemes();

    @ArrayRes
    protected abstract int getThemeIds();

    @ArrayRes
    protected abstract int getStyles();

    protected abstract int getDefaultThemeId();
}
