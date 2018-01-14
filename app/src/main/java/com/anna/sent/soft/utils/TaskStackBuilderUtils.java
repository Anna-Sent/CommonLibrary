package com.anna.sent.soft.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;

public class TaskStackBuilderUtils {
    public static void restartFromSettings(Activity activity) {
        Intent intent = new Intent(activity, activity.getClass());
        TaskStackBuilder.create(activity).addNextIntentWithParentStack(intent).startActivities();
    }
}
