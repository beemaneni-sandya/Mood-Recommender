package com.example.MoodRecommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoodRecommender.model.Recommendation;
import com.example.MoodRecommender.service.MoodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mood")
public class MoodController {

    @Autowired
    private MoodService moodService;

    @GetMapping(produces = "application/json")
    public Recommendation getRecommendation(@RequestParam String mood) {
        return moodService.getRecommendation(mood);
    }

    @GetMapping(value = "/default", produces = "application/json")
    public Recommendation getDefaultRecommendation() {
        return moodService.getRecommendation("happy");
    }
}
