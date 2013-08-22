package com.example.gravity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

public class ChangeGravity extends Activity {

	float acc = MainActivity.acc;
	float deltaNum = MainActivity.delta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Set Gravity");
		setContentView(R.layout.activity_change_gravity);
		
		SeekBar acceleration = (SeekBar) findViewById(R.id.accBar);
		acceleration.setMax(140);
		acceleration.setProgress((int)MainActivity.acc - 10);
		acceleration.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				acc = (float) (seekBar.getProgress()+10);
				
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
		delta.setMax(50);
		delta.setProgress((int)(MainActivity.delta*100) - 50);
		delta.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				deltaNum = (50 +(float) seekBar.getProgress())/100;
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
		
		ToggleButton persist = (ToggleButton) findViewById(R.id.persistToggle);
		persist.setChecked(MainActivity.persist);
		persist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
					MainActivity.persist = true;
				 else
					MainActivity.persist = false;
				System.out.println(MainActivity.persist);
			}
			
		});
		
		Button gravityReset = (Button) findViewById(R.id.resetGravity);
		
		gravityReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.wrap = false;
				MainActivity.delta = 0.96f;
				MainActivity.acc = 100.f;
				Intent intent = new Intent(getApplicationContext(),ChangeGravity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				
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
