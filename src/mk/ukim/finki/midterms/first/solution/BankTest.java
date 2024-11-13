//package mk.ukim.finki.midterms.first.solution;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//class InvalidLoanApplicationException extends Exception {
//
//    public InvalidLoanApplicationException(int clientId, long loanAmount) {
//        super(String.format("The client with id %d can not take a loan of amount %d.", clientId, loanAmount));
//    }
//}
//
//enum Type {
//    HOUSING, SPENDING
//}
//
//abstract class LoanApplication {
//    private int clientId;
//    private int loanAmount;
//    private int yearsOfPayment;
//    private int clientBalance;
//    private double interestRate;
//
//    public LoanApplication(int clientId, int loanAmount, int yearsOfPayment, int clientBalance, double interestRate) {
//        this.clientId = clientId;
//        this.loanAmount = loanAmount;
//        this.yearsOfPayment = yearsOfPayment;
//        this.clientBalance = clientBalance;
//        this.interestRate = interestRate;
//    }
//
//    public long getLoanAmount() {
//        return loanAmount;
//    }
//
//    public long getClientBalance() {
//        return clientBalance;
//    }
//
//    public static LoanApplication createLoanApplication(int clientId, int loanAmount, int yearsOfPayment, int clientBalance,
//                                                        double interestRate, char type) throws InvalidLoanApplicationException {
//        if (type == 'S')
//            return new SpendingLoanApplication(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
//        else return new HousingLoanApplication(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
//    }
//
//    public double totalPayment() {
//        return loanAmount * interestRate / 100.0 * yearsOfPayment + loanAmount;
//    }
//
//    public abstract Type getLoanType();
//
//    public abstract boolean shouldBeApproved();
//
//    public abstract double providesCommission();
//}
//
//class HousingLoanApplication extends LoanApplication {
//
//    public HousingLoanApplication(int clientId, int loanAmount, int yearsOfPayment,
//                                  int clientBalance, double interestRate) throws InvalidLoanApplicationException {
//        super(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
//        if (!shouldBeApproved()) throw new InvalidLoanApplicationException(clientId, loanAmount);
//    }
//
//    @Override
//    public Type getLoanType() {
//        return Type.HOUSING;
//    }
//
//    @Override
//    public boolean shouldBeApproved() {
//        return getLoanAmount() <= 15 * getClientBalance();
//    }
//
//    @Override
//    public double providesCommission() {
//        return 0.06 * totalPayment();
//    }
//}
//
//class SpendingLoanApplication extends LoanApplication {
//
//    public SpendingLoanApplication(int clientId, int loanAmount, int yearsOfPayment, int clientBalance, double interestRate)
//            throws InvalidLoanApplicationException {
//        super(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
//        if (!shouldBeApproved()) throw new InvalidLoanApplicationException(clientId, loanAmount);
//    }
//
//    @Override
//    public Type getLoanType() {
//        return Type.SPENDING;
//    }
//
//    @Override
//    public boolean shouldBeApproved() {
//        return getLoanAmount() <= 5 * getClientBalance();
//    }
//
//    @Override
//    public double providesCommission() {
//        return 0.03 * totalPayment();
//    }
//}
//
//class BankClerk implements Comparable<BankClerk> {
//    private int bankClerkId;
//    private List<LoanApplication> approvedLoanApplications;
//
//    public BankClerk(int bankClerkId, List<LoanApplication> approvedLoanApplications) {
//        this.bankClerkId = bankClerkId;
//        this.approvedLoanApplications = approvedLoanApplications;
//    }
//
//    public static BankClerk createBankClerk(String line) {
//        String[] parts = line.split("\\s+");
//        int bankClerkId = Integer.parseInt(parts[0]);
//        List<LoanApplication> applications = new ArrayList<>();
//
//        for (int i = 1; i < parts.length; i += 6) {
//            int clientId = Integer.parseInt(parts[i]);
//            int loanAmount = Integer.parseInt(parts[i + 1]);
//            int yearsOfPayment = Integer.parseInt(parts[i + 2]);
//            int clientBalance = Integer.parseInt(parts[i + 3]);
//            double interestRate = Double.parseDouble(parts[i + 4]);
//            char loanType = parts[i + 5].charAt(0);
//
//            try {
//                applications.add(LoanApplication.createLoanApplication(clientId, loanAmount,
//                        yearsOfPayment, clientBalance, interestRate, loanType));
//            } catch (InvalidLoanApplicationException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return new BankClerk(bankClerkId, applications);
//    }
//
//    public int numApplications() {
//        return approvedLoanApplications.size();
//    }
//
//    public int getBankClerkId() {
//        return bankClerkId;
//    }
//
//    private long minApplicationLoanValue() {
//        return approvedLoanApplications.stream().mapToLong(LoanApplication::getLoanAmount).min().orElse(0);
//    }
//
//    private double maxApplicationTotalPayment() {
//        return approvedLoanApplications.stream().mapToDouble(LoanApplication::totalPayment).max().orElse(0);
//    }
//
//    private double totalCommission() {
//        return approvedLoanApplications.stream().mapToDouble(LoanApplication::providesCommission).sum();
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%d %d %d %.2f %.2f", bankClerkId, approvedLoanApplications.size(),
//                minApplicationLoanValue(), maxApplicationTotalPayment(), totalCommission());
//    }
//
//    @Override
//    public int compareTo(BankClerk o) {
//        return Comparator.comparing(BankClerk::numApplications)
//                .thenComparing(BankClerk::getBankClerkId)
//                .compare(this, o);
//    }
//}
//
//class Bank {
//    private List<BankClerk> bankClerks;
//
//    public Bank() {
//        this.bankClerks = new ArrayList<>();
//    }
//
//    public void readApplication(InputStream is) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//
//        bankClerks = bufferedReader.lines()
//                .map(BankClerk::createBankClerk)
//                .collect(Collectors.toList());
//    }
//
//    public void printApplicationsReport(OutputStream os) {
//        PrintWriter printWriter = new PrintWriter(os);
//        bankClerks.stream().sorted().forEach(printWriter::println);
//        printWriter.flush();
//    }
//}
//
//public class BankTest {
//
//    public static void main(String[] args) {
//        Bank bank = new Bank();
//        System.out.println("----- READING LOAN APPLICATIONS -----");
//        bank.readApplication(System.in);
//        System.out.println("----- PRINTING APPROVED APPLICATIONS REPORTS FOR BANK CLERKS -----");
//        bank.printApplicationsReport(System.out);
//    }
//}
