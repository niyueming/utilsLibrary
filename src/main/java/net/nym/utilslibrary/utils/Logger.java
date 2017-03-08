/*
 * Copyright (c) 2017  Ni YueMing<niyueming@163.com>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */

package net.nym.utilslibrary.utils;

import android.util.Log;

import junit.framework.Assert;

import net.nym.utilslibrary.BuildConfig;

import java.util.MissingFormatArgumentException;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 16:13
 */

public class Logger {
    public static final String TAG = "Logger";
    public static final String DEBUG_TAG = "debug.net.nym.library";
    private static int LOG_LEVEL = Log.VERBOSE;

    static {
        if (BuildConfig.DEBUG){
            LOG_LEVEL = Log.VERBOSE;
        }else {
            LOG_LEVEL = Log.WARN;
        }
    }

    /**
     *
     * @param level {@link Log#VERBOSE,Log#DEBUG,Log#INFO,Log#WARN,Log#ERROR,Log#ASSERT}
     */
    public static void setLevel(int level)
    {
        LOG_LEVEL = level;
    }


    public static void v(String msg, Object... args) {
        try {
            if (LOG_LEVEL <= Log.VERBOSE){
                android.util.Log.v(DEBUG_TAG, String.format(msg, args));
            }
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "my.Log", e);
            android.util.Log.w(TAG, msg);
        }
    }


    public static void d(String msg, Object... args) {
        try {
            if (LOG_LEVEL <= Log.DEBUG){
                android.util.Log.d(DEBUG_TAG, String.format(msg, args));
            }
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "my.Log", e);
            android.util.Log.d(TAG, msg);
        }
    }

    public static void i(String msg, Object... args) {
        try {
            if (LOG_LEVEL <= Log.INFO){
                android.util.Log.i(DEBUG_TAG, String.format(msg, args));
            }
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "my.Log", e);
            android.util.Log.i(TAG, msg);
        }
    }

    public static void w(String msg, Object... args) {
        try {
            if (LOG_LEVEL <= Log.WARN){
                android.util.Log.w(DEBUG_TAG, String.format(msg, args));
            }
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "my.Log", e);
            android.util.Log.w(TAG, msg);
        }
    }

    public static void e(String msg, Object... args) {
        try {
            if (LOG_LEVEL <= Log.ERROR){
                android.util.Log.e(DEBUG_TAG, String.format(msg, args));
            }
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "my.Log", e);
            android.util.Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable t) {
        if (LOG_LEVEL <= Log.ERROR){
            android.util.Log.e(TAG, msg, t);
        }
    }

    public static void println(String msg,Object... args)
    {
        if(LOG_LEVEL <= Log.DEBUG){
            System.out.println(String.format(msg, args));
        }
    }
}
