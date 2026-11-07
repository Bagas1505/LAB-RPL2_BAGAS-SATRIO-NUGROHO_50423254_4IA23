package service;

public abstract class Transaction {
    private String transactionId;
    private String date;

    public Transaction(String transactionId, String date) {
        this.transactionId = transactionId;
        this.date = date;
    }

    public String getTransactionId() { return transactionId; }
    public String getDate() { return date; }

    // Method abstrak yang wajib diimplementasikan oleh kelas anak
    public abstract void processTransaction();
}