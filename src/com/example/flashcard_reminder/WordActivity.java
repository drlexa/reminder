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
	private TextView WordTextEnglish;
	private TextView WordTextTranslation;
	private TextView WordTextTranscription;
	
	private TextView DevID;
	private TextView DevUpdated;
	private TextView DevError;
	private TextView DevSorted;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word);
		
		hundred = getIntent().getIntExtra(HundredActivity.HUNDRED_ID, 0);
	    Log.e("start WordActivity", Integer.toString(hundred));
	    
	    words = ((AppContext) getApplicationContext()).getWordBundle(hundred);
	    
	    WordTextEnglish = (TextView)findViewById(R.id.word_text_english);
	    WordTextTranslation = (TextView)findViewById(R.id.word_text_translation);
	    WordTextTranscription = (TextView)findViewById(R.id.word_text_transcription);
	    
	    DevID = (TextView)findViewById(R.id.dev_id);
	    DevUpdated = (TextView)findViewById(R.id.dev_updated);
	    DevError = (TextView)findViewById(R.id.dev_error);
	    DevSorted = (TextView)findViewById(R.id.dev_sorted);
	    
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
            	
            	WordTextTranscription.setText(words.get(0).getTranscription());
            	WordTextTranslation.setText(words.get(0).getRussian());
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
            	
            	Collections.rotate(words, -1);
            	draw_word();
            }
        });
        
        ((AppContext) getApplicationContext()).prepareHundred(this.hundred);
	}

	private void draw_word(){
		
		WordTextEnglish.setText(words.get(0).getEnglish());
    	WordTextTranscription.setText("");
    	WordTextTranslation.setText("");
    	
    	DevID.setText("ID: " + words.get(0).getId());
    	DevUpdated.setText("DATE: " + formatter.format(words.get(0).getUpdated()));
    	DevError.setText("ERROR: " + words.get(0).getError());
    	DevSorted.setText("SORTED: " + words.get(0).getSorted());
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
	    draw_word();
	}
}
