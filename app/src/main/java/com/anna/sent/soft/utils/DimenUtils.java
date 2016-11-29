package com.anna.sent.soft.utils;

import android.content.Context;

public class DimenUtils {
    public static int pxFromDp(Context context, float dp) {
        int result = Math.round(dp
                * context.getResources().getDisplayMetrics().density);

        if (result == 0) {
            return 1;
        }

        return result;
    }
}