package com.example.flashcard_reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordActivity extends ActionBarActivity {

	private int hundred;
	private ArrayList<Word> words;
	private TextView WordText;
	private TextView WordDesc;
	private TextView WordDebug;
	
	private TextView WordStatRed;
	private TextView WordStatPink;
	private TextView WordStatOrange;
	private TextView WordStatYellow;
	private TextView WordStatLightGreen;
	private TextView WordStatGreen;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word);
		
		hundred = getIntent().getIntExtra(HundredActivity.HUNDRED_ID, 0);
	    
	    this.setTitle(((AppContext) getApplicationContext()).getHundred(hundred / 10).get(hundred % 10).getName());
	    
	    words = ((AppContext) getApplicationContext()).getWordBundle(hundred);
	    
	    WordText = (TextView)findViewById(R.id.word_text);
	    WordDesc = (TextView)findViewById(R.id.word_desc);
	    WordDebug = (TextView)findViewById(R.id.word_debug);
	    
	    WordStatRed = (TextView)findViewById(R.id.hundred_item_red_inner_word);
	    WordStatPink = (TextView)findViewById(R.id.hundred_item_pink_inner_word);
	    WordStatOrange = (TextView)findViewById(R.id.hundred_item_orange_inner_word);
	    WordStatYellow = (TextView)findViewById(R.id.hundred_item_yellow_inner_word);
	    WordStatLightGreen = (TextView)findViewById(R.id.hundred_item_lightgreen_inner_word);
	    WordStatGreen = (TextView)findViewById(R.id.hundred_item_green_inner_word);
	    
        final Button do_not_know = (Button) findViewById(R.id.do_not_know);
        do_not_know.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	long ct = new Date().getTime();
        		ContentValues cv = new ContentValues();
        		cv.put("repeated", String.valueOf(ct));
        		cv.put("must_be_repeated", "6");
        		int r = ((AppContext) getApplicationContext()).getDB().getWritableDatabase().update(
        			WordDataHelper.TABLE_NAME_WORD, cv, "_id = ?", new String[] { Integer.toString(words.get(0).getId()) });
            	Log.e("help", Integer.toString(r));
            	
            	words.get(0).setUpdated(ct);
            	words.get(0).setError(6);
            	
            	WordDesc.setText(words.get(0).getTranscription() + " " + words.get(0).getRussian());
            }
        });
        
        final Button know = (Button) findViewById(R.id.know);
        know.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	long ct = new Date().getTime();
        		ContentValues cv = new ContentValues();
        		cv.put("repeated", String.valueOf(ct));
        		long num_error = words.get(0).getError();
        		if(num_error > 0){
        			
        			cv.put("must_be_repeated", String.valueOf(num_error - 1));
        		}
        		int r = ((AppContext) getApplicationContext()).getDB().getWritableDatabase().update(
        			WordDataHelper.TABLE_NAME_WORD, cv, "_id = ?", new String[] { Integer.toString(words.get(0).getId()) });
            	Log.e("next", Integer.toString(r));
            	
            	words.get(0).setUpdated(ct);
        		if(num_error > 0){
        			
        			words.get(0).setError(num_error - 1);
        		}
            	
            	draw_word();
            }
        });
	}

	private void draw_word(){
		
		((AppContext) getApplicationContext()).prepareHundred(hundred);
		
		WordText.setText(words.get(0).getEnglish());
    	WordDesc.setText("");
    	
    	WordDebug.setText(
    		"id: " + words.get(0).getId() + "\n" +
    		"last updated: " + formatter.format(words.get(0).getUpdated()) + "\n" +
    		"error: " + words.get(0).getError() + "\n" +
    		"sorted: " + words.get(0).getSorted() + "\n" +
    		"color: " + Integer.toString(words.get(0).getColor(), 16) + "\n" +
    		"hundred of words: " + hundred
    	);
    	
    	BundleOfCards current_bundle = ((AppContext) getApplicationContext()).getHundred(hundred / 10).get(hundred % 10);
    	WordStatRed.setText(current_bundle.getStat()[5] + "");
    	WordStatPink.setText(current_bundle.getStat()[4] + "");
    	WordStatOrange.setText(current_bundle.getStat()[3] + "");
    	WordStatYellow.setText(current_bundle.getStat()[2] + "");
    	WordStatLightGreen.setText(current_bundle.getStat()[1] + "");
    	WordStatGreen.setText(current_bundle.getStat()[0] + "");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word, menu);
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
	    long start_resume_word = new Date().getTime();
	    draw_word();
	    long end_resume_word = new Date().getTime();
	    Log.e("RESUME WORD", (end_resume_word - start_resume_word) + "");
	}
}
