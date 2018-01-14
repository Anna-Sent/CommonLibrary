package com.anna.sent.soft.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class DisplayUtils {
    public static Point getScreenSizeInDp() {
        int widthDp = Resources.getSystem().getConfiguration().screenWidthDp;
        int heightDp = Resources.getSystem().getConfiguration().screenHeightDp;
        if (widthDp > 0 && heightDp > 0) {
            return createPoint(widthDp, heightDp);
        }

        return getScreenSizeInDp(getDisplayMetrics());
    }

    private static Point getScreenSizeInDp(DisplayMetrics metrics) {
        float density = metrics.density;

        Point sizeInPx = getScreenSizeInPx(metrics);

        int widthDp = (int) (sizeInPx.x / density);
        int heightDp = (int) (sizeInPx.y / density);

        return createPoint(widthDp, heightDp);
    }

    public static Point getScreenSizeInPx() {
        return getScreenSizeInPx(getDisplayMetrics());
    }

    private static Point getScreenSizeInPx(DisplayMetrics metrics) {
        return createPoint(metrics.widthPixels, metrics.heightPixels);
    }

    public static double getScreenDiagonalInInches() {
        int orientation = Resources.getSystem().getConfiguration().orientation;
        boolean isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT;

        DisplayMetrics metrics = getDisplayMetrics();

        float xDpi = isPortrait ? metrics.xdpi : metrics.ydpi;
        float yDpi = isPortrait ? metrics.ydpi : metrics.xdpi;

        Point sizeInPx = getScreenSizeInPx(metrics);

        float widthIn = sizeInPx.x / xDpi;
        float heightIn = sizeInPx.y / yDpi;

        return Math.sqrt(widthIn * widthIn + heightIn * heightIn);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = Resources.getSystem().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static Point createPoint(int width, int height) {
        Point outPoint = new Point();
        outPoint.x = width;
        outPoint.y = height;
        return outPoint;
    }

    private static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }
}
