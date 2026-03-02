package exception;

/**
 * Базовое исключение, указывающее на ошибку, допущенную в результате
 * работы алгоритма численного метода.
 * <p>
 * Служит родительским классом для всех специфических исключений,
 * в процессе выполнения математических алгоритмов.
 *
 * @author nartemt
 * @version 1.0
 */

public class NumericalMethodException extends RuntimeException {

    /**
     * Создает новое исключение с подробным описанием причины допущенной ошибки.
     *
     * @param message текст сообщения об ошибке.
     */

    public NumericalMethodException(String message) {
        super(message);
    }
}
