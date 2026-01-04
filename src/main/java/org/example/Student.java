package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Observer {
    private List<Enrollment> enrollments = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();

    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void update(String message) {
        notifications.add(message);
    }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public List<String> getNotifications() { return notifications; }
}