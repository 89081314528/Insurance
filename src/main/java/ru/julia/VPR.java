package ru.julia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VPR {
    public static void main(String[] args) throws IOException {
        List<String> firstIssuance = Files.lines(new File("58.03.1C.csv").toPath())
                .collect(Collectors.toList());
        List<String> firstRepayment = Files.lines(new File("58.03.MFO.csv").toPath())
                .collect(Collectors.toList());


        List<Loan> buh = new ArrayList<>();
        List<Loan> mfo = new ArrayList<>();
        List<ResaltVPR> resaltVPRS = new ArrayList<>();

        for (int i = 0; i < firstIssuance.size(); i++) {
            String line = firstIssuance.get(i);
            String[] loan = line.split(";");
            buh.add(new Loan(loan[0], loan[1]));
        }
        for (int i = 0; i < firstRepayment.size(); i++) {
            String line = firstRepayment.get(i);
            String[] loan = line.split(";");
            mfo.add(new Loan(loan[0], loan[1]));
        }
        for (int i = 0; i < buh.size(); i++) {
         String buhLoanFio = buh.get(i).getFio();
            for (int j = 0; j < mfo.size(); j++) {
                String mfoLoanFio = mfo.get(j).getFio();
                if (buhLoanFio.equals(mfoLoanFio)) {
                    ResaltVPR resaltVPR = new ResaltVPR(buh.get(i),mfo.get(j));
                    resaltVPRS.add(resaltVPR);
                    buh.remove(buh.get(i));
                    i = i - 1;
                    mfo.remove(mfo.get(j));
                    break;
                }
            }
        }
        Loan nd = new Loan("ND", "ND");
        for (int i = 0; i < buh.size(); i++) {
            ResaltVPR resaltVPR = new ResaltVPR(buh.get(i),nd);
            resaltVPRS.add(resaltVPR);
        }
        for (int i = 0; i < mfo.size(); i++) {
            ResaltVPR resaltVPR = new ResaltVPR(nd, mfo.get(i));
            resaltVPRS.add(resaltVPR);
        }

        for (int i = 0; i < resaltVPRS.size(); i++) {
            System.out.println(resaltVPRS.get(i).getBuhLoan().getFio() + " " +
                    resaltVPRS.get(i).getBuhLoan().getSum() + " " + resaltVPRS.get(i).getMfoLoan().getFio() +
                    " " + resaltVPRS.get(i).getMfoLoan().getSum());
        }
    }
}
