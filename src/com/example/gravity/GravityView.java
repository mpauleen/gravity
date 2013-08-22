package com.example.gravity;

import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScript;
import android.renderscript.RenderScriptGL;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.MotionEvent;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class GravityView extends RSSurfaceView {
	
	int particleCount;
    public GravityView(Context context, int particleCount) {
        super(context);
        this.particleCount = particleCount;
    }
        
    private RenderScriptGL mRS;
    public GravityRS mRender;

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        super.surfaceChanged(holder, format, w, h);
        if (mRS == null) {
            RenderScriptGL.SurfaceConfig sc = new RenderScriptGL.SurfaceConfig();
            mRS = createRenderScriptGL(sc);
            mRS.setSurface(holder, w, h);

            mRender = new GravityRS(particleCount);
            mRender.init(mRS, getResources(), w, h);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mRS != null) {
            mRS = null;
            destroyRenderScriptGL();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        mRender.newTouchPosition(ev.getX(0), ev.getY(0));        
        
        
        // get pointer index from the event object
        int pointerIndex = ev.getActionIndex();

        // get pointer ID
        int pointerId = ev.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = ev.getActionMasked();
        
        switch (maskedAction) {

        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN: {
          // We have a new pointer. Lets add it to the list of pointers
        	if(ev.getPointerCount() > 1) {
            mRender.newTouchPosition2(ev.getX(1), ev.getY(1));  
            mRender.multiple = true;
        	}
        	else
        		mRender.multiple = false;
          break;
        }
        case MotionEvent.ACTION_MOVE: {
        	if(ev.getPointerCount() > 1) {
            mRender.newTouchPosition2(ev.getX(1), ev.getY(1)); 
            mRender.multiple = true;
        	}
        	else
        		mRender.multiple = false;
            break;
        }
        
        case MotionEvent.ACTION_UP: 
        	if(!MainActivity.persist){
            mRender.newTouchPosition(-1, -1);
            mRender.newTouchPosition2(-1, -1); 
        	}
        	break;
        }
        return true;
    }
}