package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_code", nullable = false, length = 50)
    private String accountCode;

    @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;

    @Column(name = "balance")
    private double balance;

    // Constructor Kosong (Wajib ada untuk Hibernate)
    public Account() {
    }

    // Constructor dengan Parameter
    public Account(String accountCode, String accountName, double balance) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.balance = balance;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Logika Bisnis Tambahan (Dipertahankan dari kode lama Anda)
    public void updateBalance(double amount, boolean isDebit) {
        if (isDebit) {
            this.balance += amount;
        } else {
            this.balance -= amount;
        }
    }
}