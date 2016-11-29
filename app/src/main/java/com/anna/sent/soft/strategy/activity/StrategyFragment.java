package com.anna.sent.soft.strategy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anna.sent.soft.strategy.BaseStrategy;
import com.anna.sent.soft.strategy.Strategies;

public class StrategyFragment extends Fragment {
    private Strategies mStrategies = new Strategies();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mStrategies.onActivityResult(requestCode, resultCode, data);
    }

    // no onRestart

    @Override
    public void onStart() {
        super.onStart();
        mStrategies.onStart();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mStrategies.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mStrategies.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mStrategies.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mStrategies.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mStrategies.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStrategies.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mStrategies.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mStrategies.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        mStrategies.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mStrategies.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mStrategies.onKeyDown(keyCode, event);
    }

    private FragmentKeeper mFragmentKeeper;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentKeeper) {
            mFragmentKeeper = (FragmentKeeper) activity;
            mFragmentKeeper.attach(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mFragmentKeeper != null) {
            mFragmentKeeper.detach(this);
            mFragmentKeeper = null;
        }
    }
}