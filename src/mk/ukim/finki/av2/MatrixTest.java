package mk.ukim.finki.av2;


import java.util.Arrays;

class Matrix {
    public static double sum(double[][] a) {
        double sum = 0.0;
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a[i].length;j++){
                sum+=a[i][j];
            }
        }

        return sum;
    }

    public static double sumStream(double[][] a) {
//        return Arrays.stream(a)
//                .mapToDouble(row -> Arrays.stream(row).sum())
//                .sum();

        return Arrays.stream(a)
                .flatMapToDouble(row -> Arrays.stream(row))
                .sum();
    }
    public static double average(double[][] a) {
        if (a.length==0){
            return 0;
        }
        return sum(a)/(a.length*a[0].length);
    }
}

public class MatrixTest {
    public static void main(String[] args) {
        double [][] m = {
                {1,2,3},
                {4,5,6}
        };

        System.out.println(Matrix.sum(m));
        System.out.println(Matrix.average(m));
    }
}
