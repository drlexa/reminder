package com.example.flashcard_reminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

	private SQLiteDatabase mydb_read;
	private SQLiteDatabase mydb_write;
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME_SET = "`set`";
	private static final String TABLE_CREATE_SET = 
		"CREATE TABLE " + TABLE_NAME_SET + " ( " + 
		"id INTEGER PRIMARY KEY, " + 
		"name TEXT " + 
		");";
	private static final String TABLE_NAME_WORD = "word";
	private static final String TABLE_CREATE_WORD = 
		"CREATE TABLE " + TABLE_NAME_WORD + " ( " + 
		"id INTEGER PRIMARY KEY, " + 
		"set_id INTEGER, " + 
		"word TEXT, " + 
		"translation TEXT, " + 
		"transcription TEXT, " + 
		"extra_info TEXT, " + 
		"repeated INTEGER, " + 
		"must_be_repeated INTEGER " + 
		");";

	private static final String[] TABLE_INSERT_SET = {
		"insert into " + TABLE_NAME_SET + " (name) values " + "('Неправильные глаголы');", 
		"insert into " + TABLE_NAME_SET + " (name) values " + "('Самые употребительные слова (первая 1000)');", 
		"insert into " + TABLE_NAME_SET + " (name) values " + "('Фразовые глаголы');"
	};
	
	private static final String current_time = String.valueOf(new Date().getTime());
	
	private static final String[] TABLE_INSERT_WORD = {
		"insert into " + TABLE_NAME_WORD + 
			" (set_id, word, translation, transcription, extra_info, repeated, must_be_repeated) values " + 
			" (1, 'write', 'писать', 'raɪt', 'mark (letters, words, or other symbols) on a surface, typically paper, with a pen, pencil, or similar implement', " + current_time + ", 0 ); ", 
		"insert into " + TABLE_NAME_WORD + 
			" (set_id, word, translation, transcription, extra_info, repeated, must_be_repeated) values " + 
			" (1, 'read', 'читать', 'riːd', 'look at and comprehend the meaning of (written or printed matter) by mentally interpreting the characters or symbols of which it is composed.', " + current_time + ", 0 ); ", 
		"insert into " + TABLE_NAME_WORD + 
			" (set_id, word, translation, transcription, extra_info, repeated, must_be_repeated) values " + 
			" (1, 'let', 'пускать', 'let', 'not prevent or forbid; allow.', " + current_time + ", 0 ); ", 
		"insert into " + TABLE_NAME_WORD + 
			" (set_id, word, translation, transcription, extra_info, repeated, must_be_repeated) values " + 
			" (2, 'fish', 'рыба', 'fɪʃ', 'a limbless cold-blooded vertebrate animal with gills and fins and living wholly in water.', " + current_time + ", 0 ); "
	};
		
	DataHelper(Context context) {
		super(context, "sets_of_words", null, DATABASE_VERSION);
		mydb_read = this.getReadableDatabase();
		mydb_write = this.getWritableDatabase();
		Log.e("db", "getReadableDatabase, getWritableDatabase");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("db", TABLE_CREATE_SET);
		db.execSQL(TABLE_CREATE_SET);
		for (String val : TABLE_INSERT_SET) {
			Log.e("db", val);
			db.execSQL(val);
		}
		
		Log.e("db", TABLE_CREATE_WORD);
		db.execSQL(TABLE_CREATE_WORD);
		for (String val : TABLE_INSERT_WORD) {
			Log.e("db", val);
			db.execSQL(val);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public ArrayList<Item> getAllWords() {

		ArrayList<Item> items = new ArrayList<Item>();

		Cursor cur = mydb_read.rawQuery("select * from " + TABLE_NAME_WORD, null);
		if (cur.moveToFirst()) {
			do {
				String word = cur.getString(cur.getColumnIndex("word"));
				String translation = cur.getString(cur.getColumnIndex("translation"))
					+ " [" + cur.getString(cur.getColumnIndex("transcription")) + "]";
				long repeated = cur.getLong(cur.getColumnIndex("repeated"));
				Log.e("db", word);
				Log.e("db", translation);
				Log.e("db", String.valueOf(repeated));
				items.add(new Item(word, translation, repeated));
			} while (cur.moveToNext());
		}
		cur.close();

		return items;
	}
}