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