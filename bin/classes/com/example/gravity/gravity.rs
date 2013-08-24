/**
 * This application is built upon "gravity.rs" Copyright (c) 2011 daoki2.
 * 
 * For the portions of the software that were copied from "gravity.rs" Copyright (c) 2011 daoki2:
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

#pragma version(1)
#pragma rs java_package_name(com.example.gravity)
#pragma stateFragment(parent)

#include "rs_graphics.rsh"

static int newPart = 0;
static int initialized = 0;

float inX1 = -1.f;
float inY1 = -1.f;

int gTouchX1 = -1;
int gTouchY1 = -1;

long t1, t2;

float inX2 = -1.f;
float inY2 = -1.f;

float p1 = .5f;
float p2 = .5f;
float delta = 0.96f;
float acceleration = 100.f;
bool sensitive = true;

int gTouchX2 = -1;
int gTouchY2 = -1;

int current = 1;
int frame1;
int frame2;


bool wrap = false;
bool multiple = false;

uchar4 c;
float redRS = 0.5f;
float greenRS = 0.9f;
float blueRS = 0.9f;
bool blackRS = true;
bool hotzones = false;


typedef struct __attribute__((packed, aligned(4))) Point {
	float2 delta;
	float2 position;
	uchar4 color;
} Point_t;

Point_t *point;

rs_mesh partMesh;

/**
 * Initialize the particles
 */

void initParticles() {
	int size = rsAllocationGetDimX(rsGetAllocation(point));
	float width = rsgGetWidth();
	float height = rsgGetHeight();
	c = rsPackColorTo8888(redRS, greenRS, blueRS);
	Point_t *p = point;
	for (int i = 0; i < size; i++) {
		p->position.x = rsRand(width);
		p->position.y = rsRand(height);
		p->delta.x = 0;
		p->delta.y = 0;
		p->color = c;
		p++;
	}
	initialized = 1;
}

/**
 * root() is called every time a frame refresh occurs
 */
int root() {
	if(hotzones)
		c = rsPackColorTo8888(1, greenRS, blueRS);
	else
		c = rsPackColorTo8888(redRS, greenRS, blueRS);
	gTouchX1 = (int) inX1;
	gTouchY1 = (int) inY1;
	int width = rsgGetWidth();
	int height = rsgGetHeight();
//hotzones
//Background Switch 
	if (blackRS) {
		rsgClearColor(0.0f, 0.0f, 0.0f, 1.f);
	} else {
		rsgClearColor(1.0f, 1.0f, 1.0f, 1.f);
	}
	int size = rsAllocationGetDimX(rsGetAllocation(point));
	Point_t *p = point;
	float temp;
	if (gTouchX1 > width){
		temp = gTouchX1;
		gTouchX1 = gTouchY1;
		gTouchY1 = temp;
	}
	if (gTouchY1 > height) {
		temp = gTouchX1;
		gTouchX1 = gTouchY1;
		gTouchY1 = temp;
	}
	else {
		if (gTouchX1 < 0 && gTouchX1 != -1) {
			temp = gTouchX1;
			gTouchX1 = gTouchY1;
			gTouchY1 = temp;
		}
		if (gTouchY1 < 0 && gTouchY1 != -1) {

			temp = gTouchX1;
			gTouchX1 = gTouchY1;
			gTouchY1 = temp;
		}
	}
//	rsDebug("multiple: ", multiple);
	if (multiple) {
		if(hotzones)
			c = rsPackColorTo8888(1, greenRS, blueRS);
// If wrap true
		//TODO Multiple-Wrap
		gTouchX2 = (int) inX2;
		gTouchY2 = (int) inY2;
		
		if(abs(gTouchX2-gTouchX1) < 10 && abs(gTouchY2-gTouchY1) < 10)
			multiple = false;
		if (gTouchX2 > width){
			gTouchY2 = temp;
			gTouchX2 = gTouchY2;
			temp = gTouchX2;
		}

		if (gTouchX2 > width){
			temp = gTouchX2;
			gTouchX2 = gTouchY2;
			gTouchY2 = temp;
		}
		if (gTouchY2 > height) {
			temp = gTouchX2;
			gTouchX2 = gTouchY2;
			gTouchY2 = temp;
		}
		else {
			if (gTouchX2 < 0 && gTouchX2 != -1) {
				temp = gTouchX2;
				gTouchX2 = gTouchY2;
				gTouchY2 = temp;
			}
			if (gTouchY2 < 0 && gTouchY2 != -1) {
				temp = gTouchX2;
				gTouchX2 = gTouchY2;
				gTouchY2 = temp;
			}
		}
		if (wrap) {
			for (int i = 0; i < size; i++) {
				float diff_x = gTouchX1 - p->position.x;
				float diff_y = gTouchY1 - p->position.y;
				float dist = (diff_x * diff_x + diff_y * diff_y);
				if (gTouchX1 != -1) {
					float acc;
					if (sensitive) {
						acc = p1*2*acceleration / dist;
					} else {
						acc = acceleration / dist;
					}

					
					float acc_x = acc * diff_x;
					float acc_y = acc * diff_y;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;
				}

				p->position.x += p->delta.x;
				p->position.y += p->delta.y;

				float diff_x2 = gTouchX2 - p->position.x;
				float diff_y2 = gTouchY2 - p->position.y;
				float dist2 = (diff_x2 * diff_x2 + diff_y2 * diff_y2);
				float acc;
				if (gTouchX2 != -1) {
					if (sensitive) {
						acc = p2 * 2 * acceleration / dist2;
					} else {
						acc = acceleration / dist2;
					}
					float acc_x = acc * diff_x2;
					float acc_y = acc * diff_y2;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;
				}
				
				p->position.x += p->delta.x;
				p->position.y += p->delta.y;
				
				
				if (hotzones) {
					float dist1color = (float) (dist) / 40000 + .1;
					float dist2color = (float) (dist2) / 40000 + .1;
					if (dist2 < 36000 && dist < 36000) {
						c = rsPackColorTo8888(.7, greenRS, blueRS);

						if (dist2color < .7)
							c = rsPackColorTo8888(dist2color, greenRS, blueRS);
						if (dist1color < .7 && dist2color > dist1color)
							c = rsPackColorTo8888(dist1color, greenRS, blueRS);

					} else if (dist2 < 36000) {
						c = rsPackColorTo8888(dist2color, greenRS, blueRS);
					} else if (dist < 36000) {
						c = rsPackColorTo8888(dist1color, greenRS, blueRS);
					} 
//					else
//						c = rsPackColorTo8888(1, greenRS, blueRS);
				}
				p->color = c;

				if (p->position.x > width)
					p->position.x = 0;
				else if (p->position.x < 0)
					p->position.x = width;

				if (p->position.y > height)
					p->position.y = 0;
				else if (p->position.y < 0)
					p->position.y = height;
				p++;
			}
		}
		// If no wrap
		else {
		//TODO Multiple-Bounce

			for (int i = 0; i < size; i++) {
				float diff_x = gTouchX1 - p->position.x;
				float diff_y = gTouchY1 - p->position.y;
				float dist = (diff_x * diff_x + diff_y * diff_y);
				if (gTouchX1 != -1) {
					float acc;
					if (sensitive) {
						acc = p1*2*acceleration / dist;
					} else {
						acc = acceleration / dist;
					}

					
					float acc_x = acc * diff_x;
					float acc_y = acc * diff_y;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;
				}

				p->position.x += p->delta.x;
				p->position.y += p->delta.y;

				float diff_x2 = gTouchX2 - p->position.x;
				float diff_y2 = gTouchY2 - p->position.y;
				float dist2 = (diff_x2 * diff_x2 + diff_y2 * diff_y2);
				float acc;
				if (gTouchX2 != -1) {
					if (sensitive) {
						acc = p2 * 2 * acceleration / dist2;
					} else {
						acc = acceleration / dist2;
					}
					float acc_x = acc * diff_x2;
					float acc_y = acc * diff_y2;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;
				}
				
				p->position.x += p->delta.x;
				p->position.y += p->delta.y;
				if (hotzones){
					float dist1color = (float) (dist) / 40000 + .1;
					float dist2color = (float) (dist2) / 40000 + .1;
					if (dist2 < 36000 && dist < 36000) {
						c = rsPackColorTo8888(.7, greenRS, blueRS);

						if (dist2color < .7)
							c = rsPackColorTo8888(dist2color, greenRS, blueRS);
						if (dist1color < .7 && dist2color > dist1color)
							c = rsPackColorTo8888(dist1color, greenRS, blueRS);

					} else if (dist2 < 36000) {
						c = rsPackColorTo8888(dist2color, greenRS, blueRS);
					} else if (dist < 36000) {
						c = rsPackColorTo8888(dist1color, greenRS, blueRS);
					} else
						c = rsPackColorTo8888(1, greenRS, blueRS);
				}
				p->color = c;

//		Bounce
				if (p->position.x > width) {
					p->position.x = width;
					p->delta.x = 0 - p->delta.x;
				} else if (p->position.x < 0) {
					p->position.x = 0;
					p->delta.x = 0 - p->delta.x;
				}

				if (p->position.y > height) {
					p->position.y = height;
					p->delta.y = 0 - p->delta.y;
				} else if (p->position.y < 0) {
					p->position.y = 0;
					p->delta.y = 0 - p->delta.y;
				}
				p++;

			}
		}
	} else {
// If wrap true
		if (wrap) {
		//Single Wrap	
		
			for (int i = 0; i < size; i++) {
				float diff_x = gTouchX1 - p->position.x;
				float diff_y = gTouchY1 - p->position.y;
				float dist = (diff_x * diff_x + diff_y * diff_y);
				if (gTouchX1 != -1) {
					float acc;
					if (sensitive) {
						acc = p1*2*acceleration / dist;
					} else {
						acc = acceleration / dist;
					}
//					rsDebug("acc", acc);
					float acc_x = acc * diff_x;
					float acc_y = acc * diff_y;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;
				}

				p->position.x += p->delta.x;
				p->position.y += p->delta.y;
				if (hotzones) {
					c = rsPackColorTo8888(1, greenRS, blueRS);
					if (dist < 36000 && gTouchX1 != -1) {
						c = rsPackColorTo8888((float) (dist) / 40000 + .1,
								greenRS, blueRS);
					}
				}
				
				p->color = c;

				if (p->position.x > width) {
					p->position.x = 0;
//					if (p->delta.x == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}
				else if (p->position.x < 0) {
					p->position.x = width;
//					if (p->delta.x == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}

				if (p->position.y > height) {
					p->position.y = 0;
//					if (p->delta.y == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}
				else if (p->position.y < 0) {
					p->position.y = height;
//					if (p->delta.y == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}
				p++;
			}
		}
		// If no wrap
		else {
		//Single Bounce
			float acc;
			float accel = p1 * 2.f * (float) acceleration;
			float diff_x;
			float diff_y;
			float dist;
			for (int i = 0; i < size; i++) {

				if (gTouchX1 != -1) {
					diff_x = gTouchX1 - p->position.x;
					diff_y = gTouchY1 - p->position.y;
					dist = (diff_x * diff_x + diff_y * diff_y);
					float acc;
					if (sensitive) {
						acc = accel / (diff_x * diff_x + diff_y * diff_y);
					} else {
						acc = acceleration / (diff_x * diff_x + diff_y * diff_y);
					}
					float acc_x = acc * diff_x;
					float acc_y = acc * diff_y;

					p->delta.x += acc_x;
					p->delta.y += acc_y;

					// This is friction
					p->delta.x *= delta;
					p->delta.y *= delta;

				}

				p->position.x += p->delta.x;
				p->position.y += p->delta.y;
//				rsDebug("delta", (float)dist/10000);
				
				if (hotzones) {
					c = rsPackColorTo8888(1, greenRS, blueRS);
					if (dist < 36000 && gTouchX1 != -1) {
						c = rsPackColorTo8888((float) (dist) / 40000 + .1,
								greenRS, blueRS);
					}
				}
//					c = rsPackColorTo8888(redRS, greenRS, blueRS);
				p->color = c;

//		Bounce

				if (p->position.x > width) {
					p->position.x = width;
					p->delta.x = 0 - p->delta.x;
//					if(p->delta.x == 0){
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				} else if (p->position.x < 0) {
					p->position.x = 0;
					p->delta.x = 0 - p->delta.x;
//					if (p->delta.x == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}

				if (p->position.y > height) {
					p->position.y = height;
					p->delta.y = 0 - p->delta.y;
//					if (p->delta.y == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				} else if (p->position.y < 0) {
					p->position.y = 0;
					p->delta.y = 0 - p->delta.y;
//					if (p->delta.y == 0) {
//						p->position.x = rsRand(width);
//						p->position.y = rsRand(height);
//					}
				}
				p++;
				
			}

		}
	
	}
	rsgDrawMesh(partMesh);
	t1 = rsUptimeMillis();
	rsDebug("time", 1000/(t1-t2));
	t2 = rsUptimeMillis();
	return 1;
}
