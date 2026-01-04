package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== University Management System ===");


        Student s = (Student) UserFactory.createUser("STUDENT", "202401", "Ricardo");


        Course p1 = new Course("PROG1", 6);
        Course p2 = new Course("PROG2", 6);
        p2.addPrerequisite(p1);


        System.out.println("Aluno: " + s.id + " - " + s.name);
        Section sec1 = new Section(p1, "MON 08:00");
        Enrollment e1 = new Enrollment(s, sec1);


        e1.addGrade(18.5, 1.0);


        WeightedGPAStrategy strategy = new WeightedGPAStrategy();
        s.getEnrollments().add(e1);
        System.out.println("GPA Atual: " + strategy.calculate(s.getEnrollments()));
    }
}