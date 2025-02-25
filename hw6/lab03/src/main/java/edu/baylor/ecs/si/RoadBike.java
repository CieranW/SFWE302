package edu.baylor.ecs.si;

public abstract class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int startCadence, int startSpeed, int startGear, int newTireWidth) {
        super(startCadence, startSpeed, startGear);
        this.tireWidth = newTireWidth;
    }

    public int getTireWidth() { return this.tireWidth; }

    public void setTireWidth(int newTireWidth) { this.tireWidth = newTireWidth; }

    @Override 
    public void printDescription() {
        super.printDescription();
        System.out.println("The road bike has a tire width of " + this.tireWidth + "mm.");
    }
}
