package itmo.gorshkov.compmath.simpson;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import static itmo.gorshkov.compmath.simpson.Functions.*;

public class Main {

    private static final String instruction = "Значения введены неверно. a,b,e ∈ R, e > 0";
    private static final String functions = "Введите номер функции:\n" +
            "1) y=ln(x)\n" +
            "2) y = 1/x\n" +
            "3) x^2/x\n" +
            "4) y = sin(x)\n" +
            "5) y = e^(-x^2)\n";

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print(functions);
            int n = Integer.parseInt(scan.nextLine());
            System.out.print("Введите левый предел интегрирования:\t");
            String a = scan.nextLine();
            System.out.print("Введите правый предел интегрирования:\t");
            String b = scan.nextLine();
            System.out.print("Введите точность: ");
            String e = scan.nextLine().replace(",",".");
            double[] var = Arrays.stream(new String[]{a, b, e}).mapToDouble(Double::parseDouble).toArray();
            Function<Double, Double> function;
            switch (n) {
                case 1:
                    function = ln;
                    break;
                case 2:
                    function = hyperbole;
                    break;
                case 3:
                    function = polynomial;
                    break;
                case 4:
                    function = sin;
                    break;
                case 5:
                    function = gaussian;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (var[2] <= 0)
                throw new IllegalArgumentException();
            try {
                if (n == 2 && (var[0] <= 0 && var[1] >= 0 || var[0] >= 0 && var[1] <= 0) && var[0] != -var[1])
                    throw new ArithmeticException();
                Answer ans = SuperSimpson.solve(var[0], var[1], var[2], function);
                if(ans.partition <= 0)
                    throw new ArithmeticException();
                NumberFormat format = NumberFormat.getNumberInstance();
                format.setMaximumFractionDigits(100);
                System.out.println("Значение интеграла: \t" + format.format(ans.answer));
                System.out.println("Полученная погрешность:\t" + format.format(ans.accuracy));
                System.out.println("Количество разбиений:\t" + ans.partition);
            } catch (ArithmeticException ee) {
                System.out.println("Интеграл не берется");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(instruction);
        }
    }

}
