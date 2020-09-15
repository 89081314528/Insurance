package ru.julia;

public class ResultVpR {

    private final Loan buhLoan;
    private final Loan mfoLoan;


    public ResultVpR(Loan buhLoan, Loan mfoLoan) {
        this.buhLoan = buhLoan;
        this.mfoLoan = mfoLoan;
    }

    public Loan getBuhLoan() {
        return buhLoan;
    }

    public Loan getMfoLoan() {
        return mfoLoan;
    }
}
