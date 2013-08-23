package com.example.gravity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Settings extends ListActivity {

	static final String[] FRUITS = new String[] { "Pick Color", "Set Number of Particles", "Set Gravity", "Reset"};

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_LEFT_ICON);


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
		   } else if(position == 3){
			   new AlertDialog.Builder(Settings.this)
			   .setTitle("Reset")
			   .setMessage("Do you really want to reset all settings?")
			   .setIcon(android.R.drawable.ic_dialog_alert)
			   .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

			       public void onClick(DialogInterface dialog, int whichButton) {
			    	   MainActivity.resetAll();
			       }})
			    .setNegativeButton(android.R.string.no, null).show();

		   }
		}
	});

}

}