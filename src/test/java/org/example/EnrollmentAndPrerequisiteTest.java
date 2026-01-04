package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;



class EnrollmentAndPrerequisiteTest {
    private Student student;
    private Course p1, p2, p3;
    private Section s1, s2;

    @BeforeEach
    void setUp() {

        student = new Student("S1", "João");
        p1 = new Course("PROG1", 6);
        p2 = new Course("PROG2", 6);
        p3 = new Course("PROG3", 6);

        p2.addPrerequisite(p1);
        p3.addPrerequisite(p2);

        s1 = new Section(p1, "SEG 08:00");
        s2 = new Section(p2, "TER 10:00");
    }

    @Test
    @DisplayName("T5: Deve permitir inscrição sem pré-requisitos")
    void testEnrollmentNoPrereq() {

        Enrollment e = new Enrollment(student, s1);
        student.getEnrollments().add(e);


        assertEquals(1, student.getEnrollments().size(), "O tamanho da lista deve ser 1.");
    }

    @Test
    @DisplayName("T6-T10: Testes de Cadeia de Pré-requisitos (White-Box)")
    void testPrerequisiteChain() {

        boolean canEnrollP2 = checkPrerequisites(student, p2);
        assertFalse(canEnrollP2, "Bloqueio: P2 exige P1.");


        student.getEnrollments().add(new Enrollment(student, s1));
        assertTrue(checkPrerequisites(student, p2), "Sucesso: P1 concluído.");
    }


    private boolean checkPrerequisites(Student s, Course c) {
        if (c.getPrerequisites().isEmpty()) return true;

        for (Course prereq : c.getPrerequisites()) {

            boolean completed = s.getEnrollments().stream()
                    .anyMatch(e -> e.getSection().getCourse().getId().equals(prereq.getId()));
            if (!completed) return false;
        }
        return true;
    }
}