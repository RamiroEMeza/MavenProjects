package com.solvd.laba.enums;

public enum Planets {
    VENUS(99.5, 2.3, 2), MERCURY(78.1, 3.7, 1);

    private double radius;
    private double gravitation;
    private int order;

    Planets(double radius, double gravitation, int order) {
        this.radius = radius;
        this.gravitation = gravitation;
        this.order = order;
    }

    public double getRadius() {
        return radius;
    }

    public double getGravitation() {
        return gravitation;
    }

    public int getOrder() {
        return order;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setGravitation(float gravitation) {
        this.gravitation = gravitation;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
