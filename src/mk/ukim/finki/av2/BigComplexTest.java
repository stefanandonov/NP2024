package mk.ukim.finki.av2;

import java.math.BigDecimal;

class BigComplex {
    BigDecimal real;
    BigDecimal imaginary;

    public BigComplex(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public BigComplex add (BigComplex bc){
        return new BigComplex(
                this.real.add(bc.real),
                this.imaginary.add(bc.imaginary)
        );
    }

    @Override
    public String toString() {

        return String.format(
                "%s%s%si",
                real.toString(),
                imaginary.toString().charAt(0) == '-' ? "" : "+",
                imaginary.toString()
        );
    }
}

public class BigComplexTest {
    public static void main(String[] args) {
        BigComplex bc = new BigComplex(
                new BigDecimal("123312132231312312312123142"),
                new BigDecimal("88989899889988998899889989889978987897")
        );

        BigComplex bc1 = new BigComplex(
                new BigDecimal("1"),
                new BigDecimal("2")
        );

        System.out.println(bc.add(bc1));
    }
}
