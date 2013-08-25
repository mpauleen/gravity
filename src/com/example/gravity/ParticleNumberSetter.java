package com.example.gravity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ParticleNumberSetter extends Activity {
	public int progress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Set Number of Particles");
		setContentView(R.layout.activity_particle_number_setter);

		SeekBar partCount = (SeekBar) findViewById(R.id.particleNum);

		partCount.setMax(150);
		partCount.setProgress(MainActivity.partCount/500);
		progress = partCount.getProgress()*500;
		TextView text = (TextView) findViewById(R.id.partNum);
		text.setText("" + MainActivity.partCount);
		partCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				progress = (seekBar.getProgress()*500);
				if(progress == 0)
					progress++;	

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				TextView text = (TextView) findViewById(R.id.partNum);
				progress = seekBar.getProgress()*500;
				if(progress == 0)
				progress = 1;
				
				text.setText("" + progress);

			}
		});
		
		Button partNumReset = (Button) findViewById(R.id.restorePartDef);
		
		partNumReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
				
				MainActivity.partCount = settings.getInt("defPartNum", 20000);
				Intent intent = new Intent(getApplicationContext(),ParticleNumberSetter.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				
			}
		});

		Button saveDefaults = (Button) findViewById(R.id.setPartDef);
		
		saveDefaults.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				savePartNum();
				finish();
			}
		});

	}

	private void savePartNum(){
		SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("defPartNum", progress);
		editor.commit();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.particle_number_setter, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("progress"+progress);
		if (MainActivity.partCount != progress) {
			MainActivity.partCount = progress;
			MainActivity.notifyPartCountChanged();
		} else {
			System.out.println("noChange");
			MainActivity.partCount = progress;
		}
		System.out.println("Main Activity"+MainActivity.partCount);
		System.out.println("progress"+progress);
		
	}

}
