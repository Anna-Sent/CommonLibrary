package com.anna.sent.soft.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;

public class ActivityUtils {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        } else {
            Intent intent = activity.getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            activity.overridePendingTransition(0, 0);
            activity.finish();
            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        }
    }
}