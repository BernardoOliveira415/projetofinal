package org.example;

import java.util.List;

public interface GPAStrategy {
    double calculate(List<Enrollment> enrollments);
}