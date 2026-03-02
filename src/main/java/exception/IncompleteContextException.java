package exception;

/**
 * Исключение, которое возникает если переданных данных недостаточно для применения метода.
 * <p>
 * Используется (например {@code SolverContext.require()}).
 *
 * @author nartemt
 * @version 1.0
 */

public class IncompleteContextException extends NumericalMethodException {

    /**
     * Создаёт исключение с информацией о параметре,
     * который необходим для применения численного метода,
     * но при этом не переданном.
     *
     * @param parameterName строковое название требуемого параметра.
     */

    public IncompleteContextException(String parameterName) {
        super("Expected a " + parameterName + " but wasn't passed");
    }
}
