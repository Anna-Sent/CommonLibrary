package com.anna.sent.soft.strategy.statesaver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.anna.sent.soft.strategy.activity.FragmentKeeper;
import com.anna.sent.soft.strategy.activity.StrategyFragmentActivity;
import com.anna.sent.soft.strategy.lifecycle.LifecycleStrategy;

import java.util.ArrayList;

public abstract class StateSaverBaseActivity extends StrategyFragmentActivity
        implements StateSaver, FragmentKeeper, LifecycleStrategy.Listener {
    private static final String TAG = "moo";
    private static final boolean DEBUG = false;

    private String wrapMsg(String msg) {
        return getClass().getSimpleName() + ": " + msg;
    }

    @Override
    public void log(String msg) {
        if (DEBUG) {
            Log.d(TAG, wrapMsg(msg));
        }
    }

    @SuppressWarnings("unused")
    private void log(String msg, boolean debug) {
        if (DEBUG && debug) {
            Log.d(TAG, wrapMsg(msg));
        }
    }

    private final ArrayList<StateSaver> mStateSavers = new ArrayList<StateSaver>();

    protected abstract void setupTheme();

    protected abstract void setupLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupTheme();

        super.onCreate(savedInstanceState);

        setupLanguage();

        setViews(savedInstanceState);

        if (savedInstanceState != null) {
            // log("restore 1");
            restoreState(savedInstanceState);
        } else {
            savedInstanceState = getIntent().getExtras();
            if (savedInstanceState != null) {
                // log("restore 2");
                restoreState(savedInstanceState);
            }
        }
    }

    @Override
    protected void addStrategies() {
        super.addStrategies();
        addStrategy(new LifecycleStrategy(this));
    }

    @Override
    protected final void onSaveInstanceState(Bundle outState) {
        // log("onSaveInstanceState", true);
        beforeOnSaveInstanceState();
        saveActivityState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setViews(Bundle savedInstanceState) {
    }

    @Override
    public void restoreState(Bundle state) {
    }

    @Override
    public final void saveState(Bundle state) {
        // log("save state");
        saveActivityState(state);
        saveFragmentState(state);
    }

    protected void beforeOnSaveInstanceState() {
    }

    protected void saveActivityState(Bundle state) {
    }

    private void saveFragmentState(Bundle state) {
        for (int i = 0; i < mStateSavers.size(); ++i) {
            mStateSavers.get(i).saveState(state);
            // log("save fragment state " + i);
        }
    }

    @Override
    public void attach(Fragment f) {
        super.attach(f);
        if (f instanceof StateSaver) {
            StateSaver ss = (StateSaver) f;
            if (!mStateSavers.contains(ss)) {
                mStateSavers.add(ss);
            }
        }
    }

    @Override
    public void detach(Fragment f) {
        super.detach(f);
        if (f instanceof StateSaver) {
            mStateSavers.remove(f);
        }
    }
}