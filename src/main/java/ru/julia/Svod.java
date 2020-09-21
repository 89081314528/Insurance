package ru.julia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сводная таблица. Суммирует значения для строк с одинаковой фамилией
 * Используя 2 вложенных цикла
 */
public class Svod {
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
        List<Loan> resultSvod = new ArrayList<>();
        resultSvod.add(buh.get(0));
        for (int i = 1; i < buh.size(); i++) {
            String fioBuh = buh.get(i).getFio();
            for (int j = 0; j < resultSvod.size(); j++) {
                String fioResultSvod = resultSvod.get(j).getFio();
                if (fioBuh.equals(fioResultSvod)) {
                    System.out.println("da" + " " + fioBuh + " " + fioResultSvod);
                    String sum = Integer.toString(Integer.parseInt(resultSvod.get(j).getSum()) + Integer.parseInt(buh.get(i).getSum()));
                    System.out.println(sum);
                    Loan loanWithDetermineSum  = resultSvod.get(j).withDetermineSum(sum);
                    resultSvod.remove(j);
                    resultSvod.add(loanWithDetermineSum);
                    break;
                }
                if (j == resultSvod.size() - 1) {
                    resultSvod.add(buh.get(i));
                    break;
                }
            }
        }

        PrintStream csv = new PrintStream("resultSvod");
        for (int i = 0; i < resultSvod.size(); i++) {
            csv.println(resultSvod.get(i).getFio() + ";" +
                    resultSvod.get(i).getSum());
        }
    }
}
