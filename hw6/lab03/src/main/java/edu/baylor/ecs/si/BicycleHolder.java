package edu.baylor.ecs.si;

public class BicycleHolder {
    private Bicycle bicycle;

    public BicycleHolder(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }
}
