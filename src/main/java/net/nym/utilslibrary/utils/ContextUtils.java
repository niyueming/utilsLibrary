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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.UUID;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 17:15
 */

public class ContextUtils {
    /**
     * api14,4.0及以上
     *
     * @return boolean
     */
    public static boolean isICSOrLater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * api16,4.1及以上
     *
     * @return boolean
     */
    public static boolean isJellyBeanOrLater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * api19,4.4及以上
     *
     * @return boolean
     */
    public static boolean isKitkatOrLater() {
        return Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.KITKAT;
    }

    /**
     * api21,5.0及以上
     *
     * @return boolean
     */
    public static boolean isLollipopOrLater() {
        return Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * api23,6.0及以上
     *
     * @return boolean
     */
    public static boolean isMarshmallowOrLater() {
        return Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.M;
    }

    /**
     * api24,7.0及以上
     *
     * @return boolean
     */
    public static boolean isNougatOrLater() {
        return Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.N;
    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 休眠模式白名单
     *
     * */
    public static void setIgnoringBatteryOptimizations(Context context){
        if(isMarshmallowOrLater()) {
            String packageName = context.getPackageName();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                context.startActivity(intent);
            }
        }
    }

    public static int getVersionCode(Context ctx) {
        int version = 0;
        try {
            version = ctx.getPackageManager().getPackageInfo(ctx.getApplicationInfo().packageName, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getVersionName(Context ctx) {
        String version = "";
        try {
            version = ctx.getPackageManager().getPackageInfo(ctx.getApplicationInfo().packageName, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取设备ID
     *
     * @return 设备ID
     */
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Returns the unique device ID, for example, the IMEI for GSM and the MEID
     * or ESN for CDMA phones. Return null if device ID is not available.
     *
     * <p>Requires Permission:
     *   {@link android.Manifest.permission#READ_PHONE_STATE READ_PHONE_STATE}
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId() + "";
    }

    public static String getUUID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + telephonyManager.getDeviceId();
        tmSerial = "" + telephonyManager.getSimSerialNumber();
        androidId = "" + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString() ;
    }

    public static <T extends View> T findViewById(@NonNull View parent, @IdRes int id){
        View view = parent.findViewById(id);
        return view == null ? null : (T)view;
    }

    /**
     * 获取屏幕大小
     *
     * @return DisplayMetrics
     * @see DisplayMetrics
     * @see WindowManager
     */
    public static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((WindowManager) context.getSystemService(Activity.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * @param metaKey   meta-data里的name
     * @return meta-data的value
     *
     * */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return apiKey;
    }
}
