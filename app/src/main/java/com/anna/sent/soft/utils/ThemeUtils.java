package com.anna.sent.soft.utils;

import android.app.Activity;
import android.support.annotation.StyleRes;

public class ThemeUtils {
    public static void setupThemeBeforeOnActivityCreate(Activity activity,
                                                        @StyleRes int styleResourceId) {
        activity.setTheme(styleResourceId);
    }
}
