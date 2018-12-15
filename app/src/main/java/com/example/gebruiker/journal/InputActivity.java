package com.example.gebruiker.journal;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }


    // set mood to the clicked mood
    public void moodPicked(View view) {
        int button_ids[] = {R.id.imageButton_amazing, R.id.imageButton_angry, R.id.imageButton_happy,
                R.id.imageButton_sad};
        String moods[] = {"Amazing", "Angry", "Happy", "Sad"};
        for (int i = 0; i < button_ids.length; i ++) {
            if (button_ids[i] == view.getId()) {
                mood = moods[i];
            }
        }
    }

    // add entry to the database
    public void addEntry(View view) {

        // if no mood is clicked, standard mood is happy
        if (mood == null) {
            mood = "happy";
        }

        // collect info and add it to the database
        String title = ((EditText) findViewById(R.id.title_input)).getText().toString();
        String content = ((EditText) findViewById(R.id.content_input)).getText().toString();
        JournalEntry entry = new JournalEntry(title, content, mood);

        EntryDatabase db = EntryDatabase.getInstance(InputActivity.this);
        db.insert(entry);

        // send user back to homescreen
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
