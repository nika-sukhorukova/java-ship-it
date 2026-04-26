package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(40);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: addParcel();      break;
                case 2: sendParcels();    break;
                case 3: calculateCosts(); break;
                case 4: updateTracking(); break;
                case 5: showBox();        break;
                case 0: running = false;  break;
                default: System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение (трекинг)");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Тип: 1 — Стандартная, 2 — Хрупкая, 3 — Скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Описание: ");
        String desc = scanner.nextLine();
        System.out.print("Вес: ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Адрес: ");
        String addr = scanner.nextLine();
        System.out.print("День отправки: ");
        int day = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                StandardParcel sp = new StandardParcel(desc, weight, addr, day);
                allParcels.add(sp);
                standardBox.addParcel(sp);
                break;
            case 2:
                FragileParcel fp = new FragileParcel(desc, weight, addr, day);
                allParcels.add(fp);
                fragileBox.addParcel(fp);
                trackableParcels.add(fp);
                break;
            case 3:
                System.out.print("Срок хранения (дней): ");
                int ttl = Integer.parseInt(scanner.nextLine());
                PerishableParcel pp = new PerishableParcel(desc, weight, addr, day, ttl);
                allParcels.add(pp);
                perishableBox.addParcel(pp);
                break;
            default:
                System.out.println("Неверный тип.");
        }
    }

    private static void sendParcels() {
        for (Parcel p : allParcels) {
            p.packageItem();
            p.deliver();
        }
    }

    private static void calculateCosts() {
        double total = 0;
        for (Parcel p : allParcels) {
            total += p.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + total);
    }

    private static void updateTracking() {
        System.out.print("Новое местоположение: ");
        String location = scanner.nextLine();
        for (Trackable t : trackableParcels) {
            t.reportStatus(location);
        }
    }

    private static void showBox() {
        System.out.println("Коробка: 1 — Стандартные, 2 — Хрупкие, 3 — Скоропортящиеся");
        int choice = Integer.parseInt(scanner.nextLine());
        List<? extends Parcel> list;
        switch (choice) {
            case 1: list = standardBox.getAllParcels();   break;
            case 2: list = fragileBox.getAllParcels();    break;
            case 3: list = perishableBox.getAllParcels(); break;
            default: System.out.println("Неверный выбор."); return;
        }
        for (Parcel p : list) {
            System.out.println(" - " + p.getDescription() + " (" + p.getWeight() + " кг)");
        }
    }

}

