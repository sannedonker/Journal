package com.example.gebruiker.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.security.KeyStore;
import java.util.Map;

import static android.provider.Telephony.Mms.Part.TEXT;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    // get correct instance of database
    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(context, "entries" ,null, 1);
            return instance;
        }
    }

    // constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // create database (once)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table entries ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title String," +
                   "content String, mood String, timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");
    }

    // update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    // select all info from database
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    // add new entry to database
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());

        db.insert("entries", null, values);
    }

    // delete entry from database
    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("entries", "_id =" + id, null);
    }
}
