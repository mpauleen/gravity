package com.example.gravity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView {

	private int r = 200;
	private int g = 0;
	private int b = 0;
	Paint paint;
	
	
	public void setColors(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}


	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		r = 0;
//		g = 0;
//		b = 0;
		paint = new Paint();
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		setBackgroundColor(Color.WHITE);
		paint.setColor(Color.argb(255, r, g, b));
		paint.setStrokeWidth(5);
		System.out.println("Drawed");
		// TODO Auto-generated method stub
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
		canvas.drawCircle(10, 10, 20, paint);
		
		
	}

}
