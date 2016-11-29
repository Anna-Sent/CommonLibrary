package com.anna.sent.soft.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

/**
 * This class uses the AccountManager to get the primary email address of the
 * current user. Necessary permission is <uses-permission
 * android:name="android.permission.GET_ACCOUNTS" />
 */
public class UserEmailFetcher {
    private static final String INVALID_EMAIL = "";

    /**
     * @param context
     * @return email or empty string
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static String getEmail(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                AccountManager accountManager = AccountManager.get(context);
                Account account = getAccount(accountManager);
                if (account == null) {
                    return INVALID_EMAIL;
                } else {
                    return account.name;
                }
            } else {
                return INVALID_EMAIL;
            }
        } catch (Exception e) {
            return INVALID_EMAIL;
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }

        return account;
    }
}