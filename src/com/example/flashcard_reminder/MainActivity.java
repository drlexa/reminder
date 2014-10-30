package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ListActivity {

	private DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.e("onCreate", "must be one");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = new DataHelper(this);
        // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, db.getAllWords());
 
        //2. setListAdapter
        setListAdapter(adapter);
        
        Clicker clicker = new Clicker(this, adapter);
        final Button button = (Button) findViewById(R.id.relative_layout);
        button.setOnClickListener(clicker);
    }
        
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
