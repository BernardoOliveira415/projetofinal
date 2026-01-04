package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class UserAndModelTest {

    @Test
    @DisplayName("T1: Factory deve criar um Student corretamente")
    void testStudentCreation() {
        User student = UserFactory.createUser("STUDENT", "S001", "Ana");
        assertTrue(student instanceof Student);
        assertEquals("S001", student.getId());
    }

    @Test
    @DisplayName("T2: Factory deve criar um Instructor corretamente")
    void testInstructorCreation() {
        User instructor = UserFactory.createUser("INSTRUCTOR", "I001", "Prof. Silva");
        assertTrue(instructor instanceof Instructor);
    }

    @Test
    @DisplayName("T3: Factory deve lançar exceção para tipo inválido")
    void testInvalidUserType() {
        assertThrows(IllegalArgumentException.class, () -> UserFactory.createUser("PILOT", "P01", "Nuno"));
    }

    @Test
    @DisplayName("T4: Course deve armazenar créditos corretamente")
    void testCourseCredits() {
        Course c = new Course("CS101", 6);
        assertEquals(6, c.getCredits());
    }
}