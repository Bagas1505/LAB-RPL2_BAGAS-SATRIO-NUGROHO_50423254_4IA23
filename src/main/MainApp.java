package main;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        do {
            System.out.println("\n=================================");
            System.out.println("   APLIKASI AKUNTANSI ACCOUNTRACE ");
            System.out.println("=================================");
            System.out.println("1. Tambah Akun Baru (Account)");
            System.out.println("2. Lihat Daftar Akun");
            System.out.println("3. Catat Transaksi Jurnal Umum");
            System.out.println("4. Lihat Buku Jurnal Umum");
            System.out.println("5. Lihat Laporan Laba Rugi");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer newline

                switch (pilihan) {
                    case 1:
                        tambahAkun(scanner);
                        break;
                    case 2:
                        tampilkanAkun();
                        break;
                    case 3:
                        catatJurnal(scanner);
                        break;
                    case 4:
                        tampilkanJurnal();
                        break;
                    case 5:
                        tampilkanLaporanLabaRugi();
                        break;
                    case 6:
                        System.out.println("\nTerima kasih telah menggunakan accounTrace!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-6.");
                }
            } else {
                System.out.println("Input harus berupa angka!");
                scanner.next(); // Membersihkan input salah
            }

        } while (pilihan != 6);

        scanner.close();
    }

    // Fungsi Tambah Akun
    private static void tambahAkun(Scanner scanner) {
        System.out.println("\n--- TAMBAH AKUN BARU ---");
        System.out.print("Masukkan Kode Akun (contoh: 401 / 501): ");
        String kodeAkun = scanner.nextLine();

        System.out.print("Masukkan Nama Akun (contoh: Pendapatan Jasa): ");
        String namaAkun = scanner.nextLine();

        System.out.print("Masukkan Saldo Awal (contoh: 0): ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();

        String sql = "INSERT INTO accounts (account_code, account_name, balance) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kodeAkun);
            pstmt.setString(2, namaAkun);
            pstmt.setDouble(3, saldo);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sukses! Akun baru berhasil disimpan ke database.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan akun: " + e.getMessage());
        }
    }

    // Fungsi Lihat Daftar Akun
    private static void tampilkanAkun() {
        System.out.println("\n--- DAFTAR AKUN DALAM DATABASE ---");
        String sql = "SELECT * FROM accounts";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-10s | %-25s | %-15s\n", "Kode", "Nama Akun", "Saldo");
            System.out.println("---------------------------------------------------------");

            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                String kode = rs.getString("account_code");
                String nama = rs.getString("account_name");
                double saldo = rs.getDouble("balance");

                System.out.printf("%-10s | %-25s | Rp %,12.2f\n", kode, nama, saldo);
            }

            if (!adaData) {
                System.out.println("Belum ada akun yang tersimpan.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data akun: " + e.getMessage());
        }
    }

    // Fungsi Catat Jurnal Umum
    private static void catatJurnal(Scanner scanner) {
        System.out.println("\n--- CATAT TRANSAKSI JURNAL UMUM ---");
        System.out.print("Masukkan Tanggal (Format: YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();

        System.out.print("Masukkan Kode Akun yang Terkait: ");
        String kodeAkun = scanner.nextLine();

        System.out.print("Masukkan Keterangan / Uraian Transaksi: ");
        String keterangan = scanner.nextLine();

        System.out.print("Masukkan Nilai DEBIT (ketik 0 jika tidak ada): ");
        double debit = scanner.nextDouble();

        System.out.print("Masukkan Nilai KREDIT (ketik 0 jika tidak ada): ");
        double kredit = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan buffer

        String sql = "INSERT INTO general_journals (transaction_date, account_code, description, debit, credit) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tanggal);
            pstmt.setString(2, kodeAkun);
            pstmt.setString(3, keterangan);
            pstmt.setDouble(4, debit);
            pstmt.setDouble(5, kredit);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sukses! Transaksi jurnal umum berhasil dicatat.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencatat jurnal: " + e.getMessage());
        }
    }

    // Fungsi Lihat Daftar Jurnal Umum
    private static void tampilkanJurnal() {
        System.out.println("\n--- BUKU JURNAL UMUM ---");
        String sql = "SELECT * FROM general_journals";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-12s | %-10s | %-25s | %-12s | %-12s\n", "Tanggal", "Kode", "Keterangan", "Debit", "Kredit");
            System.out.println("-----------------------------------------------------------------------------------------");

            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                String tanggal = rs.getString("transaction_date");
                String kode = rs.getString("account_code");
                String ket = rs.getString("description");
                double debit = rs.getDouble("debit");
                double kredit = rs.getDouble("credit");

                System.out.printf("%-12s | %-10s | %-25s | Rp %,10.2f | Rp %,10.2f\n", 
                        tanggal, kode, ket, debit, kredit);
            }

            if (!adaData) {
                System.out.println("Belum ada transaksi jurnal yang tercatat.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jurnal: " + e.getMessage());
        }
    }

    // Fungsi Laporan Laba Rugi Otomatis Berdasarkan Kategori Kode Akun
    private static void tampilkanLaporanLabaRugi() {
        System.out.println("\n=================================================");
        System.out.println("            LAPORAN LABA RUGI (INCOME)           ");
        System.out.println("=================================================");

        double totalPendapatan = 0;
        double totalBeban = 0;
        String sql = "SELECT account_code, account_name, balance FROM accounts";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // 1. Bagian Pendapatan (Awalan Kode '4')
            System.out.println("PENDAPATAN (REVENUE):");
            boolean adaPendapatan = false;
            while (rs.next()) {
                String kode = rs.getString("account_code");
                String nama = rs.getString("account_name");
                double saldo = rs.getDouble("balance");

                if (kode.startsWith("4")) {
                    adaPendapatan = true;
                    System.out.printf("  [%s] %-25s : Rp %,12.2f\n", kode, nama, saldo);
                    totalPendapatan += saldo;
                }
            }
            if (!adaPendapatan) {
                System.out.println("  (Belum ada akun pendapatan dengan awalan kode '4')");
            }
            System.out.printf("Total Pendapatan                      : Rp %,12.2f\n", totalPendapatan);
            System.out.println("-------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Gagal memuat data pendapatan: " + e.getMessage());
        }

        // 2. Bagian Beban/Biaya (Awalan Kode '5' atau '6')
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("BEBAN / BIAYA (EXPENSES):");
            boolean adaBeban = false;
            while (rs.next()) {
                String kode = rs.getString("account_code");
                String nama = rs.getString("account_name");
                double saldo = rs.getDouble("balance");

                if (kode.startsWith("5") || kode.startsWith("6")) {
                    adaBeban = true;
                    System.out.printf("  [%s] %-25s : Rp %,12.2f\n", kode, nama, saldo);
                    totalBeban += saldo;
                }
            }
            if (!adaBeban) {
                System.out.println("  (Belum ada akun beban dengan awalan kode '5' atau '6')");
            }
            System.out.printf("Total Beban                           : Rp %,12.2f\n", totalBeban);
            System.out.println("-------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Gagal memuat data beban: " + e.getMessage());
        }

        // 3. Hitung Laba/Rugi Bersih
        double labaBersih = totalPendapatan - totalBeban;
        System.out.printf("LABA / RUGI BERSIH                    : Rp %,12.2f\n", labaBersih);
        
        if (labaBersih > 0) {
            System.out.println("Status Kinerja                        : [ UNTUNG (LABA) ]");
        } else if (labaBersih < 0) {
            System.out.println("Status Kinerja                        : [ RUGI (DEFISIT) ]");
        } else {
            System.out.println("Status Kinerja                        : [ IMPAS (BREAK EVEN) ]");
        }
        System.out.println("=================================================");
    }
}