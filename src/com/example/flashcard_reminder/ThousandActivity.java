package com.example.flashcard_reminder;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ThousandActivity extends ListActivity {

	public static String THOUSAND_ID = "com.example.flashcard_reminder.THOUSAND_ID";
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thousand);
		
		lv = getListView();
	    Log.e("start ThousandActivity", "");
	}

	 @Override
	 public void onListItemClick(ListView l, View v, int position, long id) {
		 BundleOfCards item = (BundleOfCards)lv.getItemAtPosition(position);
	     Log.e("click_thousand", Integer.toString(item.getId()));
	     
	     Intent intent = new Intent(this, HundredActivity.class);
	     intent.putExtra(THOUSAND_ID, item.getId());
	     startActivity(intent);
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thousand, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    Log.e("start", "resume thousand");
	    ArrayList<BundleOfCards> thousands = ((AppContext) getApplicationContext()).getThousand();
	    for (BundleOfCards current_thousand : thousands) {
	    	
	    	current_thousand.getStat()[0] = current_thousand.getStat()[1] = current_thousand.getStat()[2] = 
	    		current_thousand.getStat()[3] = current_thousand.getStat()[4] = current_thousand.getStat()[5] = 0;
	    	
	    	ArrayList<BundleOfCards> current_bunch_of_hundreds = ((AppContext) getApplicationContext()).getHundred(current_thousand.getId());
	    	for (BundleOfCards current_hundred : current_bunch_of_hundreds) {
	    		
	    		((AppContext) getApplicationContext()).prepareHundred(current_hundred.getId());
	    		
	    		current_thousand.getStat()[0] += current_hundred.getStat()[0];
	    		current_thousand.getStat()[1] += current_hundred.getStat()[1];
	    		current_thousand.getStat()[2] += current_hundred.getStat()[2];
	    		current_thousand.getStat()[3] += current_hundred.getStat()[3];
	    		current_thousand.getStat()[4] += current_hundred.getStat()[4];
	    		current_thousand.getStat()[5] += current_hundred.getStat()[5];
	    	}
	    }
	    Log.e("end", "resume thousand");
        // 1. pass context and data to the custom adapter
	    AdapterForBundleOfHundred adapter = new AdapterForBundleOfHundred(this, thousands);
        //2. setListAdapter
        setListAdapter(adapter);
	}
}
