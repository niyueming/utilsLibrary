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
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import net.nym.utilslibrary.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niyueming
 * @date 2017-06-06
 * @time 14:44
 */

public class PickerUtils {

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    public static void setDatePickerDividerColor(DatePicker datePicker) {
        // Divider changing:

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#c7000b")));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    public static void setPickerDividerColor(ViewGroup viewGroup) {
        List<NumberPicker> npList = findNumberPicker(viewGroup);
        try {
            for (NumberPicker np : npList) {
                if (np == null){
                    continue;
                }
                Field[] pickerFields = NumberPicker.class.getDeclaredFields();
                for (Field pf : pickerFields) {
                    if (pf.getName().equals("mSelectionDivider")) {
                        pf.setAccessible(true);
                        pf.set(np, new ColorDrawable(Color.parseColor("#c7000b")));
                        break;
                    }
                }
            }
        } catch (IllegalArgumentException | Resources.NotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private static List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                }else if (child instanceof TimePicker){
                    try {
                        Field field = TimePicker.class.getDeclaredField("mDelegate");
                        field.setAccessible(true);
                        Object delegate = field.get(child);
                        if(delegate!= null){
                            Class clazz = Class.forName("android.widget.TimePickerSpinnerDelegate");
                            if (clazz != null){
                                Field[] pickerFields = clazz.getDeclaredFields();
                                for (Field pf : pickerFields) {
                                    if (pf.getName().equals("mHourSpinner")
                                            | pf.getName().equals("mMinuteSpinner")
                                            | pf.getName().equals("mAmPmSpinner")
                                            ) {
                                        pf.setAccessible(true);
                                        npList.add((NumberPicker) pf.get(child));
                                    }
                                }
                            }

                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                        Field[] pickerFields = TimePicker.class.getDeclaredFields();
                        for (Field pf : pickerFields) {
                            if (pf.getName().equals("mHourSpinner")
                                    | pf.getName().equals("mMinuteSpinner")
                                    | pf.getName().equals("mAmPmSpinner")
                                    ) {
                                pf.setAccessible(true);
                                try {
                                    npList.add((NumberPicker) pf.get(child));
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (child instanceof ViewGroup) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        npList.addAll(result);
                    }
                }
            }
        }
        return npList;
    }

    /**
     * 调整FrameLayout大小
     * @param tp
     */
    public static void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker2(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     * @param viewGroup
     * @return
     */
    private static List<NumberPicker> findNumberPicker2(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0;i<viewGroup.getChildCount();i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /*
		 * 调整numberpicker大小
		 */
    private static void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ContextUtils.dip2px(np.getContext(),48)
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(ContextUtils.dip2px(np.getContext(),3), 0, ContextUtils.dip2px(np.getContext(),3), 0);
        np.setLayoutParams(params);
    }
}
