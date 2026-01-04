package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;



class GPACalculatorTest {
    private Student student;
    private WeightedGPAStrategy strategy;

    @BeforeEach
    void setUp() {
        student = new Student("S1", "Maria");
        strategy = new WeightedGPAStrategy();
    }

    @Test
    @DisplayName("T11: GPA deve ser zero sem inscrições")
    void testGPANoEnrollments() {
        assertEquals(0.0, strategy.calculate(student.getEnrollments()));
    }

    @Test
    @DisplayName("T12: GPA com nota mínima (0.0) - Boundary Low")
    void testGPAMinValue() {
        Course c1 = new Course("C1", 6);
        Section sec = new Section(c1, "SEG 08:00");
        Enrollment e = new Enrollment(student, sec);
        e.addGrade(0.0, 1.0);
        student.getEnrollments().add(e);
        assertEquals(0.0, strategy.calculate(student.getEnrollments()));
    }

    @Test
    @DisplayName("T13: GPA com nota máxima (20.0) - Boundary High")
    void testGPAMaxValue() {
        Course c1 = new Course("C1", 6);
        Section sec = new Section(c1, "SEG 08:00");
        Enrollment e = new Enrollment(student, sec);
        e.addGrade(20.0, 1.0);
        student.getEnrollments().add(e);
        assertEquals(20.0, strategy.calculate(student.getEnrollments()));
    }

    @Test
    @DisplayName("T14: Cálculo GPA - Disciplinas com créditos iguais")
    void testGPASameCredits() {
        Enrollment e1 = new Enrollment(student, new Section(new Course("C1", 6), "H1"));
        e1.addGrade(10.0, 1.0);
        student.getEnrollments().add(e1);
        assertEquals(10.0, strategy.calculate(student.getEnrollments()));
    }

    @Test
    @DisplayName("T15: Cálculo GPA - Ponderação por créditos (6 vs 3)")
    void testGPAWeightedCredits() {
        Enrollment e1 = new Enrollment(student, new Section(new Course("C1", 6), "H1"));
        e1.addGrade(20.0, 1.0); // 20 * 6 = 120
        Enrollment e2 = new Enrollment(student, new Section(new Course("C2", 3), "H2"));
        e2.addGrade(10.0, 1.0); // 10 * 3 = 30
        student.getEnrollments().addAll(Arrays.asList(e1, e2));

        assertEquals(16.66, strategy.calculate(student.getEnrollments()), 0.01);
    }

    @Test
    @DisplayName("T16: Cálculo GPA - Desempenho médio (Notas 12 e 14)")
    void testGPAMediumPerformance() {

        Enrollment e1 = new Enrollment(student, new Section(new Course("C1", 6), "H1"));
        e1.addGrade(12.0, 1.0);


        Enrollment e2 = new Enrollment(student, new Section(new Course("C2", 3), "H2"));
        e2.addGrade(14.0, 1.0);

        student.getEnrollments().addAll(Arrays.asList(e1, e2));

        assertEquals(12.66, strategy.calculate(student.getEnrollments()), 0.01);
    }

    @Test
    @DisplayName("T17: Cálculo GPA - Notas de passagem mínima (10 e 10)")
    void testGPABorderlinePass() {
        Enrollment e1 = new Enrollment(student, new Section(new Course("C1", 6), "H1"));
        e1.addGrade(10.0, 1.0);

        Enrollment e2 = new Enrollment(student, new Section(new Course("C2", 6), "H2"));
        e2.addGrade(10.0, 1.0);

        student.getEnrollments().addAll(Arrays.asList(e1, e2));
        assertEquals(10.0, strategy.calculate(student.getEnrollments()), 0.01);
    }

    @Test
    @DisplayName("T18: Cálculo GPA - Notas contrastantes (18 e 12)")
    void testGPAContrastGrades() {

        Enrollment e1 = new Enrollment(student, new Section(new Course("C1", 6), "H1"));
        e1.addGrade(18.0, 1.0);


        Enrollment e2 = new Enrollment(student, new Section(new Course("C2", 6), "H2"));
        e2.addGrade(12.0, 1.0);

        student.getEnrollments().addAll(Arrays.asList(e1, e2));
        assertEquals(15.0, strategy.calculate(student.getEnrollments()), 0.01);
    }
}