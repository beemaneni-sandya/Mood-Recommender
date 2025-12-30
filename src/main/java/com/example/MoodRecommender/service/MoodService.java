package com.example.MoodRecommender.service;

import com.example.MoodRecommender.model.Item;
import com.example.MoodRecommender.model.Recommendation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class MoodService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String detectMood(String text) {
        text = text.toLowerCase();
        if (text.contains("excited") || text.contains("thrilled") || text.contains("awesome")) return "Excited";
        else if (text.contains("happy") || text.contains("joy") || text.contains("smile")) return "Happy";
        else if (text.contains("sad") || text.contains("down") || text.contains("unhappy")) return "Sad";
        else if (text.contains("stress") || text.contains("tired") || text.contains("anxious")) return "Stressed";
        else if (text.contains("bored") || text.contains("meh") || text.contains("dull")) return "Bored";
        else if (text.contains("angry") || text.contains("mad") || text.contains("frustrated")) return "Angry";
        else if (text.contains("calm") || text.contains("relaxed") || text.contains("peaceful")) return "Calm";
        else return "Neutral";
    }

    public Recommendation getRecommendation(String mood) {
        Recommendation rec = new Recommendation();
        rec.setMood(mood);

        rec.setEmojiUrl("https://twemoji.maxcdn.com/v/latest/72x72/" + getEmojiCode(mood.toLowerCase()) + ".png");

        rec.setQuotes(fetchMoodSpecificQuotes(mood, 3));
        rec.setSongs(fetchUniqueSongs(mood, 3));

        return rec;
    }

    // -----------------------------
    // Fetch mood-specific quotes
    // -----------------------------
    private List<Item> fetchMoodSpecificQuotes(String mood, int count) {
        List<Item> quotes = new ArrayList<>();
        
        // Mood-specific fallback quotes
        Map<String, List<String>> moodQuotes = Map.of(
            "happy", List.of(
                "Happiness is not something ready made. It comes from your own actions. - Dalai Lama",
                "The purpose of our lives is to be happy. - Dalai Lama",
                "Happiness is a choice, not a result. - Ralph Marston"
            ),
            "sad", List.of(
                "The wound is the place where the Light enters you. - Rumi",
                "Every storm runs out of rain. - Maya Angelou",
                "Tears are words that need to be written. - Paulo Coelho"
            ),
            "excited", List.of(
                "The way to get started is to quit talking and begin doing. - Walt Disney",
                "Life is what happens to you while you're busy making other plans. - John Lennon",
                "Energy and persistence conquer all things. - Benjamin Franklin"
            ),
            "calm", List.of(
                "Peace comes from within. Do not seek it without. - Buddha",
                "In the midst of movement and chaos, keep stillness inside of you. - Deepak Chopra",
                "Calm mind brings inner strength and self-confidence. - Dalai Lama"
            ),
            "stressed", List.of(
                "You have been assigned this mountain to show others it can be moved. - Mel Robbins",
                "Stress is caused by being here but wanting to be there. - Eckhart Tolle",
                "Take time to make your soul happy. - Unknown"
            )
        );
        
        List<String> moodSpecificQuotes = moodQuotes.getOrDefault(mood.toLowerCase(), 
            List.of("Every moment is a fresh beginning. - T.S. Eliot",
                   "Life is 10% what happens to you and 90% how you react to it. - Charles R. Swindoll"));
        
        for (int i = 0; i < Math.min(count, moodSpecificQuotes.size()); i++) {
            quotes.add(new Item(moodSpecificQuotes.get(i), "#"));
        }
        
        return quotes;
    }

    // -----------------------------
    // Fetch mood-specific songs
    // -----------------------------
    private List<Item> fetchUniqueSongs(String mood, int count) {
        List<Item> songs = new ArrayList<>();
        
        // Direct mood-specific songs that will always work
        Map<String, List<String>> moodSongs = Map.of(
            "happy", List.of(
                "Happy - Pharrell Williams", 
                "Good as Hell - Lizzo", 
                "Can't Stop the Feeling - Justin Timberlake",
                "Walking on Sunshine - Katrina and the Waves"
            ),
            "sad", List.of(
                "Someone Like You - Adele", 
                "Hurt - Johnny Cash", 
                "Mad World - Gary Jules",
                "The Night We Met - Lord Huron"
            ),
            "excited", List.of(
                "Uptown Funk - Bruno Mars", 
                "I Gotta Feeling - Black Eyed Peas", 
                "Thunder - Imagine Dragons",
                "High Hopes - Panic! At The Disco"
            ),
            "calm", List.of(
                "Weightless - Marconi Union", 
                "River - Joni Mitchell", 
                "Clair de Lune - Debussy",
                "Mad About You - Sting"
            ),
            "stressed", List.of(
                "Breathe Me - Sia", 
                "The Sound of Silence - Simon & Garfunkel", 
                "Aqueous Transmission - Incubus",
                "Stress Relief - Meditation Music"
            ),
            "bored", List.of(
                "Mr. Brightside - The Killers",
                "Don't Stop Me Now - Queen",
                "Bohemian Rhapsody - Queen",
                "Sweet Child O' Mine - Guns N' Roses"
            ),
            "angry", List.of(
                "Break Stuff - Limp Bizkit",
                "Bodies - Drowning Pool",
                "Killing in the Name - Rage Against the Machine",
                "Chop Suey! - System of a Down"
            )
        );
        
        List<String> selectedSongs = moodSongs.getOrDefault(mood.toLowerCase(), 
            List.of("Let it Be - The Beatles", "Imagine - John Lennon", "What a Wonderful World - Louis Armstrong"));
        
        // Take only the requested count
        for (int i = 0; i < Math.min(count, selectedSongs.size()); i++) {
            songs.add(new Item(selectedSongs.get(i), "https://music.apple.com/search?term=" + 
                URLEncoder.encode(selectedSongs.get(i), StandardCharsets.UTF_8)));
        }
        
        return songs;
    }

    // -----------------------------
    // Map mood to Twemoji code
    // -----------------------------
    private String getEmojiCode(String mood) {
        Map<String, String> map = Map.of(
                "happy", "1f600",
                "sad", "1f622",
                "stressed", "1f62c",
                "excited", "1f973",
                "bored", "1f611",
                "angry", "1f620",
                "calm", "1f60c",
                "neutral", "1f610"
        );
        return map.getOrDefault(mood, "1f610");
    }
}