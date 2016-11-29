package com.anna.sent.soft.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class SettingsLanguage {
    public int getLanguage(Context context) {
        int defaultValue = getDefaultLanguage(context);

        if (isLanguageSetByUser(context)) {
            SharedPreferences settings = getSettings(context);
            String value = settings.getString(getLanguageKey(context), "");
            try {
                int id = Integer.parseInt(value);
                int index = getLanguageIndex(context, id);
                if (index == -1) {
                    id = defaultValue;
                }

                return id;
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        return defaultValue;
    }

    private int getDefaultLanguage(Context context) {
        String[] locales = context.getResources().getStringArray(
                getLocaleArrayResourceId());
        String currentLocale = context.getResources().getConfiguration().locale
                .getLanguage();
        for (int i = 0; i < locales.length; ++i) {
            if (locales[i].equals(currentLocale)) {
                String[] languages = context.getResources().getStringArray(
                        getLanguageValuesArrayResourceId());
                String value = languages[i];
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Incorrect value of language: "
                            + value, e);
                }
            }
        }

        return getDefaultLanguageId(context);
    }

    public String getLocale(Context context) {
        String[] locales = context.getResources().getStringArray(
                getLocaleArrayResourceId());
        int id = getLanguage(context);
        int index = getLanguageIndex(context, id);
        return locales[index];
    }

    private int getLanguageIndex(Context context, int id) {
        String[] languages = context.getResources().getStringArray(
                getLanguageValuesArrayResourceId());
        for (int i = 0; i < languages.length; ++i) {
            if (languages[i].equals(String.valueOf(id))) {
                return i;
            }
        }

        return -1;
    }

    public boolean isLanguageSetByUser(Context context) {
        SharedPreferences settings = getSettings(context);
        return settings.getBoolean(getIsLanguageSetByUserKey(context), false);
    }

    public void setLanguage(Context context, int value) {
        SharedPreferences settings = getSettings(context);
        Editor editor = settings.edit();
        editor.putString(getLanguageKey(context), String.valueOf(value));
        editor.putBoolean(getIsLanguageSetByUserKey(context), true);
        editor.commit();
    }

    protected abstract SharedPreferences getSettings(Context context);

    public abstract String getLanguageKey(Context context);

    protected abstract String getIsLanguageSetByUserKey(Context context);

    protected abstract int getLocaleArrayResourceId();

    protected abstract int getLanguageValuesArrayResourceId();

    protected abstract int getDefaultLanguageId(Context context);
}