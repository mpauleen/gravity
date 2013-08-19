package com.example.gravity;

import android.content.res.Resources;
import android.renderscript.Mesh;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.RenderScriptGL;

@SuppressWarnings("deprecation")
public class GravityRS {
    public static final int PART_COUNT = 40000; // Count of particles

    public GravityRS() {
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
    
    public void setColor(float r, float g, float b) {
        mScript.set_redRS(r);
        mScript.set_greenRS(g);
        mScript.set_blueRS(b);
        System.out.println("Set Color");
    }
    
    

    public void newTouchPosition(float x, float y, float pressure, int id) {
        mScript.set_gTouchX(x);
        mScript.set_gTouchY(y);
    }
    
}