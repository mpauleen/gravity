package com.example.gravity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class ColorChooserDialog extends Activity {

	Intent intent = new Intent();
	int r = MainActivity.r;
	int g = MainActivity.g;
	int b = MainActivity.b;
	boolean hotzones = MainActivity.hotzones;

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
		
		final SeekBar red = (SeekBar) findViewById(R.id.redBar);
		red.setMax(255);
		red.setProgress(MainActivity.r);
		if(MainActivity.hotzones)
			red.setEnabled(false);
		red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
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
		
		ToggleButton background = (ToggleButton) findViewById(R.id.toggleBackground);
		background.setChecked(MainActivity.black);
		background.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
					MainActivity.black = true;
				 else
					MainActivity.black = false;
				
				
			}
		});
		
		CheckBox hotzone = (CheckBox) findViewById(R.id.hotzoneCheck);
		hotzone.setChecked(MainActivity.hotzones);
		hotzone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					MainActivity.hotzones = true;
					red.setEnabled(false);
				}
				 else {
					MainActivity.hotzones = false;
					red.setEnabled(true);
				 }
				
			}
		});

		Button colorReset = (Button) findViewById(R.id.resetColor);
		
		colorReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
				
				MainActivity.r = settings.getInt("defR", 128);
				MainActivity.g = settings.getInt("defG", 230);
				MainActivity.b = settings.getInt("defB", 230);
				MainActivity.black = settings.getBoolean("defBG", true);
				MainActivity.hotzones = settings.getBoolean("defHZ", false);
				Intent intent = new Intent(getApplicationContext(),ColorChooserDialog.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				
			}
		});

		Button saveDefaults = (Button) findViewById(R.id.saveColorDef);
		
		saveDefaults.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveColorSet();
				finish();
			}
		});

	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		MainActivity.r = r;
		MainActivity.g = g;
		MainActivity.b = b;
		finish();
	}
	
	private void saveColorSet() {
		SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("defR", r);
		editor.putInt("defG", g);
		editor.putInt("defB", b);
		editor.putBoolean("defBG", MainActivity.black);
		editor.putBoolean("defHZ", MainActivity.hotzones);
		editor.commit();

	}


}
