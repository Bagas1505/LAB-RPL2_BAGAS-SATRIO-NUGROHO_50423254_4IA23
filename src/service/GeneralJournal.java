package service;

import model.Account;

public class GeneralJournal extends Transaction {
    private Account account;
    private double amount;
    private boolean isDebit;

    public GeneralJournal(String transactionId, String date, Account account, double amount, boolean isDebit) {
        super(transactionId, date); // Memanggil constructor dari kelas induk (Transaction)
        this.account = account;
        this.amount = amount;
        this.isDebit = isDebit;
    }

    @Override
    public void processTransaction() {
        account.updateBalance(amount, isDebit);
        String type = isDebit ? "DEBIT" : "KREDIT";
        System.out.println("LOG JURNAL [" + getTransactionId() + "]: " + type + " Rp " + amount + " pada " + account.getAccountName());
    }
}