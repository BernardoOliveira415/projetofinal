package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObserverPatternTest {

    @Test
    @DisplayName("T31: Student deve receber notificação ao adicionar nota (Observer Pattern)")
    void testObserverNotification() {

        Student s = new Student("S1", "João");
        Course c = new Course("PROG1", 6);
        Section sec = new Section(c, "MON 08:00");
        Enrollment e = new Enrollment(s, sec);


        e.addGrade(15.0, 1.0);


        assertFalse(s.getNotifications().isEmpty(), "O aluno deveria ter recebido uma notificação.");
        assertTrue(s.getNotifications().get(0).contains("15.0"), "A notificação deve conter o valor da nota.");
    }

    @Test
    @DisplayName("T32: Student deve acumular múltiplas notificações")
    void testMultipleNotifications() {
        Student s = new Student("S1", "João");
        Enrollment e = new Enrollment(s, new Section(new Course("C1", 6), "H1"));

        e.addGrade(10.0, 0.5);
        e.addGrade(20.0, 0.5);

        assertEquals(2, s.getNotifications().size(), "O aluno deveria ter 2 notificações acumuladas.");
    }

    @Test
    @DisplayName("T33: Mensagem de notificação deve conter o ID do curso")
    void testNotificationMessageContent() {

        Student s = new Student("S1", "Ricardo");
        Course c = new Course("MAT101", 6);
        Enrollment e = new Enrollment(s, new Section(c, "H1"));


        e.addGrade(15.0, 1.0); //


        String msg = s.getNotifications().get(0);
        assertTrue(msg.contains("MAT101"), "A notificação deve identificar o curso corretamente.");
    }

    @Test
    @DisplayName("T34: Isolamento - Notificações não devem ser cruzadas entre alunos")
    void testNotificationIsolation() {

        Student s1 = new Student("S1", "Ana");
        Student s2 = new Student("S2", "Beto");

        Enrollment e1 = new Enrollment(s1, new Section(new Course("C1", 6), "H1"));
        Enrollment e2 = new Enrollment(s2, new Section(new Course("C2", 6), "H2"));


        e1.addGrade(18.0, 1.0); // Notifica apenas s1


        assertEquals(1, s1.getNotifications().size());
        assertEquals(0, s2.getNotifications().size(), "O Aluno 2 não deveria receber notificações do Aluno 1.");
    }

    @Test
    @DisplayName("T35: Notificação de valores limite (Nota 0.0)")
    void testBoundaryNotification() {

        Student s = new Student("S1", "Marta");
        Enrollment e = new Enrollment(s, new Section(new Course("C1", 6), "H1"));


        e.addGrade(0.0, 1.0); //


        String msg = s.getNotifications().get(0);
        assertTrue(msg.contains("0.0"), "A notificação deve processar corretamente a nota mínima.");
    }

    @Test
    @DisplayName("T36: Consistência de histórico de notificações")
    void testNotificationHistoryOrder() {

        Student s = new Student("S1", "Tiago");
        Enrollment e = new Enrollment(s, new Section(new Course("C1", 6), "H1"));


        e.addGrade(10.0, 1.0);
        e.addGrade(20.0, 1.0);


        List<String> notes = s.getNotifications();
        assertTrue(notes.get(0).contains("10.0"), "A primeira notificação deve ser a primeira nota lançada.");
        assertTrue(notes.get(1).contains("20.0"), "A segunda notificação deve ser a segunda nota lançada.");
    }
}