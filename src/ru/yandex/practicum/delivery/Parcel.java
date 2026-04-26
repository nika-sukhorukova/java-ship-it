package ru.yandex.practicum.delivery;

public abstract class Parcel {
    private String description;
    private double weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public  void packageItem() {
        System.out.println("Посылка <<" + getDescription() + ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + getDescription() + ">> доставлена по адресу <<" + getDeliveryAddress() + ">>");
    }

    public abstract double calculateDeliveryCost();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }
}