package core;

import model.SolutionResult;
import model.SolverContext;

import java.math.MathContext;

/**
 * Интерфейс для описания инструментария классов, выполняющих вычисление корней.
 *
 * @author nartemt
 * @version 1.0
 */

public interface RootSolver {

    int MAX_ITERATIONS = 300;

    /**
     * Метод для нахождения корней.
     *
     * @param function    исходная функция.
     * @param context     контекст, включающий набор различных входных данных задачи (отрезок, точка, интервал).
     *                    (подробнее см. {@link SolverContext}).
     * @param mathContext контекст, включающий конфигурацию вычисления (точность и округление).
     * @return {@link SolutionResult} результат вычисления, содержащий ответ на задачу.
     * @throws exception.NumericalMethodException возникает при ошибке вычисления метода.
     * @see SolverContext
     * @see SolutionResult
     * @see solvers.BisectionSolver
     * @see solvers.NewtonSolver
     */

    SolutionResult solve(MathFunction function, SolverContext context, MathContext mathContext);

    /** @return строка с названием метода, используемая в отчете. */
    String getMethodName();
}
