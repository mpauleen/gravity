package com.example.gravity;

import android.content.res.Resources;
import android.renderscript.Mesh;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.RenderScriptGL;

@SuppressWarnings("deprecation")
public class GravityRS {
    public static int PART_COUNT = 40000; // Count of particles

    public GravityRS(int partCount) {
    	PART_COUNT = partCount;
    }
    


    private Resources mRes;
	private RenderScriptGL mRS;
    private ScriptC_gravity mScript;
    static float r = 0.5f;
    static float g = 0.9f;
    static float b = 0.9f;

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
    
    public void setColor(int r, int g, int b) {
    	float red = (float) r/255;
    	float green = (float) g/255;
    	float blue = (float) b/255;
    	System.out.println(red);
    	System.out.println(green);
    	System.out.println(blue);
        mScript.set_redRS(red);
        mScript.set_greenRS(green);
        mScript.set_blueRS(blue);
        System.out.println("Set Color");
    }
    
    public void setGravity (float delta, float acc) {
    	mScript.set_acceleration(acc);
    	mScript.set_delta(delta);
    	System.out.println(mScript.get_delta());
    	System.out.println(mScript.get_acceleration());
    }

    public void newTouchPosition(float x, float y, float pressure, int id) {
        mScript.set_gTouchX(x);
        mScript.set_gTouchY(y);
    }
    
}