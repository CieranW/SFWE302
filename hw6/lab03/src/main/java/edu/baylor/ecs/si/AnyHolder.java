package edu.baylor.ecs.si;

public class AnyHolder<T extends Bicycle> {
    private T bicycle;

    public AnyHolder(T bicycle) {
        this.bicycle = bicycle;
    }

    public T getBicycle() {
        return bicycle;
    }
}
