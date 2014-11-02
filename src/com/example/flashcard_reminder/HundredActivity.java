package com.example.flashcard_reminder;

import java.util.Date;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class HundredActivity extends ListActivity {

	public static String HUNDRED_ID = "com.example.flashcard_reminder.HUNDRED_ID";
	private int thousand;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hundred);
		
		lv = getListView();
		
		thousand = getIntent().getIntExtra(ThousandActivity.THOUSAND_ID, 0);
	    
	    this.setTitle(((AppContext) getApplicationContext()).getThousand().get(thousand).getName());
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
	    long start_resume_hundred = new Date().getTime();
	    for(int i = thousand * 10; i <= thousand * 10 + 9; i++){
	    	
	    	//Log.e("prepare hundred", i + "");
	        ((AppContext) getApplicationContext()).prepareHundred(i);
	    }
	    long end_resume_hundred = new Date().getTime();
	    Log.e("RESUME HUNDRED", (end_resume_hundred - start_resume_hundred) + "");
        // 1. pass context and data to the custom adapter
	    AdapterForBundleOfHundred adapter = new AdapterForBundleOfHundred(this, ((AppContext) getApplicationContext()).getHundred(thousand));
        //2. setListAdapter
        setListAdapter(adapter);
	}
	
	 @Override
	 public void onListItemClick(ListView l, View v, int position, long id) {
		 BundleOfCards item = (BundleOfCards)lv.getItemAtPosition(position);
	     
	     Intent intent = new Intent(this, WordActivity.class);
	     intent.putExtra(HUNDRED_ID, item.getId());
	     startActivity(intent);
	 }
}
