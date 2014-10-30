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
 
public class MyAdapter extends ArrayAdapter<Item> {
 
        private final Context context;
        private final ArrayList<Item> itemsArrayList;
        private static int current_item = 0;
        
        public MyAdapter(Context context, ArrayList<Item> itemsArrayList) {
 
            super(context, R.layout.row, itemsArrayList);
 
            this.context = context;
            this.itemsArrayList = itemsArrayList;
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
            labelView.setText(itemsArrayList.get(position).getTitle());
            valueView.setText(itemsArrayList.get(position).getDescription());
            dateView.setText(itemsArrayList.get(position).getDatetime());
            // 5. retrn rowView
            return rowView;
        }
        
        public void next_item(){
        	
        	update_date_first_element();
        	Collections.rotate(itemsArrayList, -1);
        }
        
        public void update_date_first_element(){
        	
        	itemsArrayList.get(0).setDatetime(new Date().getTime());
        }
}