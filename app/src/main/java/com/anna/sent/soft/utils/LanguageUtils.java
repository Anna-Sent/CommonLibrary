package com.anna.sent.soft.utils;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageUtils {
    public static void setupLanguageAfterOnActivityCreate(Activity activity,
                                                          boolean isLanguageSetByUser, String locale) {
        if (isLanguageSetByUser) {
            Configuration config = new Configuration(activity.getResources()
                    .getConfiguration());
            config.locale = new Locale(locale);
            Locale.setDefault(config.locale);
            Resources baseResources = activity.getBaseContext().getResources();
            baseResources.updateConfiguration(config,
                    baseResources.getDisplayMetrics());
        }
    }
}