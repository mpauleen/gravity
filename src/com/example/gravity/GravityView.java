/**
 * This application is built upon "GravityView.java" Copyright (c) 2011 daoki2.
 * 
 * For the portions of the software that were copied from "GravityView.java" Copyright (c) 2011 daoki2:
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * The rights to all modifications, edits, changes, additions, subtractions 
 * and multiplications to the software remain reserved to mpauleen.
 * Copyright (c) 2013 mpauleen.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package com.example.gravity;

import android.content.Context;
import android.content.Intent;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

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
        	if (ev.getPointerCount() == 3) {
 			   Intent intent = new Intent(getContext(), Settings.class);
 				MainActivity.instance.startActivityForResult(intent, 1);
 			   break;
        	} else if(ev.getPointerCount() == 2) {
        		mRender.newTouchPosition2(ev.getX(1), ev.getY(1), ev.getPressure(1)); 
        		mRender.newTouchPosition(ev.getX(0), ev.getY(0), ev.getPressure(0));
        		mRender.multiple = true;
        	} else {
                mRender.newTouchPosition(ev.getX(0), ev.getY(0), ev.getPressure(0));        
                mRender.multiple = false;
        	}
        	 
          break;
        }
        case MotionEvent.ACTION_MOVE: {
        	if(ev.getPointerCount() == 2) {
            mRender.newTouchPosition2(ev.getX(1), ev.getY(1), ev.getPressure(1)); 
            mRender.newTouchPosition(ev.getX(0), ev.getY(0), ev.getPressure(0));        
            mRender.multiple = true;
        	}
        	else {
                mRender.newTouchPosition(ev.getX(0), ev.getY(0), ev.getPressure(0));        
        		mRender.multiple = false;
        	}
            break;
        }
        
        case MotionEvent.ACTION_UP: 
        	if(!MainActivity.persist){
            mRender.newTouchPosition(-1, -1, 0);
            mRender.newTouchPosition2(-1, -1, 0); 
        	}
        	break;
        }
        return true;
    }


}