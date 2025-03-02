package edu.baylor.ecs.si;

public enum BikeColor {
    RED("Red", 1),
    BLUE("Blue", 2),
    GREEN("Green", 3),
    YELLOW("Yellow", 4),
    BLACK("Black", 5),
    WHITE("White", 6),
    ORANGE("Orange", 7),
    PURPLE("Purple", 8),
    PINK("Pink", 9),
    BROWN("Brown", 10);

    private final String colorName;
    private final int colorID;


    BikeColor(String colorName, int colorID) {
        this.colorName = colorName;
        this.colorID = colorID;
    }

    @Override
    public String toString() {
        return colorName + " (" + colorID + ")";
    }
}
