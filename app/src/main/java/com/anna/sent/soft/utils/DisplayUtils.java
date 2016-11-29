package com.anna.sent.soft.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Orientation of device affects on returned width, height.
 * <p>
 * determining tablet - with configuration layout info; with resources values
 *
 * @author Anna
 */
public class DisplayUtils {
    /**
     * Gets size of screen in density-independent pixels (dp, dip).
     *
     * @param context Context of the app.
     * @return Size (width and height) of screen in dp.
     */
    @SuppressLint("NewApi")
    public static Point getScreenSizeInDpWrapped(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int widthDp = context.getResources().getConfiguration().screenWidthDp;
            int heightDp = context.getResources().getConfiguration().screenHeightDp;
            if (widthDp != Configuration.SCREEN_WIDTH_DP_UNDEFINED
                    && heightDp != Configuration.SCREEN_HEIGHT_DP_UNDEFINED) {
                Point outPoint = new Point();
                outPoint.x = widthDp;
                outPoint.y = heightDp;
                return outPoint;
            }
        }

        return getScreenSizeInDp(context);
    }

    private static Point getScreenSizeInDp(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);

        float density = metrics.density;

        int widthPx = metrics.widthPixels;
        int heightPx = metrics.heightPixels;

        int widthDp = (int) (widthPx / density);
        int heightDp = (int) (heightPx / density);

        Point outPoint = new Point();
        outPoint.x = widthDp;
        outPoint.y = heightDp;
        return outPoint;
    }

    /**
     * Gets diagonal of screen in inches.
     *
     * @param context Context of the app.
     * @return Diagonal length of the device screen, in inches.
     */
    @SuppressLint("NewApi")
    public static double getScreenDiagonalInInches(Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        boolean isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT;

        DisplayMetrics metrics = getDisplayMetrics(context);

        float xDpi = isPortrait ? metrics.xdpi : metrics.ydpi;
        float yDpi = isPortrait ? metrics.ydpi : metrics.xdpi;

        int widthPx = metrics.widthPixels;
        int heightPx = metrics.heightPixels;

        float widthIn = widthPx / xDpi;
        float heightIn = heightPx / yDpi;

        return Math.sqrt(widthIn * widthIn + heightIn * heightIn);
    }

    /**
     * Gets display metrics.
     *
     * @param context Context of the app.
     * @return DisplayMetrics object for the device screen.
     */
    @SuppressLint("NewApi")
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(metrics);
        } else {
            display.getMetrics(metrics);
        }

        return metrics;
    }

    /**
     * Gets size of screen in pixels (px).
     *
     * @param context Context of the app.
     * @return Size (width and height) of screen in px.
     */
    public static Point getScreenSizeInPx(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        return getScreenSizeInPx(display);
    }

    @SuppressLint("NewApi")
    private static Point getScreenSizeInPx(Display display) {
        Point outPoint = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics); // display.getRealSize(outPoint);
            outPoint.x = metrics.widthPixels;
            outPoint.y = metrics.heightPixels;
            return outPoint;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                outPoint.x = (Integer) mGetRawW.invoke(display);
                outPoint.y = (Integer) mGetRawH.invoke(display);
                return outPoint;
            } catch (Throwable ignored) {
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(outPoint);
            return outPoint;
        }

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        outPoint.x = metrics.widthPixels; // display.getWidth();
        outPoint.y = metrics.heightPixels; // display.getHeight();

        return outPoint;
    }
}