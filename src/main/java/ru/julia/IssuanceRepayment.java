package ru.julia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssuanceRepayment {
    public static void main(String[] args) throws IOException {
        List<String> firstIssuance = Files.lines(new File("76.10.csv").toPath())
                .collect(Collectors.toList());
        List<String> firstRepayment = Files.lines(new File("76.TP.csv").toPath())
                .collect(Collectors.toList());


        List<Loan> loanFirstIssuance = new ArrayList<>();
        List<Loan> loanFirstRepayment = new ArrayList<>();

        for (int i = 0; i < firstIssuance.size(); i++) {
            String line = firstIssuance.get(i);
            String[] loan = line.split(";");
            loanFirstIssuance.add(new Loan(loan[0], loan[1]));
        }
        for (int i = 0; i < firstRepayment.size(); i++) {
            String line = firstRepayment.get(i);
            String[] loan = line.split(";");
            loanFirstRepayment.add(new Loan(loan[0], loan[1]));
        }


        Map<String, String> mapFirstIssuance = new HashMap<>();
        Map<String, String> mapFirstRepayment = new HashMap<>();

        for (int i = 0; i < loanFirstIssuance.size(); i++) {
            mapFirstIssuance.put(loanFirstIssuance.get(i).getFio(), loanFirstIssuance.get(i).getSum());
        }
        for (int i = 0; i < loanFirstRepayment.size(); i++) {
            mapFirstRepayment.put(loanFirstRepayment.get(i).getFio(), loanFirstRepayment.get(i).getSum());
        }

        List<Loan> issuance = new ArrayList<>();
        List<Loan> repayment = new ArrayList<>();
        List<Loan> questions = new ArrayList<>();

        for (int i = 0; i < loanFirstIssuance.size(); i++) {
            if (loanFirstIssuance.get(i).getSum().startsWith("-")) {
                repayment.add(loanFirstIssuance.get(i));
            } else
                if (mapFirstIssuance.containsKey(loanFirstIssuance.get(i).getFio()) &&
                        mapFirstRepayment.containsKey(loanFirstIssuance.get(i).getFio())
                        && mapFirstIssuance.get(loanFirstIssuance.get(i).getFio()).
                        equals(mapFirstRepayment.get(loanFirstIssuance.get(i).getFio()))) {

                } else
                if (mapFirstIssuance.containsKey(loanFirstIssuance.get(i).getFio()) &&
                        mapFirstRepayment.containsKey(loanFirstIssuance.get(i).getFio())
                        && !mapFirstIssuance.get(loanFirstIssuance.get(i).getFio()).
                        equals(mapFirstRepayment.get(loanFirstIssuance.get(i).getFio()))) {
                    questions.add(loanFirstIssuance.get(i));
                } else {
                    issuance.add(loanFirstIssuance.get(i));
                }
            }
        for (int i = 0; i < loanFirstRepayment.size(); i++) {
            if (mapFirstIssuance.containsKey(loanFirstRepayment.get(i).getFio()) &&
                    mapFirstRepayment.containsKey(loanFirstRepayment.get(i).getFio())) {
            } else {
                repayment.add(loanFirstRepayment.get(i));
            }
        }
        for (int i = 0; i < issuance.size(); i++) {
            System.out.println(issuance.get(i).getFio());
            System.out.println(issuance.get(i).getSum());
        }
    }
}
