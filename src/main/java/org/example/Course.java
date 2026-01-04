package org.example;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private int credits;
    private List<Course> prerequisites = new ArrayList<>();
    public Course(String id, int credits) { this.id = id; this.credits = credits; }
    public void addPrerequisite(Course c) { prerequisites.add(c); }
    public List<Course> getPrerequisites() { return prerequisites; }
    public int getCredits() { return credits; }
    public String getId() { return id; }
}
