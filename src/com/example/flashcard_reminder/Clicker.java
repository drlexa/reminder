package com.example.flashcard_reminder;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class Clicker implements View.OnClickListener {
 
    private final MainActivity context;
    private final MyAdapter adapter;
 
    public Clicker(MainActivity c, MyAdapter a) {
        super();
        this.context = c;
        this.adapter = a;
    }

	public MainActivity getContext() {
		return context;
	}

	public MyAdapter getAdapter() {
		return adapter;
	}
	
	@Override public void onClick(View v) {
        // Perform action on click
		Log.e("blah", "blah blah");
		adapter.next_item();
		context.setListAdapter(adapter);
    }
}