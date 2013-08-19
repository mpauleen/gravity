package com.example.gravity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class ColorChooserDialog extends Activity {

	Intent intent = new Intent();
	int r = MainActivity.r;
	int g = MainActivity.g;
	int b = MainActivity.b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Pick Color");
		setContentView(R.layout.colorchooser);
		
		
		
		final MySurfaceView s = (MySurfaceView) findViewById(R.id.mySurfaceView1);
		s.setColors(r, g, b);
		s.postInvalidate();
		s.draw(new Canvas());

		MainActivity.r = r;
		MainActivity.g = g;
		MainActivity.b = b;
		
		SeekBar red = (SeekBar) findViewById(R.id.redBar);
		red.setMax(255);
		red.setProgress(MainActivity.r);
		red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(),
						"red bar progress:" + r, Toast.LENGTH_SHORT).show();

//				
//				MainActivity.r = r;
//				MainActivity.g = g;
//				MainActivity.b = b;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				r = seekBar.getProgress();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
				}
		});

		SeekBar green = (SeekBar) findViewById(R.id.greenBar);
		green.setMax(255);
		green.setProgress(MainActivity.g);
		green.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(),
						"green bar progress:" + g, Toast.LENGTH_SHORT).show();

//				
//				MainActivity.r = r;
//				MainActivity.g = g;
//				MainActivity.b = b;

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				g = seekBar.getProgress();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
			}
		});

		SeekBar blue = (SeekBar) findViewById(R.id.blueBar);
		blue.setMax(255);
		blue.setProgress(MainActivity.b);
		blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(),
						"blue bar progress:" + b, Toast.LENGTH_SHORT).show();

//				
//				MainActivity.r = r;
//				MainActivity.g = g;
//				MainActivity.b = b;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				b = seekBar.getProgress();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
			}
		});

		// dialogButton.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// dialog.dismiss();
		// }
		// });

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		final MySurfaceView s = (MySurfaceView) findViewById(R.id.mySurfaceView1);
		s.setColors(r, g, b);
		s.postInvalidate();
		s.draw(new Canvas());

		MainActivity.r = r;
		MainActivity.g = g;
		MainActivity.b = b;
		finish();
	}

}
