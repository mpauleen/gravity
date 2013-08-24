/**
 * This application is built upon "GravityRS.java" Copyright (c) 2011 daoki2.
 * 
 * For the portions of the software that were copied from "GravityRS.java" Copyright (c) 2011 daoki2:
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

import android.content.res.Resources;
import android.renderscript.Mesh;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.RenderScriptGL;

@SuppressWarnings("deprecation")
public class GravityRS {
    public static int PART_COUNT = 20000; // Count of particles

    public GravityRS(int partCount) {
    	PART_COUNT = partCount;
    }
    

	boolean multiple = false;
    private Resources mRes;
	private RenderScriptGL mRS;
    private ScriptC_gravity mScript;
    static float r = 0.5f;
    static float g = 0.9f;
    static float b = 0.9f;
    static int i =0;
    
    public void init(RenderScriptGL rs, Resources res, int width, int height) {
        mRS = rs;
        mRes = res;

        ProgramFragmentFixedFunction.Builder pfb = new ProgramFragmentFixedFunction.Builder(rs);
        pfb.setVaryingColor(true);
        rs.bindProgramFragment(pfb.create());
        
        ScriptField_Point points = new ScriptField_Point(mRS, PART_COUNT);
        
        Mesh.AllocationBuilder smb = new Mesh.AllocationBuilder(mRS);
        smb.addVertexAllocation(points.getAllocation());
        smb.addIndexSetType(Mesh.Primitive.POINT);
        Mesh sm = smb.create();

        mScript = new ScriptC_gravity(mRS, mRes, R.raw.gravity);
        mScript.set_partMesh(sm);
        mScript.bind_point(points);
        mRS.bindRootScript(mScript);
        mScript.set_redRS(r);
        mScript.set_greenRS(g);
        mScript.set_blueRS(b);
        
        mScript.invoke_initParticles(); // Initialize Particles
    }
    
    public void setColor(int r, int g, int b, boolean black, boolean hotzones) {
    	float red = (float) r/255;
    	float green = (float) g/255;
    	float blue = (float) b/255;
    	System.out.println(red);
    	System.out.println(green);
    	System.out.println(blue);
    	System.out.println(hotzones);
        mScript.set_redRS(red);
        mScript.set_greenRS(green);
        mScript.set_blueRS(blue);
        mScript.set_blackRS(black);
        mScript.set_hotzones(hotzones);
        System.out.println("Set Color");
    }
    
    public void setGravity (float delta, float acc, boolean wrapped, boolean sensitivity) {
    	mScript.set_acceleration(acc);
    	mScript.set_delta(delta);
    	mScript.set_wrap(wrapped);
    	mScript.set_sensitive(sensitivity);
    	System.out.println(mScript.get_delta());
    	System.out.println(mScript.get_acceleration());
    	System.out.println(wrapped);
    }
    
    public void setWrap (boolean wrap) {
    	mScript.set_wrap(wrap);
    }

    public void newTouchPosition(float x, float y, float p) {
        mScript.set_inX1(x);
        mScript.set_inY1(y);
        mScript.set_p1(p);
        mScript.set_multiple(multiple);

    }
    
    public void newTouchPosition2(float x, float y, float p) {
        mScript.set_inX2(x);
        mScript.set_inY2(y);
        mScript.set_p2(p);
        mScript.set_multiple(multiple);
    }
    
}