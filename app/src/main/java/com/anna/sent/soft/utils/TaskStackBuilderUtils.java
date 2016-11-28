package com.anna.sent.soft.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;

public class TaskStackBuilderUtils {
	public static void restartFromSettings(Activity activity, Class<?> cls,
			String extra_configuration_changed) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			Intent intent = new Intent(activity, activity.getClass());
			TaskStackBuilder.create(activity)
					.addNextIntentWithParentStack(intent).startActivities();
		} else {
			activity.finish();
			Intent intent = new Intent(activity, cls);
			intent.putExtra(extra_configuration_changed, true);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
		}
	}
}