package itmo.gorshkov.compmath.simpson;


import java.util.function.Function;

public class SuperSimpson {
    public static Answer solve(double a, double b, double e, Function<Double, Double> func) {
        boolean inverse = false;
        if (a > b) {
            double c = a;
            a = b;
            b = c;
            inverse = true;
        }
        double I1;
        double I2 = 0;
        int n = 2;
        double h = (b - a) / 2;
        do {
            I1 = I2;
            n = n * 2;
            h = h / 2;
            int i = 0;
            I2 = 0;
            do {
                i += 2;
                double x2 = a + i * h;
                double x1 = x2 - h;
                double x0 = x1 - h;
                double y0 = funcRight(x0, func);
                double y1 = funcVal(x1, func);
                double y2 = funcLeft(x2, func);
                double s = y0 + 4 * y1 + y2;
                I2 = I2 + s;
            } while (i < n);
            I2 = I2 * h / 3;
        } while (1d / 15 * Math.abs(I1 - I2) >= e);
        double accuracy = 1d / 15 * Math.abs(I1 - I2);
        if (inverse)
            I2 *= -1;
        return new Answer(I2, n, accuracy);
    }

    private static final double EPSILON = 1e-5;

    private static double funcLeft(double x, Function<Double, Double> func) {
        double res = func.apply(x);
        if (Double.isInfinite(res) || Double.isNaN(res))
            return func.apply(x - EPSILON);
        return res;
    }

    private static double funcVal(double x, Function<Double, Double> func) {
        double res = func.apply(x);
        if (Double.isInfinite(res) || Double.isNaN(res))
            return (func.apply(x - EPSILON) + func.apply(x + EPSILON)) / 2;
        return res;
    }

    private static double funcRight(double x, Function<Double, Double> func) {
        double res = func.apply(x);
        if (Double.isInfinite(res) || Double.isNaN(res))
            return func.apply(x + EPSILON);
        return res;
    }
}
