package edu.baylor.ecs.si;
import java.util.ArrayList;
import java.util.List;

public class Car {
    List<BicycleHolder> carHolders = new ArrayList<BicycleHolder>(4);

    // @Override
    public void acceptBike(Bicycle bike) {
        System.out.println("Fixing Bicycle");
        carHolders.add(new BicycleHolder(bike));
    }

    public void acceptBike(MountainBike bike) {
        System.out.println("Fixing MountainBike");
        carHolders.add(new MountainBikeHolder(bike));
    }

    public void acceptBike(RoadBike bike) {
        System.out.println("Fixing RoadBike");
        carHolders.add(new RoadBikeHolder(bike));
    }


}
