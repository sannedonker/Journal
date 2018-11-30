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

//    Cursor cursor;

    // constructor
    public EntryAdapter (Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title_view = view.findViewById(R.id.title);
        title_view.setText(cursor.getString(cursor.getColumnIndex("title")));

        TextView mood_view = view.findViewById(R.id.mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood")).toLowerCase();
        mood_view.setText(mood);

        Log.d("mood", "mood " + mood);
//        Log.d("mood", "image " + )

        TextView date_view = view.findViewById(R.id.date);
        date_view.setText(cursor.getString(cursor.getColumnIndex("timestamp")));

//        String emojis[] = {"amazing", "angry", "happy", "sad"};
//        String emoji = "amazing";
//        for (int i = 0; i < emojis.length; i ++) {
//            if (mood.equals(emojis[i])) {
//                emoji = emojis[i];
//                break;
//            }
//        }

//        getResources().getIdentifier(mood,"drawable", getPackageName())));

//        ((ImageView) convertView.findViewById(R.id.PhotoFriend)).setImageResource(current_friend.getDrawableId());

        int emoji = context.getResources().getIdentifier(mood,"drawable", context.getPackageName());


        ImageView mood_picture_view = view.findViewById(R.id.mood_picture);
        mood_picture_view.setImageResource(emoji);
    }
}
