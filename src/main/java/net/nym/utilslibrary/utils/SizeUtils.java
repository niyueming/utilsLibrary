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

import java.util.Locale;

/**
 * @author niyueming
 * @date 2017-05-26
 * @time 09:43
 */

public class SizeUtils {
    private final static long K = 1024 * 8;
    private final static long M = 1024 * K;

    public static String formatSize(long size){
        if (size/M > 0){
            return String.format(Locale.getDefault(),"%.3fM",((float)size)/M);
        }

        if (size/K > 0){
            return String.format(Locale.getDefault(),"%.3fK",((float)size)/K);
        }else {
            return String.valueOf("1K");
        }

    }

    public static String formatIntSize(long size){
        if (size/M > 0){
            return String.format(Locale.getDefault(),"%dM",size/M);
        }

        return String.format(Locale.getDefault(),"%dK",size/K);

    }
}
