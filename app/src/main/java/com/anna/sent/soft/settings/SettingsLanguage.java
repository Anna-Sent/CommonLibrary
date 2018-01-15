package com.anna.sent.soft.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.ArrayRes;

public abstract class SettingsLanguage {
    protected final Context context;

    public SettingsLanguage(Context context) {
        this.context = context;
    }

    public int getLanguageId() {
        int defaultValue = getDefaultLanguageId();

        if (isLanguageSetByUser()) {
            SharedPreferences settings = getSettings();
            String value = settings.getString(getLanguageKey(), null);
            if (value == null) {
                return defaultValue;
            }

            try {
                int id = Integer.parseInt(value);
                int index = getLanguageIndex(id);
                if (index == -1) {
                    return defaultValue;
                }

                return id;
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        return defaultValue;
    }

    public void setLanguageId(int value) {
        SharedPreferences settings = getSettings();
        Editor editor = settings.edit();
        editor.putString(getLanguageKey(), String.valueOf(value));
        editor.putBoolean(getIsLanguageSetByUserKey(), true);
        editor.apply();
    }

    public String getLocale() {
        String[] locales = context.getResources().getStringArray(getLocales());
        int id = getLanguageId();
        int index = getLanguageIndex(id);
        return locales[index];
    }

    private int getLanguageIndex(int id) {
        String[] languages = context.getResources().getStringArray(getLanguageIds());
        for (int i = 0; i < languages.length; ++i) {
            if (languages[i].equals(String.valueOf(id))) {
                return i;
            }
        }

        return -1;
    }

    public boolean isLanguageSetByUser() {
        SharedPreferences settings = getSettings();
        return settings.getBoolean(getIsLanguageSetByUserKey(), false);
    }

    protected abstract SharedPreferences getSettings();

    public abstract String getLanguageKey();

    protected abstract String getIsLanguageSetByUserKey();

    @ArrayRes
    protected abstract int getLanguages();

    @ArrayRes
    protected abstract int getLanguageIds();

    @ArrayRes
    protected abstract int getLocales();

    protected abstract int getDefaultLanguageId();
}
