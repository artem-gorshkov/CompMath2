package itmo.gorshkov.compmath.simpson;

import java.util.function.Function;

public class Functions {
    public static final Function<Double, Double> ln = (x) -> {
        if (x < 0)
            throw new ArithmeticException();
        return Math.log(x);
    };
    public static final Function<Double, Double> hyperbole = (x) -> 1 / x;
    public static final Function<Double, Double> polynomial = (x) -> Math.pow(x, 2) / x;
    public static final Function<Double, Double> sin = Math::sin;
    public static final Function<Double, Double> gaussian = (x) -> Math.pow(Math.E, -Math.pow(x, 2));
}
