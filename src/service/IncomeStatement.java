package service;

public class IncomeStatement implements Reportable {
    private double totalRevenue;
    private double totalExpense;

    public IncomeStatement(double totalRevenue, double totalExpense) {
        this.totalRevenue = totalRevenue;
        this.totalExpense = totalExpense;
    }

    @Override
    public void generateReport() {
        double netIncome = totalRevenue - totalExpense;
        System.out.println("\n=== LAPORAN LABA RUGI (accounTrace) ===");
        System.out.println("Total Pendapatan : Rp " + totalRevenue);
        System.out.println("Total Beban      : Rp " + totalExpense);
        System.out.println("Laba Bersih      : Rp " + netIncome);
        System.out.println("=======================================");
    }
}