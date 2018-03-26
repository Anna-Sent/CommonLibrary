package com.anna.sent.soft.ad;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;

import com.anna.sent.soft.logging.MyLog;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Necessary permission is <uses-permission
 * android:name="android.permission.INTERNET" />
 */
public class AdUtils {
    private static boolean isAdFreeVersion(Context context) {
        return context.getPackageName().endsWith(".pro");
    }

    @Nullable
    @SuppressWarnings("MissingPermission")
    public static AdView setupAd(Activity activity,
                                 @IdRes int adViewId,
                                 @StringRes int adUnitId,
                                 boolean showAd) {
        if (showAd && !isAdFreeVersion(activity)) {
            MyLog.getInstance().logcat(Log.INFO, "ad: Device id is " + getTestDeviceId(activity));
            AdView adView = activity.findViewById(adViewId);
            if (adView != null) {
                MobileAds.initialize(activity.getApplicationContext(), activity.getString(adUnitId));
                com.google.android.gms.ads.AdRequest.Builder adRequestBuilder = new com.google.android.gms.ads.AdRequest.Builder()
                        .setGender(com.google.android.gms.ads.AdRequest.GENDER_FEMALE)
                        .setIsDesignedForFamilies(true);

                adRequestBuilder.addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR);
                String[] testDeviceIds = getTestDeviceIds(activity);
                for (String testDeviceId : testDeviceIds) {
                    adRequestBuilder.addTestDevice(testDeviceId);
                }

                com.google.android.gms.ads.AdRequest adRequest = adRequestBuilder.build();

                MyLog.getInstance().logcat(Log.INFO, "ad: isTestDevice = " + adRequest.isTestDevice(activity));

                adView.loadAd(adRequest);
                return adView;
            }
        }
        return null;
    }

    private static String[] getTestDeviceIds(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            String testDeviceIds = bundle.getString("test_device_ids");
            if (!TextUtils.isEmpty(testDeviceIds)) {
                return testDeviceIds.split(",");
            }
        } catch (Exception e) {
            MyLog.getInstance().logcat(Log.ERROR, "failed to load meta-data", e);
            MyLog.getInstance().report(e);
        }
        return new String[0];
    }

    private static String getTestDeviceId(Context context) {
        @SuppressWarnings("HardwareIds")
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return getMD5(androidId);
    }

    private static String getMD5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            return String.format(Locale.US, "%032X", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            MyLog.getInstance().report(e);
        }

        return "";
    }
}
