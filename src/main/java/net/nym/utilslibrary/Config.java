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

package net.nym.utilslibrary;

/**
 * @author niyueming
 * @date 2017-03-08
 * @time 17:25
 */

public abstract class Config {
    static int DISPLAY_WIDTH;
    static int DISPLAY_HEIGHT;
    static int DISPLAY_DENSITY;

    public int getDisplayWidth(){
        return DISPLAY_WIDTH;
    }

    public int getDisplayHeight(){
        return DISPLAY_HEIGHT;
    }

    public float getDisplayDensity(){
        return DISPLAY_DENSITY;
    }


}
