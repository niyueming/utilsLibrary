/*
 * Copyright (c) 2018  Ni YueMing<niyueming@163.com>
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
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;

import java.util.List;

/**
 * @author niyueming
 * @date 2018-01-26
 * @time 12:47
 */
@RequiresApi(api = Build.VERSION_CODES.N_MR1)
public class ShortcutUtils {

    private ShortcutUtils(){

    }

    public static void addDynamicShortcuts(Context context, List<ShortcutInfo> list){
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.addDynamicShortcuts(list);

    }

    public static void setDynamicShortcuts(Context context,List<ShortcutInfo> list){
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.setDynamicShortcuts(list);
    }

    public static void updateShortcuts(Context context, List<ShortcutInfo> list){
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.updateShortcuts(list);
    }

    /**
     *
     * @param context
     * @param list shortcut ids
     */
    public static void removeDynamicShortcuts(Context context, List<String> list){
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.removeDynamicShortcuts(list);

    }

    public static void removeAllDynamicShortcuts(Context context){
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.removeAllDynamicShortcuts();
    }

    public static ShortcutInfo buildShortcutInfo(Context context, String shortcutId, String label, @DrawableRes int icon, Intent intent){
        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortcutId)
                .setShortLabel(label)
                .setLongLabel(label)
//                .setRank(3)//弹出的排序,越大离应用图标越远,静态的比动态的离应用图标近
                .setIcon(Icon.createWithResource(context, icon))
                .setIntent(intent)//设置一个intent就是点击启动的页面
//                .setIntents(new Intent[]{//设置多个intent时,将构建回退栈,最后一个是点击启动的页面
//                        new Intent(),new Intent()
//                })
                .build();
        return shortcutInfo;
    }

    public static ShortcutInfo buildShortcutInfo(Context context, String shortcutId, String label, @DrawableRes int icon, Intent[] intents){
        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortcutId)
                .setShortLabel(label)
                .setLongLabel(label)
//                .setRank(3)//弹出的排序,越大离应用图标越远,静态的比动态的离应用图标近
                .setIcon(Icon.createWithResource(context, icon))
//                .setIntent(intent)//设置一个intent就是点击启动的页面
                .setIntents(intents)
                .build();
        return shortcutInfo;
    }

}
