package util;

import model.SolutionResult;
import java.math.RoundingMode;

/**
 * Представляет собой объект, используемый для отображения
 * результата вычисления метода.
 *
 * @author nartemt
 * @version 1.0
 */

public class ResultPrinter {

    /**
     * Выводит в консоль в виде таблицы данные,
     * полученные в результате вычисления.
     *
     * @param result результат вычисления {@link model.SolutionResult}
     * @see model.SolutionResult
     */

    public static void print(SolutionResult result) {
        System.out.println("\n" + "=".repeat(45));
        System.out.printf("Method report: [%s]%n", result.methodName().toUpperCase());
        System.out.println("-".repeat(45));

        var rootFormatted = result.root()
                .stripTrailingZeros()
                .toPlainString();

        System.out.printf("Root:   %s%n", rootFormatted);
        System.out.printf("Iterations:    %d%n", result.iterations());
        System.out.printf("Precision:  %s%n", result.precision().toPlainString());
        System.out.println("=".repeat(45));
    }
}

