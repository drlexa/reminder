package com.example.flashcard_reminder;

import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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
	
	private Context context;
	
	WordDataHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		long start_create_db = new Date().getTime();
		db.execSQL(TABLE_CREATE_WORD);
		String current_time = String.valueOf(new Date().getTime());
		SQLiteStatement insert = db.compileStatement(
			"insert into " + TABLE_NAME_WORD + " (_id, repeated, must_be_repeated) " + 
			" values (?, " + current_time + ", 0) "
		);
		db.beginTransaction();
		for(int i = 1; i <= ((AppContext) context.getApplicationContext()).NUMBER_OF_WORD; i++){
			
			insert.bindLong(1, i);
			insert.execute();
		}
		db.setTransactionSuccessful();	
		db.endTransaction();
		long end_create_db = new Date().getTime();
		Log.e("CREATE DB", (end_create_db - start_create_db) + "");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public long[][] getWords() {

		long[][] result = new long[((AppContext) context.getApplicationContext()).NUMBER_OF_WORD][];
		int i = 0;
		Cursor cur = this.getReadableDatabase().rawQuery("select repeated, must_be_repeated from " + TABLE_NAME_WORD + " order by _id", null);
		long start_cursor = new Date().getTime();
		if (cur.moveToFirst()) {
			do {
				result[i] = new long[2];
				result[i][0] = cur.getLong(cur.getColumnIndex("repeated"));
				result[i][1] = cur.getLong(cur.getColumnIndex("must_be_repeated"));
				i = i + 1;
			} while (cur.moveToNext());
		}
		cur.close();
		long end_cursor = new Date().getTime();
		Log.e("CURSOR DB", (end_cursor - start_cursor) + "");
		return result;
	}
}