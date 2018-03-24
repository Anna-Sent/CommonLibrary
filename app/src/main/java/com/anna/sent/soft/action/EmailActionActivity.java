package com.anna.sent.soft.action;

import android.content.Intent;
import android.net.Uri;

public abstract class EmailActionActivity extends ActionActivity {
    @Override
    protected final Intent getAction() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + getEmail()));
        intent.putExtra(Intent.EXTRA_SUBJECT, getSubject());
        intent.putExtra(Intent.EXTRA_TEXT, getText());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    protected abstract String getEmail();

    protected abstract String getSubject();

    protected abstract String getText();
}
