package model;

import exception.IncompleteContextException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;


/**
 * Представляет собой совокупность всех исходный данных задачи.
 * Может содержать интервал, начальное приближение и др.
 *
 * @author nartemt
 * @version 1.0
 */

public class SolverContext {
    private BigDecimal leftBorder;
    private BigDecimal rightBorder;
    private BigDecimal initialGuess;
    private int maxIterations = 100;
    private int multiplicity = 1;

    private SolverContext() {
    }

    /**
     * Позволяет создать контекст для решения задачи методом дихотомии.
     *
     * @param leftBorder  левая граница интервала.
     * @param rightBorder правая граница интервала.
     * @return контекст решения.
     * @see solvers.BisectionSolver
     */

    public static SolverContext forBisection(BigDecimal leftBorder, BigDecimal rightBorder) {
        SolverContext solverContext = new SolverContext();
        solverContext.leftBorder = leftBorder;
        solverContext.rightBorder = rightBorder;
        return solverContext;
    }

    /**
     * Позволяет создать контекст для решения задачи методом Ньютона.
     *
     * @param initialGuess начальное приближение.
     * @return контекст решения.
     * @see solvers.NewtonSolver
     */

    public static SolverContext forNewton(BigDecimal initialGuess) {
        SolverContext solverContext = new SolverContext();
        solverContext.initialGuess = initialGuess;
        return solverContext;
    }

    /**
     * Позволяет добавить в конфигурацию контекста максимальное число итераций
     * (к примеру используется при вычислении методом Ньютона {@link solvers.NewtonSolver}).
     *
     * @param maxIterations максимальное число итераций.
     * @return контекст решения.
     * @see solvers.NewtonSolver
     */

    public SolverContext withIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Позволяет добавить в конфигурацию контекста кратность искомого корня.
     * Это значительно ускоряет работу алгоритма
     * (к примеру используется при вычислении методом Ньютона {@link solvers.NewtonSolver}).
     *
     * @param multiplicity кратность искомого корня.
     * @return контекст решения.
     * @see solvers.NewtonSolver
     */

    public SolverContext withMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
        return this;
    }

    /**
     * Позволяет безопасно извлечь значение требуемого параметра из контекста.
     * Исключает возможность неинициализированных параметров нарушить работу алгоритма.
     *
     * @param <T> тип параметра.
     * @param value значение требуемого параметра.
     * @param parameterName название требуемого параметра.
     * @return ненулевое значение данного параметра.
     * @throws IncompleteContextException возникает, если переданный параметр не инициализирован
     * и может нарушить работу алгоритма.
     */
    @Contract
    private <T> @NotNull T require(T value, String parameterName) {
        if (value != null)
            return value;
        throw new IncompleteContextException(parameterName);
    }

    /**
     * Возвращает левую границу интервала из контекста.
     * Перед возвратом проверяет инициализацию параметра с помощью {@link #require}.
     *
     * @return значение левой границы типа {@link BigDecimal}.
     * @throws IncompleteContextException возникает, если левая граница не инициализирована.
     */

    public BigDecimal getLeftBorder() {
        return require(leftBorder, "leftBorder");
    }

    /**
     * Возвращает правую границу интервала из контекста.
     * Перед возвратом проверяет инициализацию параметра с помощью {@link #require}.
     *
     * @return значение правой границы типа {@link BigDecimal}.
     * @throws IncompleteContextException возникает, если правая граница не инициализирована.
     */

    public BigDecimal getRightBorder() {
        return require(rightBorder, "rightBorder");
    }

    /**
     * Возвращает начальное приближение из контекста.
     * Перед возвратом проверяет инициализацию параметра с помощью {@link #require}.
     *
     * @return значение начального приближения типа {@link BigDecimal}.
     * @throws IncompleteContextException возникает, если начальное приближение не инициализировано.
     */

    public BigDecimal getInitialGuess() {
        return require(initialGuess, "initialGuess");
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public int getMultiplicity() {
        return multiplicity;
    }
}
