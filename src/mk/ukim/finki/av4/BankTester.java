package mk.ukim.finki.av4;

class NotSufficientFundsException extends Exception {
    public NotSufficientFundsException(String message) {
        super(message);
    }
}

interface InterestBearingAccount {
    void addInterest();
}

class Account {

    static int COUNTER = 1;
    String holder;

    String number;

    double balance;

    public Account(String holder, double balance) {
        this.holder = holder;
        this.balance = balance;
        this.number = String.format("%d", 2100000+COUNTER);
        ++COUNTER;
    }

    void deposit (double amount){
        balance+=amount;
    }

    void withdraw (double amount) throws NotSufficientFundsException {
        if (amount > balance){
            throw new NotSufficientFundsException(String.format("Not sufficient funds for account number: %s", this.number));
        }
        balance-=amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holder='" + holder + '\'' +
                ", number='" + number + '\'' +
                ", balance=" + String.format("%.2f$",balance) +
                '}';
    }
}

class NonInterestCheckingAccount extends Account {

    public NonInterestCheckingAccount(String holder, double balance) {
        super(holder, balance);
    }

    @Override
    public String toString() {
        return "NonInterestChecking" + super.toString();
    }
}

class InterestCheckingAccount extends Account implements InterestBearingAccount{

    static double INTEREST_RATE = 0.03; //3%
    public InterestCheckingAccount(String holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void addInterest() {
        this.balance *= (1.0 + INTEREST_RATE);
    }

    @Override
    public String toString() {
        return "InterestChecking" + super.toString();
    }
}

class PlatinumCheckingAccount extends InterestCheckingAccount {

    public PlatinumCheckingAccount(String holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void addInterest() {
        balance *= (1.0 + 2*INTEREST_RATE);
    }

    @Override
    public String toString() {
        return "Platinum" + super.toString();
    }
}

class Bank {
    Account [] accounts = new Account[100];
    int n = 0;

    String name;

    public Bank(String name) {
        this.name = name;
    }

    public void addAccount (Account a) {
        accounts[n++]=a;
    }

    public void addInterest() {
        for (int i=0;i<n;i++) {
            if (accounts[i] instanceof InterestBearingAccount){
                InterestBearingAccount iba = (InterestBearingAccount) accounts[i];
                iba.addInterest();
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        for (int i=0;i<n;i++) {
            sb.append(accounts[i].toString()).append("\n");
        }

        return sb.toString();
    }
}

public class BankTester {
    public static void main(String[] args) {
//        Account ac = new Account("Stefan",0);
        Account ac1 = new InterestCheckingAccount("Stefan",10000);
        Account ac2 = new NonInterestCheckingAccount("Petko",1000000);
        Account ac3 = new PlatinumCheckingAccount("Riste",800000000);
//        Account ac2 = new NonInterestCheckingAccount("Petko",10000000);

//        System.out.println(ac1);
//        System.out.println(ac2);
//        System.out.println(ac3);

        Bank bank = new Bank("Bankata na Stefan");
        bank.addAccount(ac1);
        bank.addAccount(ac2);
        bank.addAccount(ac3);

        System.out.println(bank);

        System.out.println("Adding interest where possible");

        bank.addInterest();

        System.out.println(bank);



    }
}
