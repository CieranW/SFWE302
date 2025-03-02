package edu.baylor.ecs.si;

public class MountainBikeService extends BasicService {
    
    @Override
    public void acceptBike(Bicycle bike) {
        System.out.println("Fixing Bicycle");
    }

    public void acceptBike(MountainBike bike) {
        System.out.println("Fixing MountainBike");
    }
    
}
