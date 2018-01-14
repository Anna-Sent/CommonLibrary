package com.anna.sent.soft.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;

public abstract class SettingsTheme {
    public int getTheme(Context context) {
        int defaultValue = getDefaultThemeId(context);

        SharedPreferences settings = getSettings(context);
        String value = settings.getString(getThemeKey(context), null);
        if (value == null) {
            return defaultValue;
        }

        try {
            int id = Integer.parseInt(value);
            int index = getThemeIndex(context, id);
            if (index == -1) {
                id = defaultValue;
            }

            return id;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getStyle(Context context, int styleArrayResourceId,
                        int defaultStyleResourceId) {
        TypedArray styles = context.getResources().obtainTypedArray(styleArrayResourceId);
        int id = getTheme(context);
        int index = getThemeIndex(context, id);
        int resourceId = styles.getResourceId(index, defaultStyleResourceId);
        styles.recycle();
        return resourceId;
    }

    private int getThemeIndex(Context context, int id) {
        String[] themes = context.getResources().getStringArray(getThemeValuesArrayResourceId());
        for (int i = 0; i < themes.length; ++i) {
            if (themes[i].equals(String.valueOf(id))) {
                return i;
            }
        }

        return -1;
    }

    public void setTheme(Context context, int value) {
        SharedPreferences settings = getSettings(context);
        Editor editor = settings.edit();
        editor.putString(getThemeKey(context), String.valueOf(value));
        editor.apply();
    }

    protected abstract SharedPreferences getSettings(Context context);

    public abstract String getThemeKey(Context context);

    protected abstract int getThemeValuesArrayResourceId();

    protected abstract int getDefaultThemeId(Context context);

    public boolean isDefaultTheme(Context context) {
        int id = getTheme(context);
        return id == getDefaultThemeId(context);
    }

    public boolean isDefaultTheme(Context context, int id) {
        return id == getDefaultThemeId(context);
    }
}
