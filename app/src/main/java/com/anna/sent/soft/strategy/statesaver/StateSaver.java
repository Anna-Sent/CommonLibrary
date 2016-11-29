package com.anna.sent.soft.strategy.statesaver;

import android.os.Bundle;

public interface StateSaver {
    void setViews(Bundle savedInstanceState);

    void restoreState(Bundle state);

    void saveState(Bundle state);
}