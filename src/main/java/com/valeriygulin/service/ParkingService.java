package com.valeriygulin.service;

import com.valeriygulin.model.Auto;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingService {

    private long index;
    private CopyOnWriteArrayList<Auto> list = new CopyOnWriteArrayList<>();
    private ConcurrentLinkedQueue<Auto> queue = new ConcurrentLinkedQueue<>();

    private Thread genIn;
    private Thread genOut;
    private Thread statistic;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    private ExecutorService executorService3 = Executors.newSingleThreadExecutor();


    public void load(int parkingSize, int queueSize, int intervalGenIn, int intervalGenOut) throws InterruptedException {
        this.index = 1;
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int random = (int) (Math.random() * 8) + 1;
                        int autoSize;
                        if (random == 1 || random == 2 || random == 3 || random == 4
                                || random == 5 || random == 6) {
                            autoSize = 1;
                        } else {
                            autoSize = 2;
                        }
                        if (queue.size() <= queueSize) {
                            Auto auto = new Auto(index, autoSize);
                            queue.add(auto);
                            index++;

                            if (auto.getSize() == 1) {
                                System.out.println("Легковой автомобиль с id = " + auto.getId() + " встал в очередь на въезд.");
                            } else {
                                System.out.println("Грузовой автомобиль с id = " + auto.getId() + " встал в очередь на въезд.");
                            }
                            if (!queue.isEmpty()) {
                                parking(parkingSize);
                            }
                        } else {
                            System.out.println("Alarm");
                            executorService.shutdownNow();
                            executorService2.shutdownNow();
                            executorService3.shutdownNow();
                        }
                        Thread.sleep(intervalGenIn * 1000L);
                    }
                } catch (InterruptedException ex) {
                }
            }
        });
        this.executorService2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (!list.isEmpty()) {
                            int random = (int) (Math.random() * list.size());
                            Auto remove = list.remove(random);
                            if (remove.getSize() == 1) {
                                System.out.println("Легковой автомобиль с id = " + remove.getId() + " покинул парковку.");
                            } else {
                                System.out.println("Грузовой автомобиль с id = " + remove.getId() + " покинул парковку.");
                            }
                            if (!queue.isEmpty()) {
                                parking(parkingSize);
                            }
                        }
                        Thread.sleep(intervalGenOut * 1000L);
                    }
                } catch (InterruptedException ex) {
                }
            }
        });

        this.executorService3.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int places = Math.abs(list.size() - parkingSize);
                        System.out.println("Свободных мест: " + places);
                        int count1 = 0;
                        int count2 = 0;
                        for (Auto auto : list) {
                            if (auto.getSize() == 1) {
                                count1++;
                            } else {
                                count2++;
                            }
                        }
                        System.out.println("Занято мест: " + count1 + " из них легковых и " + count2 + " грузовых авто");
                        int size = queue.size();
                        System.out.println("Автомобилей, ожидающих в очереди: " + size);
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                }
            }
        });
    }

    //TODO написать метод который загоняет машину из очереди на паркинг в случае если паркинг имеет свободные мест
// данный метод вызывать в первом и во втором потоке
    public synchronized void parking(int parkingSize) {
        Iterator<Auto> iterator = queue.iterator();
        int size = iterator.next().getSize();
        if (parkingSize - list.size() >= size) {
            Auto autoFromQue = queue.remove();
            list.add(autoFromQue);
            if (autoFromQue.getSize() == 1) {
                System.out.println("Легковой автомобиль с id = " + autoFromQue.getId() + " припарковался.");
            } else {
                System.out.println("Грузовой автомобиль с id = " + autoFromQue.getId() + " припарковался.");
            }
        }
    }
}



