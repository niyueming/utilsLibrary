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

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 16:30
 */

public class Toaster {

    public static void toasterCustom(View view) {
        if (view == null){
            return;
        }
        Toast toast = new Toast(view.getContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

//    public static void toasterWithDrawable(int stringId, int drawable) {
//        toasterWithDrawable(BaseApplication.getAppContext(), stringId,
//                drawable, Toast.LENGTH_SHORT);
//    }

    public static void toasterWithDrawable(Context context, int stringId, int drawable) {
        toasterWithDrawable(context, stringId,
                drawable, Toast.LENGTH_SHORT);
    }

    public static void toasterWithDrawable(Context context, int stringId,
                                           int drawable, int duration) {
        if (context == null){
            return;
        }
        Toast toast = Toast.makeText(context, stringId, duration);
        ViewGroup view = (ViewGroup) toast.getView();
        ImageView imageview = new ImageView(context);
        imageview.setImageResource(drawable);
        view.addView(imageview, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

//    public static void toasterWithDrawable(String string, int drawable) {
//        toasterWithDrawable(BaseApplication.getAppContext(), string, drawable,
//                Toast.LENGTH_SHORT);
//    }

    public static void toasterWithDrawable(Context context,String string, int drawable) {
        toasterWithDrawable(context, string, drawable,
                Toast.LENGTH_SHORT);
    }

    public static void toasterWithDrawable(Context context, String string,
                                           int drawable, int duration) {
        if (context == null){
            return;
        }
        Toast toast = Toast.makeText(context, string, duration);
        ViewGroup view = (ViewGroup) toast.getView();
        ImageView imageview = new ImageView(context);
        imageview.setImageResource(drawable);
        view.addView(imageview, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /** Print an on-screen message to alert the user */
    public static void toaster(Context context, int stringId, int duration) {
        if (context == null){
            return;
        }
        Toast.makeText(context, stringId, duration).show();
    }

//    public static void toaster(int stringId) {
//        toaster(BaseApplication.getAppContext(), stringId);
//    }

    public static void toaster(Context context, int stringId) {
        toaster(context, stringId, Toast.LENGTH_SHORT);
    }

    /** Print an on-screen message to alert the user */
    public static void toaster(Context context, String string, int duration) {
        if (context == null){
            return;
        }
        Toast.makeText(context, string, duration).show();
    }

//    public static void toaster(String string) {
//        toaster(BaseApplication.getAppContext(), string);
//    }

    public static void toaster(Context context, String string) {
        toaster(context, string, Toast.LENGTH_SHORT);
    }
}
