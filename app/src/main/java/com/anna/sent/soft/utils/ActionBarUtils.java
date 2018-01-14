package com.anna.sent.soft.utils;

import android.app.ActionBar;
import android.app.Activity;

public class ActionBarUtils {
    public static void setupActionBar(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
