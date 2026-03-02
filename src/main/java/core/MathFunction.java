package core;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

/**
 * Представление данных о математической функции.
 *
 * @author nartemt
 * @version 1.0
 * @param f исходная математическая функция.
 * @param df производная этой функции.
 * @param description строковое представление.
 */

public record MathFunction(
        UnaryOperator<BigDecimal> f,
        UnaryOperator<BigDecimal> df,
        String description
) { }

