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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;

import java.util.Calendar;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 17:03
 */

public class Notify {
    /** Message ID Counter **/
    private static int MessageID = 0;

    public static int notifcation(Context context, int notificationTitle, String messageString, int smallIconId, Intent intent) {

        return notifcation(context,context.getString(notificationTitle),messageString,smallIconId,intent);

    }

    public static int notifcation(Context context, String notificationTitle, String messageString,int smallIconId, Intent intent) {

        return notifcation(context,notificationTitle,messageString,smallIconId,intent,MessageID++);

    }


    /**
     * Displays a notification in the notification area of the UI
     * @param context Context from which to create the notification
     * @param messageString The string to display to the user as a message
     * @param smallIconId drawable id
     * @param intent The intent which will start the activity when the user clicks the notification
     * @param notificationTitle The resource reference to the notification title
     * @return messageID
     */
    public static int notifcation(Context context, String notificationTitle, String messageString,int smallIconId, Intent intent,int messageID) {

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);

        long when = System.currentTimeMillis();

        //get the notification title from the application's strings.xml file
        CharSequence contentTitle = notificationTitle;

        //the message that will be displayed as the ticker
        String ticker = contentTitle + " " + messageString;

        //build the pending intent that will start the appropriate activity
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                3, intent,  PendingIntent.FLAG_UPDATE_CURRENT);

        //build the notification
        Builder notificationCompat = new Builder(context);
        notificationCompat.setAutoCancel(true)
                .setContentTitle(contentTitle)
                .setContentIntent(pendingIntent)
                .setContentText(messageString)
                .setTicker(ticker)
                .setWhen(when)
                .setSmallIcon(smallIconId)
                .setDefaults(Notification.DEFAULT_ALL)
        ;

        Notification notification = notificationCompat.build();
        //display the notification
        mNotificationManager.notify(messageID, notification);
        return messageID;

    }
}
