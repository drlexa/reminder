package com.example.flashcard_reminder;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterForBundleOfThousand extends ArrayAdapter<BundleOfCards> {

	private final Context context;
	private final ArrayList<BundleOfCards> data;

	public AdapterForBundleOfThousand(Context context, ArrayList<BundleOfCards> data) {

		super(context, R.layout.row_thousand, data);

		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.row_thousand, parent, false);

		// 3. Get the two text view from the rowView
		TextView name = (TextView) rowView.findViewById(R.id.thousand_item);

		// 4. Set the text for textView
		name.setText(data.get(position).getName());
		// 5. retrn rowView
		return rowView;
	}
}