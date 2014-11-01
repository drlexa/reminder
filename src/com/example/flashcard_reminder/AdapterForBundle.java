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

public class AdapterForBundle extends ArrayAdapter<BundleOfCards> {

	private final Context context;
	private final ArrayList<BundleOfCards> data;

	public AdapterForBundle(Context context, ArrayList<BundleOfCards> data) {

		super(context, R.layout.row, data);

		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.row, parent, false);

		// 3. Get the two text view from the rowView
		TextView labelView = (TextView) rowView.findViewById(R.id.list_thousand);
		TextView valueView = (TextView) rowView.findViewById(R.id.value);
		TextView dateView = (TextView) rowView.findViewById(R.id.date);

		// 4. Set the text for textView
		labelView.setText(data.get(position).getName());
		valueView.setText(
			":" + data.get(position).getStat()[0] + "\n" + 
			":" + data.get(position).getStat()[1] + "\n" + 
			":" + data.get(position).getStat()[2] + "\n" + 
			":" + data.get(position).getStat()[3] + "\n" + 
			":" + data.get(position).getStat()[4] + "\n" + 
			":" + data.get(position).getStat()[5] + "\n"
		);
		dateView.setText("");
		// 5. retrn rowView
		return rowView;
	}
}