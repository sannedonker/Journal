package com.example.gebruiker.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private int id;
    private String title, content, mood, timestamp;

    public JournalEntry(int id, String title, String content, String mood, String timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public JournalEntry(String title, String content, String mood, String timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() { return timestamp; }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
