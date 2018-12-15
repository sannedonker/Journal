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

        // get info of clicked entry and show it
        Intent intent = getIntent();
        retrievedEntry = (JournalEntry) intent.getSerializableExtra("clickedEntry");
        ((TextView) findViewById(R.id.title_detail)).setText(retrievedEntry.getTitle());
        ((TextView) findViewById(R.id.content_detail)).setText(retrievedEntry.getContent());
        ((TextView) findViewById(R.id.date_detail)).setText(retrievedEntry.getTimestamp());

        // set the right image to the mood
        String mood = retrievedEntry.getMood().toLowerCase();
        int emoji = getResources().getIdentifier(mood,"drawable", getPackageName());
        ((ImageView) findViewById(R.id.mood_detail)).setImageResource(emoji);
    }

    // makes sure that when pressed back the user goes to the ChooseActivity screen
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
