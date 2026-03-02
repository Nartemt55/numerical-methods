package solvers;

import core.MathFunction;
import core.RootSolver;
import exception.IncorrectSegmentException;
import model.SolutionResult;
import model.SolverContext;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Класс реализует метод дихотомии.
 * <p>
 * Данный метод является наиболее <b>надежным</b> так как гарантирует
 * сходимость к корню при соблюдении условий теоремы Больцано-Коши.
 * <p>
 * В отличие от метода Ньютона, не требует вычисления производной и устойчив к
 * "плохому" поведению функции вне окрестности корня, однако обладает более
 * низкой (линейной) скоростью сходимости.
 *
 * @author nartemt
 * @version 1.0
 * @see core.RootSolver
 */

public class BisectionSolver implements RootSolver {

    private static final String METHOD_NAME = "Bisection method";


    /**
     * {@inheritDoc}
     */
    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    /**
     * {@inheritDoc}
     * Данная реализация использует алгоритм <b>деления отрезка пополам</b>.
     * Метод последовательно сужает интервал [a;b], в котором функция
     * меняет знак до тех пор, пока его длина не станет меньше заданной точности.
     *
     * @throws IncorrectSegmentException возникает если границы переданного интервала
     * имеют одинаковый знак.
     */
    @Override
    public SolutionResult solve(MathFunction function, SolverContext solverContext, MathContext mathContext) {
        BigDecimal epsilon = BigDecimal.ONE.movePointLeft(mathContext.getPrecision());
        BigDecimal a = solverContext.getLeftBorder();
        BigDecimal b = solverContext.getRightBorder();
        BigDecimal fa = function.f().apply(a);
        BigDecimal fb = function.f().apply(b);

        if (fa.signum() == 0)
            return new SolutionResult(a, BigDecimal.ZERO, 0, getMethodName());

        if (fb.signum() == 0)
            return new SolutionResult(b, BigDecimal.ZERO, 0, getMethodName());

        if (fa.signum() == fb.signum())
            throw new IncorrectSegmentException(a, b);

        int iterations = 0;
        BigDecimal x = a;

        while (b.subtract(a).abs().compareTo(epsilon) > 0) {
            iterations++;

            x = a.add(b).divide(BigDecimal.valueOf(2), mathContext);
            BigDecimal fx = function.f().apply(x);

            if (fx.signum() == 0) break;

            if (fa.signum() != fx.signum()) {
                b = x;
            } else {
                a = x;
                fa = fx;
            }
        }

        BigDecimal precision = function.f().apply(x).abs();

        return new SolutionResult(x, precision, iterations, getMethodName());
    }
}
