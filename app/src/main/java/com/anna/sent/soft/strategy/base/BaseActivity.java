package com.anna.sent.soft.strategy.base;

import android.os.Bundle;

import com.anna.sent.soft.strategy.activity.StrategyFragmentActivity;
import com.anna.sent.soft.strategy.lifecycle.LifecycleStrategy;

public abstract class BaseActivity extends StrategyFragmentActivity
        implements LifecycleStrategy.Listener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupTheme();

        super.onCreate(savedInstanceState);

        setupLanguage();
    }

    @Override
    protected void addStrategies() {
        super.addStrategies();
        addStrategy(new LifecycleStrategy(this));
    }

    protected abstract void setupTheme();

    protected abstract void setupLanguage();
}
