package mk.ukim.finki.av2;


class PasswordNotValidException extends Exception {
    int notValidPassword;

    public PasswordNotValidException(int notValidPassword) {
        super(String.format("Password %d is not valid!", notValidPassword));

    }
}

class CombinationLock {
    int password;
    boolean isOpen;

    public CombinationLock() {
        password = 111;
        isOpen = false;
    }

    public CombinationLock(int password) throws PasswordNotValidException {
        if (password<100 || password>999){
            throw new PasswordNotValidException(password);
        }

        this.password = password;
        isOpen = false;
    }

    public boolean enterCombination(int password) {
        if (this.password == password) {
            isOpen = !isOpen;
            return true;
        } else {
            return false;
        }
    }


    public boolean changePassword(int oldPassword, int newPassword) throws PasswordNotValidException {
        if (newPassword<100 || newPassword>999){
            throw new PasswordNotValidException(newPassword);
        }

        if (this.password == oldPassword && this.isOpen) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CombinationLock{" +
                "password=" + password +
                ", isOpen=" + isOpen +
                '}';
    }
}

public class CombinationLockTest {
    public static void main(String[] args) {
        try{
            CombinationLock combinationLock = new CombinationLock(111);
            System.out.println(combinationLock);
            System.out.println(combinationLock.enterCombination(111));
            System.out.println(combinationLock);
            System.out.println(combinationLock.changePassword(111, 25556));
            System.out.println(combinationLock);
        } catch (PasswordNotValidException e){
            System.out.println(e.getMessage());
        }
    }
}
