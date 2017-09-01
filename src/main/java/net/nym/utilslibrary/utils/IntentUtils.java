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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.Locale;

/**
 * @author niyueming
 * @date 2017-05-26
 * @time 17:14
 */

public class IntentUtils {

    public static void callPhone(Context context,String phone){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }

    public static void openQQChat(Context context,String qq){
        try {
            String url=String.format("mqqwpa://im/chat?chat_type=wpa&uin=%s",qq);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            e.printStackTrace();
            Toaster.toaster(context,"请检查是否安装了QQ");
        }
    }
    public static void openWeixinChat(Context context,String weixin){
        try {
//            String url=String.format("weixin://addfriend/%s",weixin);
            String url=String.format("weixin://contacts/profile/%s",weixin);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            e.printStackTrace();
            Toaster.toaster(context,"请检查是否安装了微信");
        }

    }

    public static void openApp(Context context,String packageName,String cls){
        try {

            ComponentName componet = new ComponentName(packageName, cls);
            Intent intent = new Intent();
            intent.setComponent(componet);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            Toaster.toaster(context,"请检查是否已安装该APP");
        }
    }

    public static void openFile(Context context,File file){
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data;
            // 判断版本大于等于7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // "cn.com.firstcare.mobile.aph.fileprovider"即是在清单文件中配置的authorities
                data = FileProvider.getUriForFile(context, "cn.com.firstcare.mobile.aph.fileprovider", file);
                // 给目标应用一个临时授权
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                data = Uri.fromFile(file);
            }
            intent.setDataAndType(data,FileUtils.getMimeType(file) );
            Logger.e("getMimeType=%s",FileUtils.getMimeType(file));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            Toaster.toaster(context,"未搜索到可以打开该文件的app");
        }
    }

    public static void openActivity(Context context,String packageName,String cls){
        try {

            ComponentName componet = new ComponentName(packageName, cls);
            Intent intent = new Intent();
            intent.setComponent(componet);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
