package mk.ukim.finki.av10;

interface IBeverage{
    double price();
}

class Espresso implements IBeverage {

    @Override
    public double price() {
        return 80;
    }

    @Override
    public String toString() {
        return "Espresso with ";
    }
}

class BrewedCoffee implements IBeverage {

    @Override
    public double price() {
        return 40;
    }

    @Override
    public String toString() {
        return "BrewedCoffee with ";
    }
}

abstract class BeverageDecorator implements IBeverage {
    IBeverage wrapped;

    public BeverageDecorator(IBeverage wrapped) {
        this.wrapped = wrapped;
    }
}

class RegularMilkDecorator extends BeverageDecorator{

    public RegularMilkDecorator(IBeverage wrapped) {
        super(wrapped);
    }

    @Override
    public double price() {
        return 20 + wrapped.price();
    }

    @Override
    public String toString() {
        return wrapped.toString() + "regular milk, ";
    }
}

class OatMilkDecorator extends BeverageDecorator{

    public OatMilkDecorator(IBeverage wrapped) {
        super(wrapped);
    }

    @Override
    public double price() {
        return 50 + wrapped.price();
    }

    @Override
    public String toString() {
        return wrapped.toString() + "oat milk, ";
    }
}

class ChocolateDecorator extends BeverageDecorator{

    public ChocolateDecorator(IBeverage wrapped) {
        super(wrapped);
    }

    @Override
    public double price() {
        return 10 + wrapped.price();
    }

    @Override
    public String toString() {
        return wrapped.toString() + "chocolate, ";
    }
}

public class DecoratorTest {
    public static void main(String[] args) {
        IBeverage beverage = new Espresso();
        beverage = new OatMilkDecorator(beverage);
        beverage = new ChocolateDecorator(beverage);

        System.out.println(beverage.toString());
        System.out.println(beverage.price());
    }
}
