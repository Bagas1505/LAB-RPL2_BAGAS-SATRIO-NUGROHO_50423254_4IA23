package TestConnection;

import database.HibernateUtil;
import model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

    public static void main(String[] args) {
        // 1. Buka Session dari HibernateUtil
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            // 2. Mulai Transaksi
            tx = session.beginTransaction();

            // 3. Buat objek Account baru (Tanpa query SQL manual)
            Account accBaru = new Account("103", "Bank BCA", 15000000.00);

            // 4. Simpan objek menggunakan Hibernate ORM
            session.save(accBaru);

            // 5. Commit perubahan ke database
            tx.commit();
            System.out.println("\nSUCCESS: Berhasil menyimpan data akun baru via Hibernate ORM!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Gagal menyimpan data: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 6. Tutup Session
            session.close();
            HibernateUtil.shutdown();
        }
    }
}