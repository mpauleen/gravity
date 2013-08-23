package com.example.gravity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	public static int r = 128;
	public static int g = 230;
	public static int b = 230;
	public static int partCount = 20000;
	public static float delta = 0.96f;
	public static float acc = 100.f;
	public static boolean wrap = false;
	public static boolean black = true;
	public static boolean persist = true;
	public static boolean sensitive = false;
	public static boolean hotzones = false;
	static MainActivity instance;
	
	private GravityView mView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		mView = new GravityView(this, partCount);
		setContentView(mView);
		
		instance = this;
	}
	
	public static void notifyPartCountChanged() {
		instance.mView = new GravityView(instance, partCount);
		instance.setContentView(instance.mView);
	}
	
	public static void notifyGravityChanged(){
		instance.mView.mRender.setGravity(delta, acc, wrap, sensitive);
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
		Intent intent = new Intent(this, Settings.class);
		startActivityForResult(intent, 1);
		
		
		return super.onOptionsItemSelected(item);
		

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		System.out.println(requestCode);
		if(requestCode == 1) {
			mView.mRender.setColor(r, g, b, black, hotzones);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public static void resetParticle() {
		partCount = 40000;
	}
	
	public static void resetGravity(){
		acc = 100f;
		delta = 0.96f;
		wrap = false;
		persist = true;
		sensitive = false;
		
	}
	
	public static void resetColor() {
		r = 128;
		g = 230;
		b = 230;
		hotzones = false;
	}
	
	public static void resetAll() {
		resetColor();
		resetGravity();
		resetParticle();
		instance.mView = new GravityView(instance, partCount);
		instance.setContentView(instance.mView);
	}
	
	
}
