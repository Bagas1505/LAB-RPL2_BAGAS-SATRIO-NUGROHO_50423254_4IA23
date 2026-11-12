package TestConnection;

import java.util.List;
import model.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

public class TestSpring {

    public static void main(String[] args) {
        System.out.println("Memulai Spring IoC Container...");

        // 1. Memuat konfigurasi dari applicationContext.xml
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {

            // 2. Mengambil Bean AccountService yang sudah di-inject oleh Spring
            AccountService accountService = context.getBean(AccountService.class);

            // 3. Menyimpan akun baru menggunakan @Transactional
            accountService.buatAkunBaru("102-SPR", "Kas Bank Spring", 5000000.0);
            System.out.println("SUCCESS: Data akun berhasil disimpan melalui Spring Framework!");

            // 4. Menampilkan seluruh data akun dari MySQL
            List<Account> daftarAkun = accountService.ambilSemuaAkun();
            System.out.println("=== Daftar Akun di Database ===");
            for (Account acc : daftarAkun) {
                System.out.println(acc.getAccountCode() + " | " + acc.getAccountName() + " | Rp " + acc.getBalance());
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan pada Spring: " + e.getMessage());
            e.printStackTrace();
        }
    }
}