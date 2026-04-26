package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private int timeToLive;
    private final int BASE_COST = 3;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public double calculateDeliveryCost() {
        return getWeight() * BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        return getSendDay() + timeToLive < currentDay;
    }
}
