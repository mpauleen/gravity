package com.example.gravity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	
    public static final String PREFS_NAME = "MyPrefsFile";
	
    //Default Variables
    static int defR = 128;
    static int defG = 230;
    static int defB = 230;
    static boolean defHZ = false;
    static boolean defBG = true;
    static int defNum = 20000;
    static float defDel = 0.96f;
    static float defAcc = 100.f;
    static boolean defWrap = false;
    static boolean defSen = false;
    static boolean defPer = true;

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
	public static boolean fullCheck = false;
	public static boolean dontAlert = false;
	
	static MainActivity instance;

	private GravityView mView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		mView = new GravityView(this, partCount);
		setContentView(mView);
		instance = this;
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		dontAlert = settings.getBoolean("dontAlert", false);
		delta = settings.getFloat("defDel", 0.96f);
		acc = settings.getFloat("defAcc", 100.f);
		wrap = settings.getBoolean("defWrap", false);
		persist = settings.getBoolean("defPer", true);
		sensitive = settings.getBoolean("defSen", false);
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
		super.onResume();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		MainActivity.dontAlert = settings.getBoolean("dontAlert", false);
		System.out.println("Resume: "+MainActivity.dontAlert);

		mView.resume();
		
	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onPause();	
		mView.pause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
//	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
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

		System.out.println(requestCode);
		if(requestCode == 1) {
			mView.mRender.setColor(r, g, b, black, hotzones);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public static void resetParticle() {
		partCount = defNum;
	}
	


	private void saveColorSet() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
	      editor.putInt("defR", defR);
	      editor.putInt("defG", defG);
	      editor.putInt("defB", defB);
	      editor.putBoolean("defHZ", defHZ);
	      editor.putBoolean("defBG", defBG);
      editor.commit();
	}
	
	private void saveGravitySet() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putFloat("defDel", defDel);
		editor.putFloat("defAcc", defAcc);
		editor.putBoolean("defWrap", defWrap);
		editor.putBoolean("defSen", defSen);
		editor.putBoolean("defPer", defPer);
		editor.commit();

	}
	
	private void savePartNum() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("defNum", defNum);
		editor.commit();

	}
	
	public void saveAll() {
		saveColorSet();
		saveGravitySet();
		savePartNum();
	}
		
	
}
