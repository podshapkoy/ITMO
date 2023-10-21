public class Main {
    public static void main(String[] args) {
        //Задание №1
        long[] a = new long[(int) 10];
        for (int i = 1; i < a.length; i++) {
            a[i] = i * 2L + 1;
            System.out.printf("%5d", a[i]);
        }
        System.out.println();
        //Задание №2
        double[] x = new double[18];
        for (int i = 0; i < x.length; i++) {
            x[i] = (Math.random() * ((2.0 + 12.0) + 1) - 12.0);
            System.out.printf("%,.2f  ", x[i]);
        }
        System.out.println();
        //Задание №3
        double[][] A = new double[9][18];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 18; j++) {
                if (a[i] == 19)
                    A[i][j] = Math.pow((Math.cbrt(Math.log(Math.abs(x[j])))), (Math.PI * (Math.pow(Math.asin((x[j] - 5) / 14), (((0.25 + Math.log(Math.abs(x[j]))) / 2) / 3)) - 1 / 4)));
                else if (a[i] == 5 || a[i] == 7 || a[i] == 9 || a[i] == 11) {
                    A[i][j] = Math.pow(StrictMath.E, Math.log(Math.pow(Math.cos(x[j]), 2)));
                } else {
                    A[i][j] = Math.tan(Math.atan(Math.pow(StrictMath.E, -Math.abs(x[j]))));
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 18; j++) {
                System.out.printf("%,.2f  ", A[i][j]);
            }
            System.out.println();
        }
    }
}