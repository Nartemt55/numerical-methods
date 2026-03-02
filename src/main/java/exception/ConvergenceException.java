package exception;

/**
 * Исключение указывает на не сходимость метода.
 * <p>
 * Возникает в случае если метод не вычислил решение с заданной точностью
 * за указанное число итераций.
 * <p>
 * Используется (например {@link solvers.NewtonSolver}).
 *
 * @author nartemt
 * @version 1.0
 * @see solvers.NewtonSolver
 */

public class ConvergenceException extends NumericalMethodException {
    /** {@inheritDoc} */
    public ConvergenceException(String message) {
        super(message);
    }
}
