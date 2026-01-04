package org.example;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private Student student;
    private Section section;
    private List<Grade> grades = new ArrayList<>();

    public Enrollment(Student s, Section sec) {
        this.student = s;
        this.section = sec;
    }

    public void addGrade(double value, double weight) {
        grades.add(new Grade(value, weight));
        student.update("Nova nota lançada em " + section.getCourse().getId() + ": " + value);
    }

    public List<Grade> getGrades() { return grades; }
    public Section getSection() { return section; }
}