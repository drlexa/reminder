package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterForBundleOfHundred extends ArrayAdapter<BundleOfCards> {

	private final Context context;
	private final ArrayList<BundleOfCards> data;

	public AdapterForBundleOfHundred(Context context, ArrayList<BundleOfCards> data) {

		super(context, R.layout.row_hundred, data);

		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.row_hundred, parent, false);

		// 3. Get the two text view from the rowView
		TextView name = (TextView) rowView.findViewById(R.id.hundred_item);
		TextView stat_red = (TextView) rowView.findViewById(R.id.hundred_item_red);
		TextView stat_pink = (TextView) rowView.findViewById(R.id.hundred_item_pink);
		TextView stat_orange = (TextView) rowView.findViewById(R.id.hundred_item_orange);
		TextView stat_yellow = (TextView) rowView.findViewById(R.id.hundred_item_yellow);
		TextView stat_lightgreen = (TextView) rowView.findViewById(R.id.hundred_item_lightgreen);
		TextView stat_green = (TextView) rowView.findViewById(R.id.hundred_item_green);
		// 4. Set the text for textView
		name.setText(data.get(position).getName());
		stat_red.setText(data.get(position).getStat()[5] + "");
		stat_pink.setText(data.get(position).getStat()[4] + "");
		stat_orange.setText(data.get(position).getStat()[3] + "");
		stat_yellow.setText(data.get(position).getStat()[2] + "");
		stat_lightgreen.setText(data.get(position).getStat()[1] + "");
		stat_green.setText(data.get(position).getStat()[0] + "");
		if(data.get(position).getStat()[5] > 0){
			
			name.setBackgroundColor(context.getResources().getColor(R.color.WordRed));
		}
		else if(data.get(position).getStat()[4] > 0){
			
			name.setBackgroundColor(context.getResources().getColor(R.color.WordPink));
		}		
		else if(data.get(position).getStat()[3] > 0){
	
			name.setBackgroundColor(context.getResources().getColor(R.color.WordOrange));
		}
		else if(data.get(position).getStat()[2] > 0){
			
			name.setBackgroundColor(context.getResources().getColor(R.color.WordYellow));
		}
		else if(data.get(position).getStat()[1] > 0){
			
			name.setBackgroundColor(context.getResources().getColor(R.color.WordLightGreen));
		}
		else {
			
			name.setBackgroundColor(context.getResources().getColor(R.color.WordGreen));
		}
		// 5. retrn rowView
		return rowView;
	}
}