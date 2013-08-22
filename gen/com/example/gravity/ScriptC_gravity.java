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
 * The source Renderscript file: C:\Users\Michael\git\gravity\src\com\example\gravity\gravity.rs
 */
package com.example.gravity;

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
        mExportVar_inX1 = -1f;
        mExportVar_inY1 = -1f;
        mExportVar_gTouchX1 = -1;
        mExportVar_gTouchY1 = -1;
        mExportVar_inX2 = -1f;
        mExportVar_inY2 = -1f;
        mExportVar_gTouchX2 = -1;
        mExportVar_gTouchY2 = -1;
        mExportVar_current = 1;
        mExportVar_wrap = false;
        mExportVar_multiple = false;
        mExportVar_redRS = 0.5f;
        mExportVar_greenRS = 0.9f;
        mExportVar_blueRS = 0.9f;
        mExportVar_blackRS = true;
        mExportVar_delta = 0.96f;
        mExportVar_acceleration = 100f;
    }

    private FieldPacker __rs_fp_BOOLEAN;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_I32;
    private FieldPacker __rs_fp_MESH;
    private final static int mExportVarIdx_inX1 = 0;
    private float mExportVar_inX1;
    public synchronized void set_inX1(float v) {
        setVar(mExportVarIdx_inX1, v);
        mExportVar_inX1 = v;
    }

    public float get_inX1() {
        return mExportVar_inX1;
    }

    private final static int mExportVarIdx_inY1 = 1;
    private float mExportVar_inY1;
    public synchronized void set_inY1(float v) {
        setVar(mExportVarIdx_inY1, v);
        mExportVar_inY1 = v;
    }

    public float get_inY1() {
        return mExportVar_inY1;
    }

    private final static int mExportVarIdx_gTouchX1 = 2;
    private int mExportVar_gTouchX1;
    public synchronized void set_gTouchX1(int v) {
        setVar(mExportVarIdx_gTouchX1, v);
        mExportVar_gTouchX1 = v;
    }

    public int get_gTouchX1() {
        return mExportVar_gTouchX1;
    }

    private final static int mExportVarIdx_gTouchY1 = 3;
    private int mExportVar_gTouchY1;
    public synchronized void set_gTouchY1(int v) {
        setVar(mExportVarIdx_gTouchY1, v);
        mExportVar_gTouchY1 = v;
    }

    public int get_gTouchY1() {
        return mExportVar_gTouchY1;
    }

    private final static int mExportVarIdx_inX2 = 4;
    private float mExportVar_inX2;
    public synchronized void set_inX2(float v) {
        setVar(mExportVarIdx_inX2, v);
        mExportVar_inX2 = v;
    }

    public float get_inX2() {
        return mExportVar_inX2;
    }

    private final static int mExportVarIdx_inY2 = 5;
    private float mExportVar_inY2;
    public synchronized void set_inY2(float v) {
        setVar(mExportVarIdx_inY2, v);
        mExportVar_inY2 = v;
    }

    public float get_inY2() {
        return mExportVar_inY2;
    }

    private final static int mExportVarIdx_gTouchX2 = 6;
    private int mExportVar_gTouchX2;
    public synchronized void set_gTouchX2(int v) {
        setVar(mExportVarIdx_gTouchX2, v);
        mExportVar_gTouchX2 = v;
    }

    public int get_gTouchX2() {
        return mExportVar_gTouchX2;
    }

    private final static int mExportVarIdx_gTouchY2 = 7;
    private int mExportVar_gTouchY2;
    public synchronized void set_gTouchY2(int v) {
        setVar(mExportVarIdx_gTouchY2, v);
        mExportVar_gTouchY2 = v;
    }

    public int get_gTouchY2() {
        return mExportVar_gTouchY2;
    }

    private final static int mExportVarIdx_current = 8;
    private int mExportVar_current;
    public synchronized void set_current(int v) {
        setVar(mExportVarIdx_current, v);
        mExportVar_current = v;
    }

    public int get_current() {
        return mExportVar_current;
    }

    private final static int mExportVarIdx_frame1 = 9;
    private int mExportVar_frame1;
    public synchronized void set_frame1(int v) {
        setVar(mExportVarIdx_frame1, v);
        mExportVar_frame1 = v;
    }

    public int get_frame1() {
        return mExportVar_frame1;
    }

    private final static int mExportVarIdx_frame2 = 10;
    private int mExportVar_frame2;
    public synchronized void set_frame2(int v) {
        setVar(mExportVarIdx_frame2, v);
        mExportVar_frame2 = v;
    }

    public int get_frame2() {
        return mExportVar_frame2;
    }

    private final static int mExportVarIdx_wrap = 11;
    private boolean mExportVar_wrap;
    public synchronized void set_wrap(boolean v) {
        if (__rs_fp_BOOLEAN!= null) {
            __rs_fp_BOOLEAN.reset();
        } else {
            __rs_fp_BOOLEAN = new FieldPacker(1);
        }
        __rs_fp_BOOLEAN.addBoolean(v);
        setVar(mExportVarIdx_wrap, __rs_fp_BOOLEAN);
        mExportVar_wrap = v;
    }

    public boolean get_wrap() {
        return mExportVar_wrap;
    }

    private final static int mExportVarIdx_multiple = 12;
    private boolean mExportVar_multiple;
    public synchronized void set_multiple(boolean v) {
        if (__rs_fp_BOOLEAN!= null) {
            __rs_fp_BOOLEAN.reset();
        } else {
            __rs_fp_BOOLEAN = new FieldPacker(1);
        }
        __rs_fp_BOOLEAN.addBoolean(v);
        setVar(mExportVarIdx_multiple, __rs_fp_BOOLEAN);
        mExportVar_multiple = v;
    }

    public boolean get_multiple() {
        return mExportVar_multiple;
    }

    private final static int mExportVarIdx_redRS = 13;
    private float mExportVar_redRS;
    public synchronized void set_redRS(float v) {
        setVar(mExportVarIdx_redRS, v);
        mExportVar_redRS = v;
    }

    public float get_redRS() {
        return mExportVar_redRS;
    }

    private final static int mExportVarIdx_greenRS = 14;
    private float mExportVar_greenRS;
    public synchronized void set_greenRS(float v) {
        setVar(mExportVarIdx_greenRS, v);
        mExportVar_greenRS = v;
    }

    public float get_greenRS() {
        return mExportVar_greenRS;
    }

    private final static int mExportVarIdx_blueRS = 15;
    private float mExportVar_blueRS;
    public synchronized void set_blueRS(float v) {
        setVar(mExportVarIdx_blueRS, v);
        mExportVar_blueRS = v;
    }

    public float get_blueRS() {
        return mExportVar_blueRS;
    }

    private final static int mExportVarIdx_blackRS = 16;
    private boolean mExportVar_blackRS;
    public synchronized void set_blackRS(boolean v) {
        if (__rs_fp_BOOLEAN!= null) {
            __rs_fp_BOOLEAN.reset();
        } else {
            __rs_fp_BOOLEAN = new FieldPacker(1);
        }
        __rs_fp_BOOLEAN.addBoolean(v);
        setVar(mExportVarIdx_blackRS, __rs_fp_BOOLEAN);
        mExportVar_blackRS = v;
    }

    public boolean get_blackRS() {
        return mExportVar_blackRS;
    }

    private final static int mExportVarIdx_delta = 17;
    private float mExportVar_delta;
    public synchronized void set_delta(float v) {
        setVar(mExportVarIdx_delta, v);
        mExportVar_delta = v;
    }

    public float get_delta() {
        return mExportVar_delta;
    }

    private final static int mExportVarIdx_acceleration = 18;
    private float mExportVar_acceleration;
    public synchronized void set_acceleration(float v) {
        setVar(mExportVarIdx_acceleration, v);
        mExportVar_acceleration = v;
    }

    public float get_acceleration() {
        return mExportVar_acceleration;
    }

    private final static int mExportVarIdx_point = 19;
    private ScriptField_Point mExportVar_point;
    public void bind_point(ScriptField_Point v) {
        mExportVar_point = v;
        if (v == null) bindAllocation(null, mExportVarIdx_point);
        else bindAllocation(v.getAllocation(), mExportVarIdx_point);
    }

    public ScriptField_Point get_point() {
        return mExportVar_point;
    }

    private final static int mExportVarIdx_partMesh = 20;
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

