package model;

import java.math.BigDecimal;

/**
 * Представляет собой объект в котором находятся все данные,
 * полученные в ходе вычисления.
 *
 * @param root найденный корень.
 * @param precision точность решения.
 * @param iterations количество итераций, произведенных в ходе вычисления.
 * @param methodName название примененного метода.
 *
 * @author nartemt
 * @version 1.0
 */

public record SolutionResult(
        BigDecimal root,
        BigDecimal precision,
        int iterations,
        String methodName

) { }

