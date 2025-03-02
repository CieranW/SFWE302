package edu.baylor.ecs.si;

public interface BicycleVisitor {
    public void acceptBike(Bicycle b);
    public void acceptBike(MountainBike b);
    public void acceptBike(RoadBike b);
}
