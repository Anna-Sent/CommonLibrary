package com.anna.sent.soft.settings;

import java.util.Map;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

public class SharedPreferencesWrapper implements SharedPreferences {
	private SharedPreferences mSettings;

	public SharedPreferencesWrapper(SharedPreferences settings) {
		mSettings = settings;
	}

	@Override
	public Map<String, ?> getAll() {
		return mSettings.getAll();
	}

	@Override
	public String getString(String key, String defValue) {
		try {
			return mSettings.getString(key, defValue);
		} catch (ClassCastException e) {
			return defValue;
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public Set<String> getStringSet(String key, Set<String> defValues) {
		try {
			return mSettings.getStringSet(key, defValues);
		} catch (ClassCastException e) {
			return defValues;
		}
	}

	@Override
	public int getInt(String key, int defValue) {
		try {
			return mSettings.getInt(key, defValue);
		} catch (ClassCastException e) {
			return defValue;
		}
	}

	@Override
	public long getLong(String key, long defValue) {
		try {
			return mSettings.getLong(key, defValue);
		} catch (ClassCastException e) {
			return defValue;
		}
	}

	@Override
	public float getFloat(String key, float defValue) {
		try {
			return mSettings.getFloat(key, defValue);
		} catch (ClassCastException e) {
			return defValue;
		}
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		try {
			return mSettings.getBoolean(key, defValue);
		} catch (ClassCastException e) {
			return defValue;
		}
	}

	@Override
	public boolean contains(String key) {
		return mSettings.contains(key);
	}

	@Override
	public Editor edit() {
		return mSettings.edit();
	}

	@Override
	public void registerOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
		mSettings.registerOnSharedPreferenceChangeListener(listener);
	}

	@Override
	public void unregisterOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
		mSettings.registerOnSharedPreferenceChangeListener(listener);
	}
}