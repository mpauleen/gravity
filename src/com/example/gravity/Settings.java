package com.example.gravity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

public class Settings extends ListActivity {

	static final String[] FRUITS = new String[] { "Pick Color", "Set Number of Particles", "Set Gravity", "Fullscreen", "Reset Default Settings"};

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_LEFT_ICON);

	
	   View checkBoxView = View.inflate(getApplicationContext(), R.layout.fullscreen_box, null);
	   CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.fullBox);
	   checkBox.setChecked(false);
	   checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		   
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			   if (isChecked)
				   MainActivity.dontAlert = true;
			   else
				   MainActivity.dontAlert = false;
		   }
	   });
	   checkBox.setText("Don't show again");
	
	
	setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_settings,FRUITS));
	setTitle("Settings");
	getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_action_action_settings);

	ListView listView = getListView();
	listView.setTextFilterEnabled(true);
	listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		   if(position == 0){
			   Intent intent = new Intent(getApplicationContext(),ColorChooserDialog.class);
			   startActivity(intent);
		   } else if(position == 1){
			   Intent intent = new Intent(getApplicationContext(), ParticleNumberSetter.class);
			   startActivity(intent);
		   } else if(position == 2){
			   Intent intent = new Intent(getApplicationContext(), ChangeGravity.class);
			   startActivity(intent);
		   } else if (position == 3){
			   View checkBoxView = View.inflate(getApplicationContext(), R.layout.fullscreen_box, null);
			   CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.fullBox);
			   checkBox.setChecked(false);
			   checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					   if (isChecked)
						   MainActivity.dontAlert = true;
					   else
						   MainActivity.dontAlert = false;
				   }
			   });
			   checkBox.setText("Don't show again");
			   if (MainActivity.fullCheck){
					MainActivity.instance.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
					MainActivity.instance.getActionBar().show();
					MainActivity.fullCheck = false;
			   } else {
				   if (!MainActivity.dontAlert) {
				   new AlertDialog.Builder(Settings.this)
				   .setTitle("Fullscreen")
				   .setMessage("You can access the settings menu in fullscreen mode with a three-finger press.")
				   .setIcon(android.R.drawable.ic_dialog_info)
				   .setView(checkBoxView)
				   .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

				       public void onClick(DialogInterface dialog, int whichButton) {
				    	   MainActivity.instance.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
				    	   MainActivity.instance.getActionBar().hide();
				    	   MainActivity.fullCheck = true;
				       }})
				    .setNegativeButton("Cancel", null).show();
			   } else {
		    	   MainActivity.instance.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		    	   MainActivity.instance.getActionBar().hide();
		    	   MainActivity.fullCheck = true;	   
			   }
				   
			   }
			   System.out.println("onClick: "+MainActivity.dontAlert);
			   
			      SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
			      SharedPreferences.Editor editor = settings.edit();
			      editor.putBoolean("dontAlert", MainActivity.dontAlert);
		      editor.commit();

			   
		   } else if(position == 4){
			   new AlertDialog.Builder(Settings.this)
			   .setTitle("Reset Defaults")
			   .setMessage("Do you really want to reset all defaults to factory settings?")
			   .setIcon(android.R.drawable.ic_dialog_alert)
			   .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

			       public void onClick(DialogInterface dialog, int whichButton) {
					      SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
					      SharedPreferences.Editor editor = settings.edit();
					      editor.clear();
					      editor.commit();
					      resetAll();
					      
			       }})
			    .setNegativeButton(android.R.string.no, null).show();

		   }
		}
	});

}
	private void resetAll() {
		SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	    MainActivity.r = 128;
		MainActivity.g = 230;
		MainActivity.b = 230;
		MainActivity.partCount = 20000;
		MainActivity.delta = 0.96f;
		MainActivity.acc = 100.f;
		MainActivity.wrap = false;
		MainActivity.black = true;
		MainActivity.persist = true;
		MainActivity.sensitive = false;
		MainActivity.hotzones = false;
		MainActivity.fullCheck = false;
		MainActivity.dontAlert = false;
		

	}
}