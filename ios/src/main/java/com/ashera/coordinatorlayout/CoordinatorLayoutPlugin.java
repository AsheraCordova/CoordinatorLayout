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
    }
}
