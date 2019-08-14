/*
 *  Copyright (C) Y Media Labs, Inc 2018 - All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Written by Y Media Labs
 *
 */

package com.solvvy.sample.logger;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

public final class AppLogger {
    private AppLogger() {
        throw new InstantiationError(AppLogger.class.getSimpleName() + " cannot be instantiated");
    }

    public static void v(@NonNull String tag, @NonNull String msg) {
        if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.v(trimTag(tag), msg);
        }
    }

    private static String trimTag(@NonNull String tag) {
        return tag.substring(0, tag.length() >= 6 ? 6 : tag.length());
    }
}
