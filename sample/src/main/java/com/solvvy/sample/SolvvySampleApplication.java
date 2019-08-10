/*
 *  Copyright (C) Y Media Labs, Inc 2018 - All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Written by Y Media Labs
 *
 */

package com.solvvy.sample;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


public final class SolvvySampleApplication extends MultiDexApplication {
    private static final String TAG = "SolvvySampleApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Solvvy Sample onCreate");
    }
}
