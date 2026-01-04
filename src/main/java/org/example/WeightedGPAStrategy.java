package org.example;

import java.util.List;

public class WeightedGPAStrategy implements GPAStrategy {

    @Override
    public double calculate(List<Enrollment> enrollments) {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment e : enrollments) {
            double courseAverage = e.getGrades().stream()
                    .mapToDouble(Grade::getWeightedValue).sum();
            totalPoints += (courseAverage * e.getSection().getCourse().getCredits());
            totalCredits += e.getSection().getCourse().getCredits();
        }
        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }
}