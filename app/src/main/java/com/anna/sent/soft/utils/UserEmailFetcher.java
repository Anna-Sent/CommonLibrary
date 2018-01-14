package com.anna.sent.soft.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;

/**
 * This class uses the AccountManager to get the primary email address of the
 * current user. Necessary permission is <uses-permission
 * android:name="android.permission.GET_ACCOUNTS" />
 */
public class UserEmailFetcher {
    private static final String INVALID_EMAIL = "";

    /**
     * @param context Context of the app.
     * @return email or empty string
     */
    public static String getEmail(Context context) {
        try {
            AccountManager accountManager = AccountManager.get(context);
            Account account = getAccount(accountManager);
            if (account == null) {
                return INVALID_EMAIL;
            } else {
                return account.name;
            }
        } catch (Exception e) {
            return INVALID_EMAIL;
        }
    }

    private static Account getAccount(AccountManager accountManager) {
        @SuppressLint("MissingPermission")
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
