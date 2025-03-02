package edu.baylor.ecs.si;
import java.util.ArrayList;
import java.util.List;


public class AnyCarHolder {
    private List<AnyHolder<Bicycle>> holders = new ArrayList<>(4);

    public void addBike(Bicycle bike) {
        holders.add(new AnyHolder<Bicycle>(bike));
    }

    // public void printBikes() {
    //     for (AnyHolder<Bicycle> holder : holders) {
    //         holder.printDescription();
    //     }
    // }
}
