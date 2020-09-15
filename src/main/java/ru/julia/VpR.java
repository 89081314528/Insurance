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
        vpr(buh, mfo);
    }


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
