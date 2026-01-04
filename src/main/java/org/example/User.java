package org.example;


public abstract class User {
    protected String id, name;
    public User(String id, String name) { this.id = id; this.name = name; }
    public String getId() { return id; }
}
