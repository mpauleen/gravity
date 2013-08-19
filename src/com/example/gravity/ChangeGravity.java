package com.example.gravity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ChangeGravity extends Activity {

	float acc = MainActivity.acc;
	float deltaNum = MainActivity.delta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_gravity);
		
		SeekBar acceleration = (SeekBar) findViewById(R.id.accBar);
		acceleration.setMax(200);
		acceleration.setProgress((int)MainActivity.acc);
		acceleration.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				acc = (float) seekBar.getProgress();
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		SeekBar delta = (SeekBar) findViewById(R.id.deltaBar);
		delta.setMax(200);
		delta.setProgress((int)(MainActivity.delta*100));
		delta.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				deltaNum = (float) seekBar.getProgress()/100;
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ToggleButton wrap = (ToggleButton) findViewById(R.id.wrapButton);
		wrap.setChecked(MainActivity.wrap);
		wrap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
					MainActivity.wrap = true;
				 else
					MainActivity.wrap = false;
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_gravity, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MainActivity.acc = acc;
		MainActivity.delta = deltaNum;
		MainActivity.notifyGravityChanged();
		
	}

}
