package ru.julia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сводная таблица. Суммирует значения для строк с одинаковой фамилией
 * Используя мап
 */

public class Svod2 {
    public static void main(String[] args) throws IOException {
        List<String> buhCsv = Files.lines(new File("buh.csv").toPath())
                .collect(Collectors.toList());

        List<Loan> buh = new ArrayList<>();

        for (int i = 0; i < buhCsv.size(); i++) {
            String line = buhCsv.get(i);
            String[] loan = line.split(";");
            buh.add(new Loan(loan[0], loan[1]));
        }
        svod(buh);
    }

    public static void svod(List<Loan> buh) throws FileNotFoundException {
        Map<String, String> mapBuh = new HashMap<>();
        for (int i = 0; i < buh.size(); i++) {
            String fio = buh.get(i).getFio();
            String sum = buh.get(i).getSum();
            if (mapBuh.containsKey(fio)) {
                mapBuh.put(fio,
                        Integer.toString(Integer.parseInt(mapBuh.get(fio)) + Integer.parseInt(sum)));
            } else {
                mapBuh.put(fio, sum);
            }
        }
        System.out.println(mapBuh.entrySet());
    }
}
