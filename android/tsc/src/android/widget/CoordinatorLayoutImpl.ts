// start - imports

export const enum InsetEdge {
none = "none",
top = "top",
bottom = "bottom",
left = "left",
right = "right",
start = "start",
end = "end",
}
export const enum DodgeInsetEdge {
none = "none",
top = "top",
bottom = "bottom",
left = "left",
right = "right",
start = "start",
end = "end",
all = "all",
}
export const enum BehaviorState {
state_none = "state_none",
state_dragging = "state_dragging",
state_settling = "state_settling",
state_expanded = "state_expanded",
state_collapsed = "state_collapsed",
state_hidden = "state_hidden",
state_half_expanded = "state_half_expanded",
}	
import CommandAttr from '../../widget/CommandAttr';
import IWidget from '../../widget/IWidget';
import ILayoutParam from '../../widget/ILayoutParam';
import {plainToClass, Type, Exclude, Expose, Transform} from "class-transformer";
import {Gravity} from '../../widget/TypeConstants';
import {ITranform, TransformerFactory} from '../../widget/TransformerFactory';
import {Event} from '../../app/Event';
import {MotionEvent} from '../../app/MotionEvent';
import {DragEvent} from '../../app/DragEvent';
import {KeyEvent} from '../../app/KeyEvent';
import { ScopedObject } from '../../app/ScopedObject';
import { Mixin, decorate } from 'ts-mixer';










export class DodgeInsetEdgeTransformer implements ITranform {
    transform(value: any, obj: any, type: number) : any{
        if (type == 1) {
            return value.toString().replace(",", "|");
        } else {
            let strArray:Array<string> = value.toString().split("|");
            
            let valueArr:Array<DodgeInsetEdge> = new Array<DodgeInsetEdge>();
            for (let i =0; i < strArray.length; i++) {
                switch(strArray[i]) {
					case "none":
						valueArr.push(DodgeInsetEdge.none);
                       	break;	
					case "top":
						valueArr.push(DodgeInsetEdge.top);
                       	break;	
					case "bottom":
						valueArr.push(DodgeInsetEdge.bottom);
                       	break;	
					case "left":
						valueArr.push(DodgeInsetEdge.left);
                       	break;	
					case "right":
						valueArr.push(DodgeInsetEdge.right);
                       	break;	
					case "start":
						valueArr.push(DodgeInsetEdge.start);
                       	break;	
					case "end":
						valueArr.push(DodgeInsetEdge.end);
                       	break;	
					case "all":
						valueArr.push(DodgeInsetEdge.all);
                       	break;	
                }
                
            }
            return valueArr;
        }
    }
}











import {ViewGroupImpl_LayoutParams} from './ViewGroupImpl';

// end - imports
import {ViewGroupImpl} from './ViewGroupImpl';
export abstract class CoordinatorLayoutImpl<T> extends ViewGroupImpl<T>{
	//start - body
	static initialize() {
		TransformerFactory.getInstance().register("dodgeInsetEdge", new DodgeInsetEdgeTransformer());
    }	
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "keylines" }))
	keylines!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "childViewsChanged" }))
	childViewsChanged_!:CommandAttr<void>| undefined;

	@decorate(Exclude())
	protected thisPointer: T;	
	protected abstract getThisPointer(): T;
	reset() : T {	
		super.reset();
		this.keylines = undefined;
		this.childViewsChanged_ = undefined;
		return this.thisPointer;
	}
	constructor(id: string, path: string[], event:  string) {
		super(id, path, event);
		this.thisPointer = this.getThisPointer();
	}
	

	public setKeylines(value : string) : T {
		this.resetIfRequired();
		if (this.keylines == null || this.keylines == undefined) {
			this.keylines = new CommandAttr<string>();
		}
		
		this.keylines.setSetter(true);
		this.keylines.setValue(value);
		this.orderSet++;
		this.keylines.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public childViewsChanged() : T {
		this.resetIfRequired();
		if (this.childViewsChanged_ == null || this.childViewsChanged_ == undefined) {
			this.childViewsChanged_ = new CommandAttr<void>();
		}
		
		this.childViewsChanged_.setSetter(true);
		
		this.orderSet++;
		this.childViewsChanged_.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		
	//end - body

}
	
//start - staticinit
export abstract class CoordinatorLayoutImpl_LayoutParams<T> extends ViewGroupImpl_LayoutParams<T> {
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_gravity" }))
	layout_gravity!:CommandAttr<Gravity[]>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior" }))
	layout_behavior!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_anchor" }))
	layout_anchor!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_keyline" }))
	layout_keyline!:CommandAttr<number>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_anchorGravity" }))
	layout_anchorGravity!:CommandAttr<Gravity[]>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_insetEdge" }))
	layout_insetEdge!:CommandAttr<InsetEdge>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_dodgeInsetEdges" }))
	layout_dodgeInsetEdges!:CommandAttr<DodgeInsetEdge[]>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_fitToContents" }))
	layout_behavior_fitToContents!:CommandAttr<boolean>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_peekHeight" }))
	layout_behavior_peekHeight!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_hideable" }))
	layout_behavior_hideable!:CommandAttr<boolean>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_skipCollapsed" }))
	layout_behavior_skipCollapsed!:CommandAttr<boolean>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_draggable" }))
	layout_behavior_draggable!:CommandAttr<boolean>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_halfExpandedRatio" }))
	layout_behavior_halfExpandedRatio!:CommandAttr<number>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_expandedOffset" }))
	layout_behavior_expandedOffset!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_significantVelocityThreshold" }))
	layout_behavior_significantVelocityThreshold!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_state" }))
	layout_behavior_state!:CommandAttr<BehaviorState>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_onStateChanged" }))
	layout_behavior_onStateChanged!:CommandAttr<string>| undefined;
	@decorate(Type(() => CommandAttr))
	@decorate(Expose({ name: "layout_behavior_onSlide" }))
	layout_behavior_onSlide!:CommandAttr<string>| undefined;
	@decorate(Exclude())
	protected thisPointer: T;	
	protected abstract getThisPointer(): T;
	reset() : T {	
		super.reset();
		this.layout_gravity = undefined;
		this.layout_behavior = undefined;
		this.layout_anchor = undefined;
		this.layout_keyline = undefined;
		this.layout_anchorGravity = undefined;
		this.layout_insetEdge = undefined;
		this.layout_dodgeInsetEdges = undefined;
		this.layout_behavior_fitToContents = undefined;
		this.layout_behavior_peekHeight = undefined;
		this.layout_behavior_hideable = undefined;
		this.layout_behavior_skipCollapsed = undefined;
		this.layout_behavior_draggable = undefined;
		this.layout_behavior_halfExpandedRatio = undefined;
		this.layout_behavior_expandedOffset = undefined;
		this.layout_behavior_significantVelocityThreshold = undefined;
		this.layout_behavior_state = undefined;
		this.layout_behavior_onStateChanged = undefined;
		this.layout_behavior_onSlide = undefined;
		return this.thisPointer;
	}
	constructor() {
		super();
		this.thisPointer = this.getThisPointer();
	}
	
	public tryGetLayoutGravity() : T {
		if (this.layout_gravity == null || this.layout_gravity == undefined) {
			this.layout_gravity = new CommandAttr<Gravity[]>()
		}
		
		this.layout_gravity.setGetter(true);
		this.orderGet++;
		this.layout_gravity.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutGravity() : Gravity[] {
		if (this.layout_gravity == null || this.layout_gravity == undefined) {
			this.layout_gravity = new CommandAttr<Gravity[]>();
		}
this.layout_gravity.setTransformer('gravity');		return this.layout_gravity.getCommandReturnValue();
	}
	public setLayoutGravity(...value : Gravity[]) : T {
		if (this.layout_gravity == null || this.layout_gravity == undefined) {
			this.layout_gravity = new CommandAttr<Gravity[]>();
		}
		this.layout_gravity.setSetter(true);
		this.layout_gravity.setValue(value);
		this.orderSet++;
		this.layout_gravity.setOrderSet(this.orderSet);
this.layout_gravity.setTransformer('gravity');		return this.thisPointer;
	}
	public setLayoutBehavior(value : string) : T {
		if (this.layout_behavior == null || this.layout_behavior == undefined) {
			this.layout_behavior = new CommandAttr<string>();
		}
		this.layout_behavior.setSetter(true);
		this.layout_behavior.setValue(value);
		this.orderSet++;
		this.layout_behavior.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public tryGetLayoutAnchor() : T {
		if (this.layout_anchor == null || this.layout_anchor == undefined) {
			this.layout_anchor = new CommandAttr<string>()
		}
		
		this.layout_anchor.setGetter(true);
		this.orderGet++;
		this.layout_anchor.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutAnchor() : string {
		if (this.layout_anchor == null || this.layout_anchor == undefined) {
			this.layout_anchor = new CommandAttr<string>();
		}
		return this.layout_anchor.getCommandReturnValue();
	}
	public setLayoutAnchor(value : string) : T {
		if (this.layout_anchor == null || this.layout_anchor == undefined) {
			this.layout_anchor = new CommandAttr<string>();
		}
		this.layout_anchor.setSetter(true);
		this.layout_anchor.setValue(value);
		this.orderSet++;
		this.layout_anchor.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public tryGetLayoutKeyline() : T {
		if (this.layout_keyline == null || this.layout_keyline == undefined) {
			this.layout_keyline = new CommandAttr<number>()
		}
		
		this.layout_keyline.setGetter(true);
		this.orderGet++;
		this.layout_keyline.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutKeyline() : number {
		if (this.layout_keyline == null || this.layout_keyline == undefined) {
			this.layout_keyline = new CommandAttr<number>();
		}
		return this.layout_keyline.getCommandReturnValue();
	}
	public setLayoutKeyline(value : number) : T {
		if (this.layout_keyline == null || this.layout_keyline == undefined) {
			this.layout_keyline = new CommandAttr<number>();
		}
		this.layout_keyline.setSetter(true);
		this.layout_keyline.setValue(value);
		this.orderSet++;
		this.layout_keyline.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public tryGetLayoutAnchorGravity() : T {
		if (this.layout_anchorGravity == null || this.layout_anchorGravity == undefined) {
			this.layout_anchorGravity = new CommandAttr<Gravity[]>()
		}
		
		this.layout_anchorGravity.setGetter(true);
		this.orderGet++;
		this.layout_anchorGravity.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutAnchorGravity() : Gravity[] {
		if (this.layout_anchorGravity == null || this.layout_anchorGravity == undefined) {
			this.layout_anchorGravity = new CommandAttr<Gravity[]>();
		}
this.layout_anchorGravity.setTransformer('gravity');		return this.layout_anchorGravity.getCommandReturnValue();
	}
	public setLayoutAnchorGravity(...value : Gravity[]) : T {
		if (this.layout_anchorGravity == null || this.layout_anchorGravity == undefined) {
			this.layout_anchorGravity = new CommandAttr<Gravity[]>();
		}
		this.layout_anchorGravity.setSetter(true);
		this.layout_anchorGravity.setValue(value);
		this.orderSet++;
		this.layout_anchorGravity.setOrderSet(this.orderSet);
this.layout_anchorGravity.setTransformer('gravity');		return this.thisPointer;
	}
	public tryGetLayoutInsetEdge() : T {
		if (this.layout_insetEdge == null || this.layout_insetEdge == undefined) {
			this.layout_insetEdge = new CommandAttr<InsetEdge>()
		}
		
		this.layout_insetEdge.setGetter(true);
		this.orderGet++;
		this.layout_insetEdge.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutInsetEdge() : InsetEdge {
		if (this.layout_insetEdge == null || this.layout_insetEdge == undefined) {
			this.layout_insetEdge = new CommandAttr<InsetEdge>();
		}
		return this.layout_insetEdge.getCommandReturnValue();
	}
	public setLayoutInsetEdge(value : InsetEdge) : T {
		if (this.layout_insetEdge == null || this.layout_insetEdge == undefined) {
			this.layout_insetEdge = new CommandAttr<InsetEdge>();
		}
		this.layout_insetEdge.setSetter(true);
		this.layout_insetEdge.setValue(value);
		this.orderSet++;
		this.layout_insetEdge.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public tryGetLayoutDodgeInsetEdges() : T {
		if (this.layout_dodgeInsetEdges == null || this.layout_dodgeInsetEdges == undefined) {
			this.layout_dodgeInsetEdges = new CommandAttr<DodgeInsetEdge[]>()
		}
		
		this.layout_dodgeInsetEdges.setGetter(true);
		this.orderGet++;
		this.layout_dodgeInsetEdges.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getLayoutDodgeInsetEdges() : DodgeInsetEdge[] {
		if (this.layout_dodgeInsetEdges == null || this.layout_dodgeInsetEdges == undefined) {
			this.layout_dodgeInsetEdges = new CommandAttr<DodgeInsetEdge[]>();
		}
this.layout_dodgeInsetEdges.setTransformer('dodgeInsetEdge');		return this.layout_dodgeInsetEdges.getCommandReturnValue();
	}
	public setLayoutDodgeInsetEdges(...value : DodgeInsetEdge[]) : T {
		if (this.layout_dodgeInsetEdges == null || this.layout_dodgeInsetEdges == undefined) {
			this.layout_dodgeInsetEdges = new CommandAttr<DodgeInsetEdge[]>();
		}
		this.layout_dodgeInsetEdges.setSetter(true);
		this.layout_dodgeInsetEdges.setValue(value);
		this.orderSet++;
		this.layout_dodgeInsetEdges.setOrderSet(this.orderSet);
this.layout_dodgeInsetEdges.setTransformer('dodgeInsetEdge');		return this.thisPointer;
	}
	public setLayoutBehavior_fitToContents(value : boolean) : T {
		if (this.layout_behavior_fitToContents == null || this.layout_behavior_fitToContents == undefined) {
			this.layout_behavior_fitToContents = new CommandAttr<boolean>();
		}
		this.layout_behavior_fitToContents.setSetter(true);
		this.layout_behavior_fitToContents.setValue(value);
		this.orderSet++;
		this.layout_behavior_fitToContents.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_peekHeight(value : string) : T {
		if (this.layout_behavior_peekHeight == null || this.layout_behavior_peekHeight == undefined) {
			this.layout_behavior_peekHeight = new CommandAttr<string>();
		}
		this.layout_behavior_peekHeight.setSetter(true);
		this.layout_behavior_peekHeight.setValue(value);
		this.orderSet++;
		this.layout_behavior_peekHeight.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_hideable(value : boolean) : T {
		if (this.layout_behavior_hideable == null || this.layout_behavior_hideable == undefined) {
			this.layout_behavior_hideable = new CommandAttr<boolean>();
		}
		this.layout_behavior_hideable.setSetter(true);
		this.layout_behavior_hideable.setValue(value);
		this.orderSet++;
		this.layout_behavior_hideable.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_skipCollapsed(value : boolean) : T {
		if (this.layout_behavior_skipCollapsed == null || this.layout_behavior_skipCollapsed == undefined) {
			this.layout_behavior_skipCollapsed = new CommandAttr<boolean>();
		}
		this.layout_behavior_skipCollapsed.setSetter(true);
		this.layout_behavior_skipCollapsed.setValue(value);
		this.orderSet++;
		this.layout_behavior_skipCollapsed.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_draggable(value : boolean) : T {
		if (this.layout_behavior_draggable == null || this.layout_behavior_draggable == undefined) {
			this.layout_behavior_draggable = new CommandAttr<boolean>();
		}
		this.layout_behavior_draggable.setSetter(true);
		this.layout_behavior_draggable.setValue(value);
		this.orderSet++;
		this.layout_behavior_draggable.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_halfExpandedRatio(value : number) : T {
		if (this.layout_behavior_halfExpandedRatio == null || this.layout_behavior_halfExpandedRatio == undefined) {
			this.layout_behavior_halfExpandedRatio = new CommandAttr<number>();
		}
		this.layout_behavior_halfExpandedRatio.setSetter(true);
		this.layout_behavior_halfExpandedRatio.setValue(value);
		this.orderSet++;
		this.layout_behavior_halfExpandedRatio.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_expandedOffset(value : string) : T {
		if (this.layout_behavior_expandedOffset == null || this.layout_behavior_expandedOffset == undefined) {
			this.layout_behavior_expandedOffset = new CommandAttr<string>();
		}
		this.layout_behavior_expandedOffset.setSetter(true);
		this.layout_behavior_expandedOffset.setValue(value);
		this.orderSet++;
		this.layout_behavior_expandedOffset.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_significantVelocityThreshold(value : string) : T {
		if (this.layout_behavior_significantVelocityThreshold == null || this.layout_behavior_significantVelocityThreshold == undefined) {
			this.layout_behavior_significantVelocityThreshold = new CommandAttr<string>();
		}
		this.layout_behavior_significantVelocityThreshold.setSetter(true);
		this.layout_behavior_significantVelocityThreshold.setValue(value);
		this.orderSet++;
		this.layout_behavior_significantVelocityThreshold.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_state(value : BehaviorState) : T {
		if (this.layout_behavior_state == null || this.layout_behavior_state == undefined) {
			this.layout_behavior_state = new CommandAttr<BehaviorState>();
		}
		this.layout_behavior_state.setSetter(true);
		this.layout_behavior_state.setValue(value);
		this.orderSet++;
		this.layout_behavior_state.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_onStateChanged(value : string) : T {
		if (this.layout_behavior_onStateChanged == null || this.layout_behavior_onStateChanged == undefined) {
			this.layout_behavior_onStateChanged = new CommandAttr<string>();
		}
		this.layout_behavior_onStateChanged.setSetter(true);
		this.layout_behavior_onStateChanged.setValue(value);
		this.orderSet++;
		this.layout_behavior_onStateChanged.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
	public setLayoutBehavior_onSlide(value : string) : T {
		if (this.layout_behavior_onSlide == null || this.layout_behavior_onSlide == undefined) {
			this.layout_behavior_onSlide = new CommandAttr<string>();
		}
		this.layout_behavior_onSlide.setSetter(true);
		this.layout_behavior_onSlide.setValue(value);
		this.orderSet++;
		this.layout_behavior_onSlide.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
}

export class CoordinatorLayout_LayoutParams extends CoordinatorLayoutImpl_LayoutParams<CoordinatorLayout_LayoutParams> implements ILayoutParam {
    getThisPointer(): CoordinatorLayout_LayoutParams {
        return this;
    }

   	constructor() {
		super();	
	}
}

export class CoordinatorLayout extends CoordinatorLayoutImpl<CoordinatorLayout> implements IWidget{
    getThisPointer(): CoordinatorLayout {
        return this;
    }
    
   	public getClass() {
		return CoordinatorLayout;
	}
	
   	constructor(id: string, path: string[], event: string) {
		super(id, path, event);	
	}
}

CoordinatorLayoutImpl.initialize();
export interface OnStateChangedEvent extends Event{
        //bottomSheet:View;

	        newState:number;

}
export interface OnSlideEvent extends Event{
        //bottomSheet:View;

	        slideOffset:number;

}
export interface OnStateChangedEvent extends Event{
        //bottomSheet:View;

	        newState:number;

}
export interface OnSlideEvent extends Event{
        //bottomSheet:View;

	        slideOffset:number;

}

//end - staticinit
