package org.example;

public class Section {
    private Course course;
    private String timeslot;
    public Section(Course c, String slot) { this.course = c; this.timeslot = slot; }
    public String getTimeslot() { return timeslot; }
    public Course getCourse() { return course; }
}