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
import android.content.Intent;
import android.net.Uri;

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
            Toaster.toaster(context,"请检查是否安装了QQ");
        }
    }
    public static void openWeixinChat(Context context,String weixin){
        try {
//            String url=String.format("weixin://addfriend/%s",weixin);
            String url=String.format("weixin://contacts/profile/%s",weixin);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            Toaster.toaster(context,"请检查是否安装了微信");
        }

    }
}
