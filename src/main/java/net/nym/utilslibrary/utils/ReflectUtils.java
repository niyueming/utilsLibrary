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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author niyueming
 * @date 2017-05-12
 * @time 10:41
 */

public class ReflectUtils {

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        Class clazz = getClass();
        string(sb, clazz,this);

        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

    public static void string(StringBuffer sb, Class clazz,Object object) {
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            string(sb,clazz.getSuperclass(),object);
        }
        try {
            for (Field item : clazz.getDeclaredFields()) {

                if (Modifier.toString(item.getModifiers()).contains("static")){
                    //不要static修饰的属性
                    continue;
                }
                boolean accessFlag = item.isAccessible();
                /**
                 * 设置是否有权限访问反射类中的私有属性的
                 * */
                item.setAccessible(true);
                sb.append(item.getName()).append(":").append(item.get(object) + "").append(",");
                item.setAccessible(accessFlag);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
