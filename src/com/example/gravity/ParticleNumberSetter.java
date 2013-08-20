package com.example.gravity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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

		partCount.setMax(99999);
		partCount.setProgress(MainActivity.partCount);
		progress = 40000;
		TextView text = (TextView) findViewById(R.id.partNum);
		text.setText(""+MainActivity.partCount);
		partCount.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				progress = seekBar.getProgress();
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
				progress = seekBar.getProgress();
				progress++;
				text.setText(""+progress);
				
			}
		});
		
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
		MainActivity.partCount = progress;
		MainActivity.notifyPartCountChanged();
	}

}
