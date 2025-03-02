package edu.baylor.ecs.si;

public class RoadBikeService extends BasicService {
    
    @Override
    public void acceptBike(Bicycle bike) {
        System.out.println("Fixing Bicycle");
    }

    public void acceptBike(RoadBike bike) {
        System.out.println("Fixing RoadBike");
    }
    
}