package mk.ukim.finki.midterms.first.starter;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class InvalidLoanApplicationException extends Exception {
    public InvalidLoanApplicationException(String clientId, int loanAmount) {
        super(String.format("The client with id %s can not take a loan of amount %d.", clientId, loanAmount));
    }
}

enum LoanType {
    SPENDING,
    HOUSING
}

abstract class LoanApplication {

    private String clientId;
    private int loanAmount;
    private int yearsOfPayment;
    private int clientBalance;
    private double interestRate;

    public LoanApplication(String clientId, int loanAmount, int yearsOfPayment, int clientBalance, double interestRate) {
        this.clientId = clientId;
        this.loanAmount = loanAmount;
        this.yearsOfPayment = yearsOfPayment;
        this.clientBalance = clientBalance;
        this.interestRate = interestRate;
    }

    public double getTotalPayment() {
        return loanAmount * interestRate / 100 * yearsOfPayment + loanAmount;
    }

    public abstract String getLoanType();

    public abstract boolean isLoanApproved();

    public abstract double commission();

    public String getClientId() {
        return clientId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public int getYearsOfPayment() {
        return yearsOfPayment;
    }

    public int getClientBalance() {
        return clientBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "clientId='" + clientId + '\'' +
                ", loanAmount=" + loanAmount +
                ", yearsOfPayment=" + yearsOfPayment +
                ", clientBalance=" + clientBalance +
                ", interestRate=" + interestRate +
                '}';
    }
}

class SpendingLoanApplication extends LoanApplication {

    public SpendingLoanApplication(String clientId, int loanAmount, int yearsOfPayment, int clientBalance, double interestRate) throws InvalidLoanApplicationException {
        super(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
        if (!isLoanApproved())
            throw new InvalidLoanApplicationException(clientId, loanAmount);
    }

    @Override
    public String getLoanType() {
        return LoanType.SPENDING.name();
    }

    @Override
    public boolean isLoanApproved() {
        return getLoanAmount() <= getClientBalance() * 5;
    }

    @Override
    public double commission() {
        return getTotalPayment() * 0.03;
    }
}

class HousingLoanApplication extends LoanApplication {

    public HousingLoanApplication(String clientId, int loanAmount, int yearsOfPayment, int clientBalance, double interestRate) throws InvalidLoanApplicationException {
        super(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
        if (!isLoanApproved())
            throw new InvalidLoanApplicationException(clientId, loanAmount);
    }

    @Override
    public String getLoanType() {
        return LoanType.HOUSING.name();
    }

    @Override
    public boolean isLoanApproved() {
        return getLoanAmount() <= getClientBalance() * 15;
    }

    @Override
    public double commission() {
        return getTotalPayment() * 0.06;
    }
}

class BankClerk {

    private String bankClerkId;
    private List<LoanApplication> loanApplications;

    public BankClerk(String bankClerkId, List<LoanApplication> loanApplications) {
        this.bankClerkId = bankClerkId;
        this.loanApplications = loanApplications;
    }

    public static BankClerk createBankClerk(String line) {
        String[] parts = line.split("\\s+");
        String bankClerkId = parts[0];
        List<LoanApplication> applications = new ArrayList<>();

        for (int i = 1; i < parts.length; i += 6) {
            String clientId = parts[i];
            int loanAmount = Integer.parseInt(parts[i + 1]);
            int yearsOfPayment = Integer.parseInt(parts[i + 2]);
            int clientBalance = Integer.parseInt(parts[i + 3]);
            double interestRate = Double.parseDouble(parts[i + 4]);
            char loanType = parts[i + 5].charAt(0);

            LoanApplication application;

            try {
                if (loanType == 'S') {
                    application = new SpendingLoanApplication(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
                } else {
                    application = new HousingLoanApplication(clientId, loanAmount, yearsOfPayment, clientBalance, interestRate);
                }
                applications.add(application);
            } catch (InvalidLoanApplicationException e) {
                System.out.println(e.getMessage());
            }
        }
        return new BankClerk(bankClerkId, applications);
    }

    public String getBankClerkId() {
        return bankClerkId;
    }

    public int countApprovedApplications() {
        return (int) loanApplications.stream().filter(LoanApplication::isLoanApproved).count();
    }

    public int minApplicationLoanValue() {
        return loanApplications.stream().mapToInt(LoanApplication::getLoanAmount).min().orElse(0);
    }

    public double maxApplicationTotalPayment() {
        return loanApplications.stream().mapToDouble(LoanApplication::getTotalPayment).max().orElse(0);
    }

    public double totalCommission() {
        return loanApplications.stream().mapToDouble(LoanApplication::commission).sum();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d %.2f %.2f", bankClerkId, countApprovedApplications(), minApplicationLoanValue(), maxApplicationTotalPayment(), totalCommission());
    }
}

class Bank {

    List<BankClerk> bankClerks;

    public Bank() {
        bankClerks = new ArrayList<>();
    }


    public void readApplication(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        bankClerks = bufferedReader.lines().map(BankClerk::createBankClerk).collect(Collectors.toList());
    }

    public void printApplicationsReport(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);

        bankClerks.stream()
                .sorted(Comparator.comparing(BankClerk::countApprovedApplications)
                        .thenComparing(BankClerk::getBankClerkId))
                .forEach(i -> printWriter.println(i.toString()));

        printWriter.flush();
    }
}


public class BankTest {

    public static void main(String[] args) {
        Bank bank = new Bank();
        System.out.println("----- READING LOAN APPLICATIONS -----");
        bank.readApplication(System.in);
        System.out.println("----- PRINTING APPROVED APPLICATIONS REPORTS FOR BANK CLERKS -----");
        bank.printApplicationsReport(System.out);
    }
}
