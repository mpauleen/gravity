/*
 * Copyright (C) 2011-2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: C:\Users\i-ecarbonaro\Desktop\workspace\Gravity\src\com\example\gravity\gravity.rs
 */
package com.android.sample;

import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_gravity extends ScriptC {
    private static final String __rs_resource_name = "gravity";
    // Constructor
    public  ScriptC_gravity(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_gravity(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
        mExportVar_gTouchX = 50f;
        mExportVar_gTouchY = 50f;
    }

    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_MESH;
    private final static int mExportVarIdx_gTouchX = 0;
    private float mExportVar_gTouchX;
    public synchronized void set_gTouchX(float v) {
        setVar(mExportVarIdx_gTouchX, v);
        mExportVar_gTouchX = v;
    }

    public float get_gTouchX() {
        return mExportVar_gTouchX;
    }

    private final static int mExportVarIdx_gTouchY = 1;
    private float mExportVar_gTouchY;
    public synchronized void set_gTouchY(float v) {
        setVar(mExportVarIdx_gTouchY, v);
        mExportVar_gTouchY = v;
    }

    public float get_gTouchY() {
        return mExportVar_gTouchY;
    }

    private final static int mExportVarIdx_point = 2;
    private ScriptField_Point mExportVar_point;
    public void bind_point(ScriptField_Point v) {
        mExportVar_point = v;
        if (v == null) bindAllocation(null, mExportVarIdx_point);
        else bindAllocation(v.getAllocation(), mExportVarIdx_point);
    }

    public ScriptField_Point get_point() {
        return mExportVar_point;
    }

    private final static int mExportVarIdx_partMesh = 3;
    private Mesh mExportVar_partMesh;
    public synchronized void set_partMesh(Mesh v) {
        setVar(mExportVarIdx_partMesh, v);
        mExportVar_partMesh = v;
    }

    public Mesh get_partMesh() {
        return mExportVar_partMesh;
    }

    private final static int mExportFuncIdx_initParticles = 0;
    public void invoke_initParticles() {
        invoke(mExportFuncIdx_initParticles);
    }

}

