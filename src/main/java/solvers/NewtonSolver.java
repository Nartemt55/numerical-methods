package solvers;

import core.MathFunction;
import core.RootSolver;
import exception.ConvergenceException;
import model.SolutionResult;
import model.SolverContext;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Класс реализует метод Ньютона (касательных).
 * <p>
 * Данный метод является наиболее <b>быстрым</b> так как обладает
 * квадратичной скоростью сходимости в окрестности.
 * <p>
 * Особенности:
 * <ul>
 *     <li>Требует наличие производной функции (для построения касательной).</li>
 *     <li>Критические зависит от выбора начального приближения (при неудачном выборе может расходиться).</li>
 *     <li>Функция должна быть непрерывно дифференцируема.</li>
 * </ul>
 *
 * @author nartemt
 * @version 1.0
 * @see core.RootSolver
 */

public class NewtonSolver implements RootSolver {

    private static final String METHOD_NAME = "Newton method";

    /** {@inheritDoc} */
    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    /** {@inheritDoc}
     * Итерации продолжаются до тех пор, пока разница между соседними
     * приближениями не станет меньше заданной точности.
     *
     * @throws ConvergenceException возникает если метод не сходится к решению
     * вследствие неудачного начального приближения
     * или особенностей поведения функции в данной области.
     */
    @Override
    public SolutionResult solve(MathFunction function, SolverContext solverContext, MathContext mathContext) {
        BigDecimal epsilon = BigDecimal.ONE.movePointLeft(mathContext.getPrecision());
        BigDecimal initialGuess = solverContext.getInitialGuess();
        BigDecimal multiplicity = BigDecimal.valueOf(solverContext.getMultiplicity());
        BigDecimal x = initialGuess;
        int maxIterations = solverContext.getMaxIterations();
        int actualIterations = 0;
        boolean isConverged = false;

        while (actualIterations < maxIterations){
            actualIterations++;
            BigDecimal dfx = function.df().apply(x);

            if (dfx.signum() == 0) {
                isConverged = true;
                break;
            }

            BigDecimal fx = function.f().apply(x);
            BigDecimal correction = fx.divide(dfx, mathContext).multiply(multiplicity, mathContext);
            BigDecimal nextX = x.subtract(correction, mathContext);

            if (nextX.subtract(x).abs().compareTo(epsilon) < 0) {
                x = nextX;
                isConverged = true;
                break;
            }
            x = nextX;
        }

        if (!isConverged)
            throw new ConvergenceException("Not converged");

        BigDecimal precision = function.f().apply(x).abs();
        return new SolutionResult(x, precision, actualIterations, METHOD_NAME);
    }
}
