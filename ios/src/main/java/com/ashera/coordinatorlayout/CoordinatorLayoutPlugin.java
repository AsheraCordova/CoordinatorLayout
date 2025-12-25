//start - license
/*
 * Copyright (c) 2025 Ashera Cordova
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
//end - license
package com.ashera.coordinatorlayout;

import com.ashera.widget.IBehavior;
import com.ashera.widget.WidgetFactory;

public class CoordinatorLayoutPlugin  {
    public static void initPlugin() {
    	//start - widgets
        WidgetFactory.register(new com.ashera.coordinatorlayout.CoordinatorLayoutImpl());
        //end - widgets
        
        WidgetFactory.registerBehavior("com.google.android.material.behavior.HideBottomViewOnScrollBehavior",
                new IBehavior() {
                    @Override
                    public Object newInstance() {
                        return new com.google.android.material.behavior.HideBottomViewOnScrollBehavior();
                    }
                });
        
        WidgetFactory.registerBehavior("com.google.android.material.bottomsheet.BottomSheetBehavior",
                new IBehavior() {
                    @Override
                    public Object newInstance() {
                        return new com.google.android.material.bottomsheet.BottomSheetBehavior();
                    }
                });
    }
}
