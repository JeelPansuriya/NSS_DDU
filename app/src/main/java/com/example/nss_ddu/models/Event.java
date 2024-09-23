package com.example.nss_ddu.models;

public class Event {
    private String title;
    private String description;
    private String date;
    private String venue;
    private String time;
    private String link;

    public Event(String title, String description, String date, String venue, String time, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.time = time;
        this.link = link;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
    public String getTime() { return time; }
    public String getLink() { return link; }
}
