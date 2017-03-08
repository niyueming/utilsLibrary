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

import android.text.TextUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 16:45
 */

public class StringUtils {

    public static String join(Object[] elements, CharSequence separator) {
        return join(Arrays.asList(elements), separator);
    }

    public static String join(Iterable<? extends Object> elements, CharSequence separator) {
        StringBuilder builder = new StringBuilder();

        if (elements != null) {
            Iterator<? extends Object> iter = elements.iterator();
            if (iter.hasNext()) {
                builder.append(String.valueOf(iter.next()));
                while (iter.hasNext()) {
                    builder.append(separator).append(String.valueOf(iter.next()));
                }
            }
        }

        return builder.toString();
    }

    public static int convertToInt(String str) throws NumberFormatException {
        int s, e;
        for (s = 0; s < str.length(); s++)
            if (Character.isDigit(str.charAt(s)))
                break;
        for (e = str.length(); e > 0; e--)
            if (Character.isDigit(str.charAt(e - 1)))
                break;
        if (e > s) {
            try {
                return Integer.parseInt(str.substring(s, e));
            } catch (NumberFormatException ex) {
                throw new NumberFormatException();
            }
        } else {
            throw new NumberFormatException();
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static String subString(String str,int end)
    {
        String result = str;
        if (str.length() > end)
        {
            result = str.substring(0,end);
        }
        return result;
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c).replaceAll(":",": ");
    }

    /**
     * 把字符串的第start位到end位（不包括end）用“*”号代替
     * @param str 要代替的字符串
     * @return
     */

    public static String replaceSubString(String str,int start,int end){
        return replaceSubString(str,start,end,'*');
    }

    public static String replaceSubString(String str,int start,int end,char replacement){
        String sub1="";
        String sub2="";
        StringBuffer sb=new StringBuffer();
        try {
            sub1 = str.substring(0, start);
            sub2 = str.substring(end, str.length());
            sb.append(sub1);
            for(int i=0;i<end -start;i++){
                sb=sb.append(replacement);
            }
            sb.append(sub2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String format(String format,Object... args){
        return String.format(Locale.getDefault(),format,args);
    }
}
