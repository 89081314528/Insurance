package ru.julia;

public class Loan {
    private final String fio;
    private final String sum;


    public String getFio() {
        return fio;
    }

    public String getSum() {
        return sum;
    }

    public Loan(String fio, String sum) {
        this.fio = fio;
        this.sum = sum;
    }

    public Loan withDetermineSum(String a) {
        return new Loan(this.getFio(), a);
    }
}
