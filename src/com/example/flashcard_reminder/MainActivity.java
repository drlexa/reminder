package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());
 
        //2. setListAdapter
        setListAdapter(adapter);
        
        Clicker clicker = new Clicker(this, adapter);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(clicker);
    }

    public void set_adapter(){
    	
        // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());
 
        //2. setListAdapter
        setListAdapter(adapter);
    }
    
    
    
    private ArrayList<Item> generateData(){
        ArrayList<Item> items = new ArrayList<Item>();
        
        for(int x = 0; x < 100; x = x+1) {
        	
        	char[] chars = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
        	StringBuilder sb = new StringBuilder();
        	StringBuilder sb2 = new StringBuilder();
        	Random random = new Random();
        	for (int i = 0; i < 40; i++) {
        	    char c = chars[random.nextInt(chars.length)];
        	    sb.append(c);
        	    if(i % 3 == 0){
        	    	
        	    	c = chars[random.nextInt(chars.length)];
        	    	sb2.append(c);
        	    }
        	}
        	
            items.add(new Item(Integer.toString(x) + sb2.toString(), sb.toString()));
        }
        
        return items;
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
