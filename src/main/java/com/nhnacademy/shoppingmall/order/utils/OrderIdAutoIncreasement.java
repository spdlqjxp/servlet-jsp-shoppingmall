package com.nhnacademy.shoppingmall.order.utils;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderIdAutoIncreasement {
    private static final String ID_FILE_PATH = "order_id.dat";
    private static final AtomicInteger currentId = new AtomicInteger(loadIdFromFile());

    // ID를 가져오면서 자동 증가
    public static String nextId() {
        int next = currentId.incrementAndGet();
        saveIdToFile(next);
        return String.format("O%05d", next);
    }

    private static int loadIdFromFile() {
        File file = new File(ID_FILE_PATH);
        if (!file.exists()) {
            return 0;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void saveIdToFile(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE_PATH))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}