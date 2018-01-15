package com.anna.sent.soft.strategy.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public interface Strategy {
    void onCreate(Bundle savedInstanceState);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onRestart();

    void onStart();

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onResume();

    void onPause();

    void onSaveInstanceState(Bundle outState);

    void onStop();

    void onDestroy();

    void onConfigurationChanged(Configuration newConfig);

    boolean onCreateOptionsMenu(android.view.Menu menu);

    boolean onPrepareOptionsMenu(android.view.Menu menu);

    boolean onOptionsItemSelected(android.view.MenuItem item);

    boolean onKeyDown(int keyCode, KeyEvent event);
}
