package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    JournalEntry retrievedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        retrievedEntry = (JournalEntry) intent.getSerializableExtra("clickedEntry");
        ((TextView) findViewById(R.id.title_detail)).setText(retrievedEntry.getTitle());
        ((TextView) findViewById(R.id.content_detail)).setText(retrievedEntry.getContent());
        ((TextView) findViewById(R.id.date_detail)).setText(retrievedEntry.getTimestamp());
    }
}
