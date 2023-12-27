package com.ashera.coordinatorlayout;
// start - imports
import java.util.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.*;
import android.widget.*;
import android.view.View.*;

import com.ashera.widget.BaseHasWidgets;

import android.annotation.SuppressLint;

import com.ashera.core.IFragment;
import com.ashera.widget.bus.*;
import com.ashera.converter.*;
import com.ashera.widget.bus.Event.*;
import com.ashera.widget.*;
import com.ashera.widget.IWidgetLifeCycleListener.*;
import com.ashera.layout.*;

import android.graphics.Canvas;
import android.widget.*;
import androidx.core.view.*;
import android.view.*;

import static com.ashera.widget.IWidget.*;
//end - imports

public class CoordinatorLayoutImpl extends BaseHasWidgets {
	//start - body
	public final static String LOCAL_NAME = "androidx.coordinatorlayout.widget.CoordinatorLayout"; 
	public final static String GROUP_NAME = "androidx.coordinatorlayout.widget.CoordinatorLayout";
	private androidx.coordinatorlayout.widget.CoordinatorLayout coordinatorLayout;
	

	
		@SuppressLint("NewApi")
		final static class InsetEdge extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("none",  0x0);
				mapping.put("top",  0x30);
				mapping.put("bottom",  0x50);
				mapping.put("left",  0x03);
				mapping.put("right",  0x05);
				mapping.put("start",  0x00800003);
				mapping.put("end",  0x00800005);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
		@SuppressLint("NewApi")
		final static class DodgeInsetEdge  extends AbstractBitFlagConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("none", 0x0);
				mapping.put("top", 0x30);
				mapping.put("bottom", 0x50);
				mapping.put("left", 0x03);
				mapping.put("right", 0x05);
				mapping.put("start", 0x00800003);
				mapping.put("end", 0x00800005);
				mapping.put("all", 0x77);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
	@Override
	public void loadAttributes(String localName) {
		ViewGroupImpl.register(localName);

		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("keylines").withType("array").withArrayType("int"));
	
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_gravity").withType("gravity").forChild());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_behavior").withType("string").forChild());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_anchor").withType("id").forChild());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_keyline").withType("int").forChild());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_anchorGravity").withType("gravity").forChild());
				ConverterFactory.register("androidx.coordinatorlayout.widget.CoordinatorLayout.insetEdge", new InsetEdge());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_insetEdge").withType("androidx.coordinatorlayout.widget.CoordinatorLayout.insetEdge").forChild());
				ConverterFactory.register("androidx.coordinatorlayout.widget.CoordinatorLayout.dodgeInsetEdge", new DodgeInsetEdge());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("layout_dodgeInsetEdges").withType("androidx.coordinatorlayout.widget.CoordinatorLayout.dodgeInsetEdge").forChild());
	}
	
	public CoordinatorLayoutImpl() {
		super(GROUP_NAME, LOCAL_NAME);
	}
	public  CoordinatorLayoutImpl(String localname) {
		super(GROUP_NAME, localname);
	}
	public  CoordinatorLayoutImpl(String groupName, String localname) {
		super(groupName, localname);
	}

	@Override
	public IWidget newInstance() {
		return new CoordinatorLayoutImpl(groupName, localName);
	}
	
	@SuppressLint("NewApi")
	@Override
	public void create(IFragment fragment, Map<String, Object> params) {
		super.create(fragment, params);
Context context = (Context) fragment.getRootActivity();
	Object systemStyle = params.get("systemStyle");
	Object systemAndroidAttrStyle = params.get("systemAndroidAttrStyle");
	
	if (systemStyle == null && systemAndroidAttrStyle == null) {
		coordinatorLayout = new CoordinatorLayoutExt(context);
	} else {
		int defStyleAttr = 0;
		int defStyleRes = 0;
		
		if (systemStyle != null) {
			defStyleRes = context.getResources().getIdentifier((String) systemStyle, "style", context.getPackageName());	
		}
		
		if (systemAndroidAttrStyle != null) {
			defStyleAttr = context.getResources().getIdentifier((String) systemAndroidAttrStyle, "attr", "android");	
		}
		
		if (defStyleRes == 0) {
			coordinatorLayout = new CoordinatorLayoutExt(context, null, defStyleAttr);	
		} else {
			coordinatorLayout = new CoordinatorLayoutExt(context, null, defStyleAttr, defStyleRes);
		}
		
	}

		
		nativeCreate(params);
		
		
		ViewGroupImpl.registerCommandConveter(this);
	}

	@Override
	public Object asWidget() {
		return coordinatorLayout;
	}

	@Override
	public boolean remove(IWidget w) {		
		boolean remove = super.remove(w);
		coordinatorLayout.removeView((View) w.asWidget());
		return remove;
	}
	
	@Override
    public boolean remove(int index) {
		IWidget widget = widgets.get(index);
        boolean remove = super.remove(index);

        if (index + 1 <= coordinatorLayout.getChildCount()) {
            coordinatorLayout.removeViewAt(index);
        }    
        return remove;
    }
	
	@Override
	public void add(IWidget w, int index) {
		if (index != -2) {
			View view = (View) w.asWidget();
			createLayoutParams(view);
			    if (index == -1) {
			        coordinatorLayout.addView(view);
			    } else {
			        coordinatorLayout.addView(view, index);
			    }
		}
		
		ViewGroupImpl.nativeAddView(asNativeWidget(), w.asNativeWidget());
		super.add(w, index);
	}
	
	private void createLayoutParams(View view) {
		androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams layoutParams = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) view.getLayoutParams();
		
		layoutParams = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) view.getLayoutParams();
		if (layoutParams == null) {
			layoutParams = new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(-2, -2);
			view.setLayoutParams(layoutParams);
		}  else {
			layoutParams.height = -2;
			layoutParams.width = -2;
		}
	}
	
	private androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams getLayoutParams(View view) {
		return (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) view.getLayoutParams();		
	}
	
	@SuppressLint("NewApi")
	@Override
	public void setChildAttribute(IWidget w, WidgetAttribute key, String strValue, Object objValue) {
		View view = (View) w.asWidget();
		androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams layoutParams = getLayoutParams(view);
		ViewGroupImpl.setChildAttribute(w, key, objValue, layoutParams);		
		
		switch (key.getAttributeName()) {
		case "layout_width":
			layoutParams.width = (int) objValue;
			break;	
		case "layout_height":
			layoutParams.height = (int) objValue;
			break;
			case "layout_gravity": {
				
							layoutParams.gravity = ((int)objValue);
				
			}
			break;
			case "layout_behavior": {
				
							setBehavior(layoutParams, objValue);
				
			}
			break;
			case "layout_anchor": {
				
							layoutParams.setAnchorId((int)objValue);
				
			}
			break;
			case "layout_keyline": {
				
							layoutParams.keyline = ((int)objValue);
				
			}
			break;
			case "layout_anchorGravity": {
				
							layoutParams.anchorGravity = ((int)objValue);
				
			}
			break;
			case "layout_insetEdge": {
				
							layoutParams.insetEdge = ((int)objValue);
				
			}
			break;
			case "layout_dodgeInsetEdges": {
				
							layoutParams.dodgeInsetEdges = ((int)objValue);
				
			}
			break;
		default:
			break;
		}
		
		
		view.setLayoutParams(layoutParams);		
	}
	
	@SuppressLint("NewApi")
	@Override
	public Object getChildAttribute(IWidget w, WidgetAttribute key) {
		Object attributeValue = ViewGroupImpl.getChildAttribute(w, key);		
		if (attributeValue != null) {
			return attributeValue;
		}
		View view = (View) w.asWidget();
		androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams layoutParams = getLayoutParams(view);

		switch (key.getAttributeName()) {
		case "layout_width":
			return layoutParams.width;
		case "layout_height":
			return layoutParams.height;
			case "layout_gravity": {
return layoutParams.gravity;			}


			case "layout_anchor": {
return layoutParams.getAnchorId();			}

			case "layout_keyline": {
return layoutParams.keyline;			}

			case "layout_anchorGravity": {
return layoutParams.anchorGravity;			}

			case "layout_insetEdge": {
return layoutParams.insetEdge;			}

			case "layout_dodgeInsetEdges": {
return layoutParams.dodgeInsetEdges;			}

		}
		
		return null;

	}
	
		
	public class CoordinatorLayoutExt extends androidx.coordinatorlayout.widget.CoordinatorLayout implements ILifeCycleDecorator, com.ashera.widget.IMaxDimension{
		private MeasureEvent measureFinished = new MeasureEvent();
		private OnLayoutEvent onLayoutEvent = new OnLayoutEvent();
		public IWidget getWidget() {
			return CoordinatorLayoutImpl.this;
		}
		private int mMaxWidth = -1;
		private int mMaxHeight = -1;
		@Override
		public void setMaxWidth(int width) {
			mMaxWidth = width;
		}
		@Override
		public void setMaxHeight(int height) {
			mMaxHeight = height;
		}
		@Override
		public int getMaxWidth() {
			return mMaxWidth;
		}
		@Override
		public int getMaxHeight() {
			return mMaxHeight;
		}

		public CoordinatorLayoutExt(Context context, android.util.AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }

	    public CoordinatorLayoutExt(Context context, android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
	    	super(context, attrs, defStyleAttr);
	    }
		public CoordinatorLayoutExt(Context context) {
			super(context);
			
		}
		
		@Override
		public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

			if(mMaxWidth > 0) {
	        	widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
	        }
	        if(mMaxHeight > 0) {
	            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);

	        }

	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
			    measureFinished.setWidth(getMeasuredWidth());
			    measureFinished.setHeight(getMeasuredHeight());
				listener.eventOccurred(EventId.measureFinished, measureFinished);
			}
		}
		
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			super.onLayout(changed, l, t, r, b);
			ViewImpl.nativeMakeFrame(asNativeWidget(), l, t, r, b);
			replayBufferedEvents();
			
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
				onLayoutEvent.setB(b);
				onLayoutEvent.setL(l);
				onLayoutEvent.setR(r);
				onLayoutEvent.setT(t);
				onLayoutEvent.setChanged(changed);
				listener.eventOccurred(EventId.onLayout, onLayoutEvent);
			}
			
			if (isInvalidateOnFrameChange() && isInitialised()) {
				CoordinatorLayoutImpl.this.invalidate();
			}
		}	
		
		@Override
		public void onDraw(Canvas canvas) {
			Runnable runnable = () -> super.onDraw(canvas);
			executeMethodListeners("onDraw", runnable, canvas);
		}

		@Override
		public void draw(Canvas canvas) {
			Runnable runnable = () -> super.draw(canvas);
			executeMethodListeners("draw", runnable, canvas);
		}

		@SuppressLint("WrongCall")
		@Override
		public void execute(String method, Object... args) {
			switch (method) {
				case "onDraw":
					setOnMethodCalled(true);
					super.onDraw((Canvas) args[0]);
					break;

				case "draw":
					setOnMethodCalled(true);
					super.draw((Canvas) args[0]);
					break;

				default:
					break;
			}
		}

		public void updateMeasuredDimension(int width, int height) {
			setMeasuredDimension(width, height);
		}


		@Override
		public ILifeCycleDecorator newInstance(IWidget widget) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(WidgetAttribute widgetAttribute,
				String strValue, Object objValue) {
			throw new UnsupportedOperationException();
		}		
		

		@Override
		public List<String> getMethods() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void initialized() {
			throw new UnsupportedOperationException();
		}
		
        @Override
        public Object getAttribute(WidgetAttribute widgetAttribute) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void drawableStateChanged() {
        	super.drawableStateChanged();
        	ViewImpl.drawableStateChanged(CoordinatorLayoutImpl.this);
        }
        
    	public void setState0(float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState0(int value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState0(double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	
    	public void setState0(Float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState0(Integer value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState0(Double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState0(Object value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 0, value);
    	}
    	public void setState1(float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState1(int value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState1(double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	
    	public void setState1(Float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState1(Integer value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState1(Double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState1(Object value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 1, value);
    	}
    	public void setState2(float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState2(int value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState2(double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	
    	public void setState2(Float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState2(Integer value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState2(Double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState2(Object value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 2, value);
    	}
    	public void setState3(float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState3(int value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState3(double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	
    	public void setState3(Float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState3(Integer value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState3(Double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState3(Object value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 3, value);
    	}
    	public void setState4(float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	public void setState4(int value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	public void setState4(double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	
    	public void setState4(Float value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	public void setState4(Integer value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	public void setState4(Double value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
    	public void setState4(Object value) {
    		ViewImpl.setState(CoordinatorLayoutImpl.this, 4, value);
    	}
        	public void state0() {
        		ViewImpl.state(CoordinatorLayoutImpl.this, 0);
        	}
        	public void state1() {
        		ViewImpl.state(CoordinatorLayoutImpl.this, 1);
        	}
        	public void state2() {
        		ViewImpl.state(CoordinatorLayoutImpl.this, 2);
        	}
        	public void state3() {
        		ViewImpl.state(CoordinatorLayoutImpl.this, 3);
        	}
        	public void state4() {
        		ViewImpl.state(CoordinatorLayoutImpl.this, 4);
        	}
                        
        public void stateYes() {
        	ViewImpl.stateYes(CoordinatorLayoutImpl.this);
        	
        }
        
        public void stateNo() {
        	ViewImpl.stateNo(CoordinatorLayoutImpl.this);
        }
	}
	@Override
	public Class getViewClass() {
		return CoordinatorLayoutExt.class;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void setAttribute(WidgetAttribute key, String strValue, Object objValue, ILifeCycleDecorator decorator) {
		ViewGroupImpl.setAttribute(this, key, strValue, objValue, decorator);
		Object nativeWidget = asNativeWidget();
		switch (key.getAttributeName()) {
			case "keylines": {


		setKeyLines(objValue);



			}
			break;
		default:
			break;
		}
		
	}
	
	@Override
	@SuppressLint("NewApi")
	public Object getAttribute(WidgetAttribute key, ILifeCycleDecorator decorator) {
		Object attributeValue = ViewGroupImpl.getAttribute(this, key, decorator);
		if (attributeValue != null) {
			return attributeValue;
		}
		Object nativeWidget = asNativeWidget();
		switch (key.getAttributeName()) {
		}
		return null;
	}


	@Override
    public Object asNativeWidget() {
        return coordinatorLayout;
    }
    private void nativeCreate(Map<String, Object> params) {
    }
    
    @Override
    public void requestLayout() {
    	if (isInitialised()) {
    		ViewImpl.requestLayout(this, asNativeWidget());
    	}
    }
    
    @Override
    public void invalidate() {
    	if (isInitialised()) {
    		ViewImpl.invalidate(this, asNativeWidget());
    	}
    }
    
	

	@Override
	public void setId(String id){
		if (id != null && !id.equals("")){
			super.setId(id);
			coordinatorLayout.setId((int) quickConvert(id, "id"));
		}
	}
	
    

	
private CoordinatorLayoutCommandBuilder builder;
private CoordinatorLayoutBean bean;
public Object getPlugin(String plugin) {
	return WidgetFactory.getAttributable(plugin).newInstance(this);
}
public CoordinatorLayoutBean getBean() {
	if (bean == null) {
		bean = new CoordinatorLayoutBean();
	}
	return bean;
}
public CoordinatorLayoutCommandBuilder getBuilder() {
	if (builder == null) {
		builder = new CoordinatorLayoutCommandBuilder();
	}
	return builder;
}


public  class CoordinatorLayoutCommandBuilder extends com.ashera.layout.ViewGroupImpl.ViewGroupCommandBuilder <CoordinatorLayoutCommandBuilder> {
    public CoordinatorLayoutCommandBuilder() {
	}
	
	public CoordinatorLayoutCommandBuilder execute(boolean setter) {
		if (setter) {
			executeCommand(command, null, IWidget.COMMAND_EXEC_SETTER_METHOD);
			getFragment().remeasure();
		}
		executeCommand(command, null, IWidget.COMMAND_EXEC_GETTER_METHOD);
return this;	}

public CoordinatorLayoutCommandBuilder setKeylines(String value) {
	Map<String, Object> attrs = initCommand("keylines");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
}
public class CoordinatorLayoutBean extends com.ashera.layout.ViewGroupImpl.ViewGroupBean{
		public CoordinatorLayoutBean() {
			super(CoordinatorLayoutImpl.this);
		}
public void setKeylines(String value) {
	getBuilder().reset().setKeylines(value).execute(true);
}

}


private CoordinatorLayoutCommandParamsBuilder paramsBuilder;
private CoordinatorLayoutParamsBean paramsBean;

public CoordinatorLayoutParamsBean getParamsBean() {
	if (paramsBean == null) {
		paramsBean = new CoordinatorLayoutParamsBean();
	}
	return paramsBean;
}
public CoordinatorLayoutCommandParamsBuilder getParamsBuilder() {
	if (paramsBuilder == null) {
		paramsBuilder = new CoordinatorLayoutCommandParamsBuilder();
	}
	return paramsBuilder;
}



public class CoordinatorLayoutParamsBean extends com.ashera.layout.ViewGroupImpl.ViewGroupParamsBean{
public Object getLayoutGravity(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutGravity().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutGravity();
}
public void setLayoutGravity(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutGravity(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public void setLayoutBehavior(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutBehavior(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public Object getLayoutAnchor(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutAnchor().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutAnchor();
}
public void setLayoutAnchor(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutAnchor(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public Object getLayoutKeyline(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutKeyline().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutKeyline();
}
public void setLayoutKeyline(IWidget w, int value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutKeyline(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public Object getLayoutAnchorGravity(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutAnchorGravity().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutAnchorGravity();
}
public void setLayoutAnchorGravity(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutAnchorGravity(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public Object getLayoutInsetEdge(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutInsetEdge().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutInsetEdge();
}
public void setLayoutInsetEdge(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutInsetEdge(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

public Object getLayoutDodgeInsetEdges(IWidget w) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	java.util.Map<String, Object> command = getParamsBuilder().reset().tryGetLayoutDodgeInsetEdges().getCommand();
	
	layoutParams.put("layoutParams", command);
	w.executeCommand(layoutParams, null, COMMAND_EXEC_GETTER_METHOD); 
	return getParamsBuilder().getLayoutDodgeInsetEdges();
}
public void setLayoutDodgeInsetEdges(IWidget w, String value) {
	java.util.Map<String, Object> layoutParams = new java.util.HashMap<String, Object>();
	layoutParams.put("layoutParams", getParamsBuilder().reset().setLayoutDodgeInsetEdges(value).getCommand());
	w.executeCommand(layoutParams, null, COMMAND_EXEC_SETTER_METHOD);
	w.getFragment().remeasure();
}

}





public class CoordinatorLayoutCommandParamsBuilder extends com.ashera.layout.ViewGroupImpl.ViewGroupCommandParamsBuilder<CoordinatorLayoutCommandParamsBuilder>{
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutGravity() {
	Map<String, Object> attrs = initCommand("layout_gravity");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutGravity() {
	Map<String, Object> attrs = initCommand("layout_gravity");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutGravity(String value) {
	Map<String, Object> attrs = initCommand("layout_gravity");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder setLayoutBehavior(String value) {
	Map<String, Object> attrs = initCommand("layout_behavior");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutAnchor() {
	Map<String, Object> attrs = initCommand("layout_anchor");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutAnchor() {
	Map<String, Object> attrs = initCommand("layout_anchor");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutAnchor(String value) {
	Map<String, Object> attrs = initCommand("layout_anchor");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutKeyline() {
	Map<String, Object> attrs = initCommand("layout_keyline");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutKeyline() {
	Map<String, Object> attrs = initCommand("layout_keyline");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutKeyline(int value) {
	Map<String, Object> attrs = initCommand("layout_keyline");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutAnchorGravity() {
	Map<String, Object> attrs = initCommand("layout_anchorGravity");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutAnchorGravity() {
	Map<String, Object> attrs = initCommand("layout_anchorGravity");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutAnchorGravity(String value) {
	Map<String, Object> attrs = initCommand("layout_anchorGravity");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutInsetEdge() {
	Map<String, Object> attrs = initCommand("layout_insetEdge");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutInsetEdge() {
	Map<String, Object> attrs = initCommand("layout_insetEdge");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutInsetEdge(String value) {
	Map<String, Object> attrs = initCommand("layout_insetEdge");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public CoordinatorLayoutCommandParamsBuilder tryGetLayoutDodgeInsetEdges() {
	Map<String, Object> attrs = initCommand("layout_dodgeInsetEdges");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getLayoutDodgeInsetEdges() {
	Map<String, Object> attrs = initCommand("layout_dodgeInsetEdges");
	return attrs.get("commandReturnValue");
}
public CoordinatorLayoutCommandParamsBuilder setLayoutDodgeInsetEdges(String value) {
	Map<String, Object> attrs = initCommand("layout_dodgeInsetEdges");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
}

	//end - body

	//start - codecopy
	private void setBehavior(androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams layoutParams, Object objValue) {
		Object behavior = WidgetFactory.getBehavior((String) objValue);
		if (behavior == null) {
			throw new RuntimeException("Call WidgetFactory.registerBehavior for " + (String) objValue);
		}
		layoutParams.setBehavior((androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior) behavior);
		
	}
	
	
	private void setKeyLines(Object objValue) {
		List<Object> values = (List<Object>) objValue;
		int[] keyLines = new int[values.size()];
		
		for (int i = 0; i < values.size(); i++) {
			int obj = (int) values.get(i);
			keyLines[i] = (int) (obj * coordinatorLayout.getContext().getResources().getDisplayMetrics().density);
		}
		
		setMyKeyLines(keyLines);
	}
	//end - codecopy

	private void setMyKeyLines(int[] keyLines) {
		setFieldUsingReflection(coordinatorLayout, "mKeylines", keyLines);
	}
}
