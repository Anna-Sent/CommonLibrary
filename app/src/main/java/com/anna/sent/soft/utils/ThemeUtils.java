package com.anna.sent.soft.utils;

import android.app.Activity;

public class ThemeUtils {
    public static void setupThemeBeforeOnActivityCreate(Activity activity,
                                                        int styleResourceId) {
        activity.setTheme(styleResourceId);
    }
}