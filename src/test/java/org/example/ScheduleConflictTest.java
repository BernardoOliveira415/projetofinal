package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ScheduleConflictTest {



    @Test
    @DisplayName("T21: Conflito - Mesma hora e mesmo dia (Segunda)")
    void testConflictSameTimeMonday() {
        Section s1 = new Section(new Course("C1", 6), "MON 08:00");
        Section s2 = new Section(new Course("C2", 6), "MON 08:00");
        assertEquals(s1.getTimeslot(), s2.getTimeslot(), "Devem ter o mesmo slot.");
    }

    @Test
    @DisplayName("T24: Conflito - Mesma hora na Quarta-feira (WED)")
    void testConflictWednesday() {
        Section s1 = new Section(new Course("C3", 6), "WED 14:00");
        Section s2 = new Section(new Course("C4", 6), "WED 14:00");
        assertTrue(s1.getTimeslot().equals(s2.getTimeslot()), "Deveria haver conflito à quarta-feira.");
    }

    @Test
    @DisplayName("T25: Conflito - Slot de final de dia na Sexta-feira (FRI)")
    void testConflictFridayLate() {
        Section s1 = new Section(new Course("C5", 6), "FRI 18:00");
        Section s2 = new Section(new Course("C6", 6), "FRI 18:00");
        assertEquals(s1.getTimeslot(), s2.getTimeslot());
    }



    @Test
    @DisplayName("T22: Sem conflito - Dias diferentes (MON vs TUE)")
    void testNoConflictDifferentDays() {
        Section s1 = new Section(new Course("C1", 6), "MON 08:00");
        Section s2 = new Section(new Course("C2", 6), "TUE 08:00");
        assertNotEquals(s1.getTimeslot(), s2.getTimeslot());
    }

    @Test
    @DisplayName("T23: Sem conflito - Mesma segunda mas horas diferentes")
    void testNoConflictSameDayDifferentHours() {
        Section s1 = new Section(new Course("C1", 6), "MON 08:00");
        Section s2 = new Section(new Course("C2", 6), "MON 10:00");
        assertNotEquals(s1.getTimeslot(), s2.getTimeslot());
    }

    @Test
    @DisplayName("T26: Sem conflito - Quinta-feira em horários distintos")
    void testNoConflictThursdaySlots() {
        Section s1 = new Section(new Course("C1", 6), "THU 09:00");
        Section s2 = new Section(new Course("C2", 6), "THU 11:00");
        assertNotEquals(s1.getTimeslot(), s2.getTimeslot());
    }



    @Test
    @DisplayName("T27: Validação em lista - Adicionar curso sem conflito")
    void testListNoConflict() {
        List<Section> schedule = new ArrayList<>();
        schedule.add(new Section(new Course("C1", 6), "MON 08:00"));
        schedule.add(new Section(new Course("C2", 6), "WED 08:00"));

        Section newSection = new Section(new Course("C3", 6), "FRI 08:00");
        boolean hasConflict = schedule.stream().anyMatch(s -> s.getTimeslot().equals(newSection.getTimeslot()));

        assertFalse(hasConflict, "Não deveria haver conflito com a sexta-feira.");
    }

    @Test
    @DisplayName("T28: Validação em lista - Detetar conflito numa agenda cheia")
    void testListWithConflict() {
        List<Section> schedule = new ArrayList<>();
        schedule.add(new Section(new Course("C1", 6), "MON 08:00"));
        schedule.add(new Section(new Course("C2", 6), "TUE 08:00"));

        Section conflicting = new Section(new Course("C3", 6), "MON 08:00");
        boolean hasConflict = schedule.stream().anyMatch(s -> s.getTimeslot().equals(conflicting.getTimeslot()));

        assertTrue(hasConflict, "Deveria detetar o conflito com a segunda-feira.");
    }

    @Test
    @DisplayName("T29: Robustez - Agenda vazia não deve ter conflitos")
    void testEmptyScheduleNoConflict() {
        List<Section> schedule = new ArrayList<>();
        Section newSection = new Section(new Course("C1", 6), "MON 08:00");

        boolean hasConflict = schedule.stream().anyMatch(s -> s.getTimeslot().equals(newSection.getTimeslot()));
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("T30: Caso Sensível - Espaços e Formatação")
    void testCaseSensitiveFormat() {
        Section s1 = new Section(new Course("C1", 6), "MON 08:00");
        Section s2 = new Section(new Course("C2", 6), "mon 08:00");


        assertNotEquals(s1.getTimeslot(), s2.getTimeslot(), "Strings com casing diferente são tratadas como diferentes.");
    }
}