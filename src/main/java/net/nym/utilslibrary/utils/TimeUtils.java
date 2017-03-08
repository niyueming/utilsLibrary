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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 16:53
 */

public class TimeUtils {

    /**
     *
     * @param time 毫秒时间差
     * @return 返回00:00:00 格式
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? StringUtils.format("%02d:%02d:%02d", hours, minutes, seconds) : StringUtils.format("%02d:%02d", minutes, seconds);
    }


    /**
     *
     * @param time long型时间，毫秒
     * @param format 如："yyyy-MM-dd HH:mm:ss"
     * @return 返回format格式的时间
     *
     * 时间戳转换成字符窜
     * */
    public static String getDateToString(String format,long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    /**
     * 将字符串转换成时间戳
     * @param format 时间格式
     * @param time 字符串时间
     * @return 时间戳
     * @throws ParseException
     */
    public static long getStringToDate(String format,String time) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(time).getTime();
    }
    /**
     * 返回默认格式"yyyy-MM-dd HH:mm:ss"的时间
     * @param time long型时间，毫秒
     *
     *
     * */
    public static String getDateToString(long time) {
        return getDateToString("yyyy-MM-dd HH:mm:ss",time);
    }

}
