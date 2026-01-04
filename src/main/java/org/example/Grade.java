package org.example;

public class Grade {
    private double value, weight;
    public Grade(double v, double w) { this.value = v; this.weight = w; }
    public double getWeightedValue() { return value * weight; }
}