package com.example.gravity;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	float r = 0.5f;
	float g = 0.9f;
	float b = 0.9f;
	private GravityView mView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mView = new GravityView(this);
		setContentView(mView);
	}

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onResume();
		mView.resume();
	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onPause();
		mView.pause();

		// Runtime.getRuntime().exit(0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.colorchooser);
		dialog.setTitle("Color Picker");
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				mView.mRender.setColor(r, g, b );
				
			}
		});

		
		
		SeekBar red = (SeekBar) dialog.findViewById(R.id.redBar);
		red.setProgress((int) (r*100));
		red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				r = (float) seekBar.getProgress() / 100;
				Toast.makeText(getApplicationContext(),
						"red bar progress:" + r, Toast.LENGTH_SHORT).show();
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
			
		
		
		SeekBar green = (SeekBar) dialog.findViewById(R.id.greenBar);
		green.setProgress((int) (g*100));
		green.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				g = (float) seekBar.getProgress() / 100;
				Toast.makeText(getApplicationContext(),
						"green bar progress:" + g, Toast.LENGTH_SHORT).show();

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

		
		
		SeekBar blue = (SeekBar) dialog.findViewById(R.id.blueBar);
		blue.setProgress((int) (b*100));
		blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				b = (float) seekBar.getProgress() / 100;
				Toast.makeText(getApplicationContext(),
						"blue bar progress:" + b, Toast.LENGTH_SHORT).show();

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
			
		
//		dialogButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});

		dialog.show();
		
		
		
		
		return super.onOptionsItemSelected(item);
	}
}
