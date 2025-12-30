package com.example.MoodRecommender.model;

import java.util.List;

public class Recommendation {
    private String mood;
    private List<Item> quotes;
    private List<Item> songs;
    private String emojiUrl;

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public List<Item> getQuotes() { return quotes; }
    public void setQuotes(List<Item> quotes) { this.quotes = quotes; }

    public List<Item> getSongs() { return songs; }
    public void setSongs(List<Item> songs) { this.songs = songs; }

    public String getEmojiUrl() { return emojiUrl; }
    public void setEmojiUrl(String emojiUrl) { this.emojiUrl = emojiUrl; }
}