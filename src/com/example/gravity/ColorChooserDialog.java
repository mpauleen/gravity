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
				r = seekBar.getProgress();
				Toast.makeText(getApplicationContext(),
						"red bar progress:" + r, Toast.LENGTH_SHORT).show();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
				
				MainActivity.r = r;
				MainActivity.g = g;
				MainActivity.b = b;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});

		SeekBar green = (SeekBar) findViewById(R.id.greenBar);
		green.setMax(255);
		green.setProgress(MainActivity.g);
		green.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				g = seekBar.getProgress();
				Toast.makeText(getApplicationContext(),
						"green bar progress:" + g, Toast.LENGTH_SHORT).show();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
				
				MainActivity.r = r;
				MainActivity.g = g;
				MainActivity.b = b;

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		SeekBar blue = (SeekBar) findViewById(R.id.blueBar);
		blue.setMax(255);
		blue.setProgress(MainActivity.b);
		blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				b = seekBar.getProgress();
				Toast.makeText(getApplicationContext(),
						"blue bar progress:" + b, Toast.LENGTH_SHORT).show();
				s.setColors(r, g, b);
				s.postInvalidate();
				s.draw(new Canvas());
				
				MainActivity.r = r;
				MainActivity.g = g;
				MainActivity.b = b;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

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
		intent.putExtra("r", r);
		System.out.println(r);
		intent.putExtra("g", g);
		System.out.println(g);
		intent.putExtra("b", b);
		System.out.println(b);
		setResult(RESULT_OK, intent);
		finish();
	}

}
