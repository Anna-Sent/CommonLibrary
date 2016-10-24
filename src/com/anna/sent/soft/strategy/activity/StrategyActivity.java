package com.anna.sent.soft.strategy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.anna.sent.soft.strategy.BaseStrategy;
import com.anna.sent.soft.strategy.Strategies;

@SuppressLint("Registered")
public class StrategyActivity extends Activity {
	private Strategies mStrategies = new Strategies();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addStrategies();

		mStrategies.onCreate(savedInstanceState);
	}

	protected void addStrategies() {
	}

	protected final void addStrategy(BaseStrategy strategy) {
		mStrategies.addStrategy(strategy);
	}

	protected final BaseStrategy getStrategy(String name) {
		return mStrategies.getStrategy(name);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mStrategies.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mStrategies.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
		mStrategies.onStart();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mStrategies.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mStrategies.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mStrategies.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mStrategies.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		mStrategies.onStop();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		mStrategies.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mStrategies.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (mStrategies.onCreateOptionsMenu(menu)) {
			return true;
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (mStrategies.onPrepareOptionsMenu(menu)) {
			return true;
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mStrategies.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mStrategies.onKeyDown(keyCode, event)) {
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}