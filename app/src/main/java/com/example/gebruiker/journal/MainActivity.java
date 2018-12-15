package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get updated database and adapter
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(MainActivity.this, db.selectAll());

        // set the listview
        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener());
        lv.setOnItemLongClickListener(new OnItemLongClickListener());
    }

    // when button clicked go to the inputactivity screen
    public void newEntry(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // when item clicked go to detailactivity screen
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // send correct info to the next screen
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            JournalEntry entry = new JournalEntry(title, content, mood, timestamp);

            intent.putExtra("clickedEntry", entry);
            startActivity(intent);
        }
    }

    // when item clikced for long delete it
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // select correct item
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            long entry_id = cursor.getInt(cursor.getColumnIndex("_id"));

            // update database
            db = EntryDatabase.getInstance(getApplicationContext());
            db.delete(entry_id);
            updateData(db);

            return false;
        }
    }

    // update data in listview
    private void updateData(EntryDatabase db){
        adapter.swapCursor(db.selectAll());
    }

}
