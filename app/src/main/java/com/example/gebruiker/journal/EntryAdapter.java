package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;


public class EntryAdapter extends ResourceCursorAdapter {

    // constructor
    public EntryAdapter (Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    // set info of entry to the bindview
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // set title
        TextView title_view = view.findViewById(R.id.title);
        title_view.setText(cursor.getString(cursor.getColumnIndex("title")));

        // set mood string
        TextView mood_view = view.findViewById(R.id.mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood")).toLowerCase();
        mood_view.setText(mood);

        // set mood image
        int emoji = context.getResources().getIdentifier(mood,"drawable", context.getPackageName());
        ImageView mood_picture_view = view.findViewById(R.id.mood_picture);
        mood_picture_view.setImageResource(emoji);

        // set timestamp
        TextView date_view = view.findViewById(R.id.date);
        date_view.setText(cursor.getString(cursor.getColumnIndex("timestamp")));
    }
}
