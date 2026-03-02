package util;

import core.MathFunction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Представляет собой репозиторий функций, используемых
 * для демонстрации применения алгоритмов численных методов.
 *
 * @author nartemt
 * @version 1.0
 */

public class FunctionRepository {

    /**
     * Позволяет извлечь представления математических функций в виде списка.
     *
     * @return список математических функций,
     * представленных типом {@link core.MathFunction}.
     * @see core.MathFunction
     */

    public static List<MathFunction> getFunctionRepository() {
        return List.of(
                new MathFunction(
                        x -> x.pow(3).subtract(new BigDecimal("7").multiply(x.pow(2)))
                                .subtract(new BigDecimal("17").multiply(x)).subtract(new BigDecimal("9")),
                        x -> x.pow(2).multiply(new BigDecimal("3"))
                                .subtract(new BigDecimal("14").multiply(x)).subtract(new BigDecimal("17")),
                        "f(x) = x^3 - 7x^2 - 17x - 9"
                ),
                new MathFunction(
                        x -> x.pow(2)
                                .subtract(new BigDecimal("6").multiply(x))
                                .add(new BigDecimal("9")),

                        x -> x.multiply(new BigDecimal("2"))
                                .subtract(new BigDecimal("6")),

                        "f(x) = (x - 3)^2"
                ),
                new MathFunction(
                        x -> x.pow(2).subtract(new BigDecimal("4")),
                        x -> x.multiply(new BigDecimal("2")),
                        "f(x) = x^2 - 4"
                ));
    }
}
