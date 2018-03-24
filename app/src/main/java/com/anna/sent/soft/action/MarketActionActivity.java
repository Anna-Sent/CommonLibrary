package com.anna.sent.soft.action;

import android.content.Intent;
import android.net.Uri;

public abstract class MarketActionActivity extends ActionActivity {
    @Override
    protected final Intent getAction() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + getAppName()));
        return intent;
    }

    protected abstract String getAppName();
}
