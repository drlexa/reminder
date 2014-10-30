package com.example.flashcard_reminder;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class HundredActivity extends ListActivity {

	private int hundred;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hundred);
		
		hundred = getIntent().getIntExtra(ThousandActivity.HUNDRED_ID, 0);
	    Log.e("start", Integer.toString(hundred));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hundred, menu);
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

        // 1. pass context and data to the custom adapter
	    AdapterForBundle adapter = new AdapterForBundle(this, ((AppContext) getApplicationContext()).getHundred(hundred));
        //2. setListAdapter
        setListAdapter(adapter);
	}
}
