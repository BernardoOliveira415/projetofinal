package org.example;

public class UserFactory {
    public static User createUser(String type, String id, String name) {
        if (type.equalsIgnoreCase("STUDENT")) return new Student(id, name);
        if (type.equalsIgnoreCase("INSTRUCTOR")) return new Instructor(id, name);
        throw new IllegalArgumentException("Tipo inválido");
    }
}