package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Collections;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word);
		
		hundred = getIntent().getIntExtra(HundredActivity.HUNDRED_ID, 0);
	    Log.e("start", Integer.toString(hundred));
	    
	    words = ((AppContext) getApplicationContext()).getWordBundle(hundred);
	    
	    WordTextEnglish = (TextView)findViewById(R.id.word_text_english);
	    WordTextTranslation = (TextView)findViewById(R.id.word_text_translation);
	    WordTextTranscription = (TextView)findViewById(R.id.word_text_transcription);
	    
        final Button do_not_know = (Button) findViewById(R.id.do_not_know);
        do_not_know.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	WordTextTranscription.setText(words.get(0).getTranscription());
            	WordTextTranslation.setText(words.get(0).getRussian());
            }
        });
        
        final Button know = (Button) findViewById(R.id.know);
        know.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Collections.rotate(words, -1);
            	draw_word();
            }
        });
	}

	private void draw_word(){
		
		WordTextEnglish.setText(words.get(0).getEnglish());
    	WordTextTranscription.setText("");
    	WordTextTranslation.setText("");
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
