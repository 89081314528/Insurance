package ru.julia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * убрать полные дубли (строки, которые полностью совпадают друг с другом)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> csv = Files.lines(new File("person.csv").toPath())
                .collect(Collectors.toList());

        Map<String, Integer> map = new HashMap<String, Integer>();
        String key;
        for (int i = 0; i < csv.size(); i++) {
            key = csv.get(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
//        System.out.println(csv);
        List <String> newCsv = new ArrayList<>(map.keySet());
        for (int i = 0; i < newCsv.size(); i++) {
            System.out.println(newCsv.get(i));
        }
    }
}
