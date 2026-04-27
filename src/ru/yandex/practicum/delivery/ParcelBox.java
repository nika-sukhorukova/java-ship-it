package ru.yandex.practicum.delivery;

import  java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> parcelBoxes;
    private double maxWeight;

    public ParcelBox(double maxWight) {
        this.parcelBoxes = new ArrayList<>();
        this.maxWeight = maxWight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void addParcel(T parcel) {
        double currentWeight = 0;
        for (T p : parcelBoxes) {
            currentWeight += p.getWeight();
        }

        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcelBoxes.add(parcel);
            System.out.println("Посылка <<" + parcel.getDescription() + ">> добавлена в коробку.");
        } else {
            System.out.println("Превышен максимальный вес коробки: " + getMaxWeight() + " кг.");
        }
    }

    public ArrayList<T> getAllParcels()  {
        return parcelBoxes;
    }

}
