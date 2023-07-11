package com.ashera.coordinatorlayout;

import com.ashera.widget.WidgetFactory;

public class CoordinatorLayoutPlugin  {
    public static void initPlugin() {
    	//start - widgets
        WidgetFactory.register(new com.ashera.coordinatorlayout.CoordinatorLayoutImpl());
        //end - widgets
    }
}
