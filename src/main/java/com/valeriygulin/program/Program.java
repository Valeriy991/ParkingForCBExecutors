package com.valeriygulin.program;

import com.valeriygulin.service.ParkingService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в программу симуляции автопарковки!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество парковочных мест");
        int parkingSize = scanner.nextInt();
        System.out.println("Введите максимальную длину очереди автомобилей ожидающих въезда на парковку");
        int queueSize = scanner.nextInt();
        System.out.println("Введите интервал генерации входящих автомобилей в секундах");
        int intervalGenIn = scanner.nextInt();
        System.out.println("Введите интервал генерации выходящих автомобилей в секундах");
        int intervalGenOut = scanner.nextInt();
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.load(parkingSize, queueSize, intervalGenIn, intervalGenOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
