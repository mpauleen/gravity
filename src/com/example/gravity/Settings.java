package com.example.gravity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Settings extends ListActivity {

	static final String[] FRUITS = new String[] { "Pick Color", "Set Number of Particles", "Set Gravity"};

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// no more this
	// setContentView(R.layout.list_fruit);

	setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_settings,FRUITS));

	ListView listView = getListView();
	listView.setTextFilterEnabled(true);

	listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
		   if(position == 0){
			   Intent intent = new Intent(getApplicationContext(),ColorChooserDialog.class);
			   startActivity(intent);
		   }
		   else if(position == 1){
			   Intent intent = new Intent(getApplicationContext(), ParticleNumberSetter.class);
			   startActivity(intent);
		   }
		   else if(position == 2){
			   Intent intent = new Intent(getApplicationContext(), ChangeGravity.class);
			   startActivity(intent);
		   }
		}
	});

}

}