package service;

import java.util.List;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AccountRepository;

@Service
public class AccountService {

    // Constructor Injection (Dependency Injection)
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Declarative Transaction: otomatis commit jika sukses, rollback jika gagal
    @Transactional
    public void buatAkunBaru(String kode, String nama, double saldoAwal) {
        Account akun = new Account(kode, nama, saldoAwal);
        accountRepository.save(akun);
    }

    @Transactional
    public void catatTransaksi(Account akun, double nominal, boolean isDebit) {
        akun.updateBalance(nominal, isDebit);
        accountRepository.save(akun);
    }

    @Transactional(readOnly = true)
    public List<Account> ambilSemuaAkun() {
        return accountRepository.findAll();
    }
}