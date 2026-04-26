package ru.yandex.practicum;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;
import ru.yandex.practicum.delivery.ParcelBox;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    // --- calculateDeliveryCost ---

    @Test
    public void standardCostIsCorrect() {
        StandardParcel p = new StandardParcel("Книга", 5.0, "Москва", 1);
        assertEquals(10.0, p.calculateDeliveryCost());
    }

    @Test
    public void fragileCostIsCorrect() {
        FragileParcel p = new FragileParcel("Ваза", 3.0, "СПб", 1);
        assertEquals(12.0, p.calculateDeliveryCost());
    }

    @Test
    public void perishableCostIsCorrect() {
        PerishableParcel p = new PerishableParcel("Торт", 4.0, "Казань", 1, 3);
        assertEquals(12.0, p.calculateDeliveryCost());
    }

    @Test
    public void zeroCostForZeroWeight() {
        StandardParcel p = new StandardParcel("Бумага", 0.0, "Тверь", 1);
        assertEquals(0.0, p.calculateDeliveryCost());
    }

    // --- isExpired ---

    @Test
    public void parcelIsNotExpired() {
        PerishableParcel p = new PerishableParcel("Пирог", 2.0, "Урал", 1, 3);
        assertFalse(p.isExpired(3));
    }

    @Test
    public void parcelIsExpired() {
        PerishableParcel p = new PerishableParcel("Пирог", 2.0, "Урал", 1, 3);
        assertTrue(p.isExpired(5));
    }

    @Test
    public void parcelIsNotExpiredOnBoundary() {
        PerishableParcel p = new PerishableParcel("Молоко", 1.0, "Омск", 5, 2);
        assertFalse(p.isExpired(7));
    }


    @Test
    public void parcelAddedWhenWeightIsWithinLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(20);
        StandardParcel p = new StandardParcel("Носки", 5.0, "Тула", 1);
        box.addParcel(p);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void parcelNotAddedWhenWeightExceedsLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(4);
        StandardParcel p = new StandardParcel("Гири", 5.0, "Тула", 1);
        box.addParcel(p);
        assertEquals(0, box.getAllParcels().size());
    }

    @Test
    public void parcelAddedWhenWeightIsExactLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(5);
        StandardParcel p = new StandardParcel("Словарь", 5.0, "Тула", 1);
        box.addParcel(p);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void secondParcelNotAddedWhenTotalExceedsLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(8);
        box.addParcel(new StandardParcel("Первая", 5.0, "Тула", 1));
        box.addParcel(new StandardParcel("Вторая", 5.0, "Тула", 1));
        assertEquals(1, box.getAllParcels().size());
    }

}
