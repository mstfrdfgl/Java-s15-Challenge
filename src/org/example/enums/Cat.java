package org.example.enums;

public enum Cat {
    NOVEL(18.81),
    SELF_IMPROVEMENT(19.38),
    CHILD(19.23),
    ENCYCLOPEDIA(19.98),
    PHILOSOPHY(20.24),
    HISTORY(14.53),
    SCIENCE_FICTION(19.07),
    MIDDLE_EARTH(20.07);

    private final double price;

    Cat(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
