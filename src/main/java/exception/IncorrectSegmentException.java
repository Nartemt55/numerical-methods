package exception;

import java.math.BigDecimal;

/**
 * Исключение, которое возникает если границы переданного интервала
 * имеют одинаковый знак.
 * <p>
 * Сигнализирует о нарушении теоремы Больцано — Коши.
 * При не соблюдении этого условия прийти к решению невозможно.
 * <p>
 * Используется (например {@link solvers.BisectionSolver}).
 *
 * @author nartemt
 * @version 1.0
 * @see solvers.BisectionSolver
 */

public class IncorrectSegmentException extends NumericalMethodException {
    /**
     * Создает исключение, с сообщением о некорректном интервале.
     *
     * @param leftBorder левая граница интервала.
     * @param rightBorder правая граница интервала.
     */
    public IncorrectSegmentException(BigDecimal leftBorder, BigDecimal rightBorder) {
        super(String.format("A segment [%s; %s] was given in which the function doesn't become a zero",
                leftBorder.compareTo(rightBorder) <= 0 ? leftBorder : rightBorder,
                leftBorder.compareTo(rightBorder) <= 0 ? rightBorder : leftBorder
        ));
    }
}
