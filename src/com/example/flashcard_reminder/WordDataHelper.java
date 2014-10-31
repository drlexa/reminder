package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordDataHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DB_NAME = "Word";
	public static final String TABLE_NAME_WORD = "word";
	private static final String TABLE_CREATE_WORD = 
		"CREATE TABLE " + TABLE_NAME_WORD + " ( " + 
		"_id INTEGER PRIMARY KEY, " + 
		"repeated INTEGER, " + 
		"must_be_repeated INTEGER " + 
		");";
	
	WordDataHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("db", TABLE_CREATE_WORD);
		db.execSQL(TABLE_CREATE_WORD);
		String current_time = String.valueOf(new Date().getTime());
		for(int i = 1; i <= 3000; i++){
			
			db.execSQL(
				"insert into " + TABLE_NAME_WORD + " (_id, repeated, must_be_repeated) " + 
				" values (" + String.valueOf(i) + ", " + current_time + ", 0) "
			);
		}
		Log.e("END insert", "0");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public long[][] getWords() {

		long[][] result = new long[3000][];
		int i = 0;
		Cursor cur = this.getReadableDatabase().rawQuery("select repeated, must_be_repeated from " + TABLE_NAME_WORD + " order by _id", null);
		if (cur.moveToFirst()) {
			do {
				result[i] = new long[2];
				result[i][0] = cur.getLong(cur.getColumnIndex("repeated"));
				result[i][1] = cur.getLong(cur.getColumnIndex("must_be_repeated"));
				i = i + 1;
			} while (cur.moveToNext());
		}
		cur.close();

		return result;
	}
}