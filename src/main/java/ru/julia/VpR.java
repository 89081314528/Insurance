package ru.julia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VpR {
    public static void main(String[] args) throws IOException {
        List<String> buhCsv = Files.lines(new File("buh.csv").toPath())
                .collect(Collectors.toList());
        List<String> mfoCsv = Files.lines(new File("mfo.csv").toPath())
                .collect(Collectors.toList());


        List<Loan> buh = new ArrayList<>();
        List<Loan> mfo = new ArrayList<>();

        for (int i = 0; i < buhCsv.size(); i++) {
            String line = buhCsv.get(i);
            String[] loan = line.split(";");
            buh.add(new Loan(loan[0], loan[1]));
        }
        for (int i = 0; i < mfoCsv.size(); i++) {
            String line = mfoCsv.get(i);
            String[] loan = line.split(";");
            mfo.add(new Loan(loan[0], loan[1]));
        }
//        List<Loan> buhSvod = buhSvod(buh);
//        PrintStream csv = new PrintStream("resultVpR");
//        for (int i = 0; i < buhSvod.size(); i++) {
//            csv.println(buhSvod.get(i).getFio() + ";" + buhSvod.get(i).getSum());
//        }
    }

//    public static List<Loan> buhSvod(List<Loan> buh) {
//        List<Loan> buhSvod = new ArrayList<>();
//        buhSvod.add(buh.get(0));
//        for (int i = 1; i < buh.size(); i++) {
//            String buhFio = buh.get(i).getFio();
//            for (int j = 0; j < buhSvod.size(); j++) {
//                String buhSvodFio = buhSvod.get(j).getFio();
//                if (buhFio.equals(buhSvodFio)) {
//                    String a = Integer.toString(Integer.parseInt(buhSvod.get(j).getSum()) + Integer.parseInt(buh.get(i).getSum()));
//                    buhSvod.get(j).withDetermineSum(a);
//                    System.out.println("da");
//                    break;
//                } else {
//                    if (j == buhSvod.size() - 1) {
//                        buhSvod.add(buh.get(i));
//                    }
//                }
//            }
//        }
//        return buhSvod;
//    }

    public static void vpr(List<Loan> buh, List<Loan> mfo) throws FileNotFoundException {
        List<ResultVpR> resultVpRs = new ArrayList<>();
        for (int i = 0; i < buh.size(); ) {
            String buhLoanFio = buh.get(i).getFio();
            for (int j = 0; j < mfo.size(); j++) {
                String mfoLoanFio = mfo.get(j).getFio();
                if (buhLoanFio.equals(mfoLoanFio)) {
                    ResultVpR resultVpR = new ResultVpR(buh.get(i), mfo.get(j));
                    resultVpRs.add(resultVpR);
                    buh.remove(i);
                    mfo.remove(j);
                    break;
                }
                if (j == mfo.size() - 1) {
                    i = i + 1;
                }
            }
        }
        Loan nd = new Loan("ND", "ND");
        for (int i = 0; i < buh.size(); i++) {
            ResultVpR resultVpR = new ResultVpR(buh.get(i), nd);
            resultVpRs.add(resultVpR);
        }
        for (int i = 0; i < mfo.size(); i++) {
            ResultVpR resultVpR = new ResultVpR(nd, mfo.get(i));
            resultVpRs.add(resultVpR);
        }

        PrintStream csv = new PrintStream("resultVpR");
        for (int i = 0; i < resultVpRs.size(); i++) {
            csv.println(resultVpRs.get(i).getBuhLoan().getFio() + ";" +
                    resultVpRs.get(i).getBuhLoan().getSum() + ";" + resultVpRs.get(i).getMfoLoan().getFio() +
                    ";" + resultVpRs.get(i).getMfoLoan().getSum());
        }
    }
}
