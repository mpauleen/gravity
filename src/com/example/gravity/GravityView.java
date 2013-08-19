package com.example.gravity;

import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScript;
import android.renderscript.RenderScriptGL;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.MotionEvent;

@SuppressWarnings("deprecation")
public class GravityView extends RSSurfaceView {

	int touchTracker = 0;
	int particleCount;
    public GravityView(Context context, int particleCount) {
        super(context);
        this.particleCount = particleCount;
        touchTracker = 0;
        
        Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (touchTracker > 0)
						touchTracker --;
					else {
						try {
						mRender.newTouchPosition(-1, -1, 0, 0);
						} catch (Exception e) {}
					}
					try {
						Thread.sleep(10);
					} catch (Exception e) {}
				}
			}
		});
        t.start();
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
    	
        mRender.newTouchPosition(ev.getX(0), ev.getY(0), ev.getPressure(0), ev.getPointerId(0));        
        return true;
    }
}