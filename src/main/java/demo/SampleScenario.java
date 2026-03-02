package demo;

import exception.NumericalMethodException;
import model.SolverContext;
import solvers.BisectionSolver;
import solvers.NewtonSolver;
import util.FunctionRepository;
import util.ResultPrinter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Демонстрационные сценарии применения алгоритмов численных методов.
 * <p>
 * Каждый метод в этом классе представляет собой законченный пример решения
 * конкретной математической задачи с предустановленными параметрами.
 *
 * @author nartemt
 * @version 1.0
 */

public class SampleScenario {

    /**
     * Демонстрирует решение уравнения методом бисекции.
     * <p>
     * Сценарий включает получение функции из репозитория, настройку интервала
     * [2; 9.2], заданная точность составляет 16 знаков.
     *
     * @see BisectionSolver
     */

    public static void bisectionScenario() {
        try {
            var functionRepository = FunctionRepository.getFunctionRepository();
            var exampleFunc = functionRepository.getFirst();
            var mathContext = new MathContext(16, RoundingMode.HALF_UP);

            var solver = new BisectionSolver();

            BigDecimal a = BigDecimal.valueOf(2);
            BigDecimal b = BigDecimal.valueOf(9.2);

            var solverContext = SolverContext.forBisection(a, b);

            var result = solver.solve(exampleFunc, solverContext, mathContext);

            ResultPrinter.print(result);
        } catch (NumericalMethodException e) {
            System.err.println("Bisection method error: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует решение уравнения методом Ньютона.
     * <p>
     * Сценарий выполняет поиск корня функции, начиная с приближения {@code x0 = 4},
     * точность составляет 20 значащих цифр.
     * <p>
     * Стоит отметить что метод {@link SolverContext#withMultiplicity} является опциональным,
     * а значит сценарий использования вычисления касательных Ньютона может быть как
     * с использованием кратности корня, так и без неё.
     * Этот и нижеследующий сценарии продемонстрируют, что алгоритм значительно быстрее
     * находит решение в случае если задана кратность искомого корня.
     * <p>
     * Для примера данный сценарий предполагает вызов опционального метода
     * {@link SolverContext#withIterations}, который запросто можно не вызывать явно,
     * тогда программа воспользуется значением максимально допустимого числа
     * операций, заданным по умолчанию.
     *
     * @see NewtonSolver
     * @see SolverContext#withIterations
     * @see SolverContext#withMultiplicity
     */

    public static void newtonScenario() {
        try {
            var functionRepository = FunctionRepository.getFunctionRepository();
            var exampleFunc = functionRepository.get(1);
            var mathContext = new MathContext(20, RoundingMode.HALF_UP);

            var solver = new NewtonSolver();

            BigDecimal x0 = BigDecimal.valueOf(4);

            var solverContext = SolverContext.forNewton(x0).withIterations(100);

            var result = solver.solve(exampleFunc, solverContext, mathContext);

            ResultPrinter.print(result);


        } catch (NumericalMethodException e) {
            System.err.println("Newton method error: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует решение уравнения методом Ньютона
     * с указанной кратностью искомого корня.
     * <p>
     * При передаче кратности в метод {@link SolverContext#withMultiplicity}
     * алгоритм находит решение за значительно меньшее число итераций, что
     * положительно сказывается на производительности.
     * <p>
     * Для примера данный сценарий вычисления исключает
     * использование {@link SolverContext#withIterations}, демонстрируя
     * что он опционален.
     * В данном случае программа использует значение максимального
     * допустимого числа итераций, заданного по умолчанию.
     */

    public static void newtonScenarioWithMultiplicity() {
        try {
            var functionRepository = FunctionRepository.getFunctionRepository();
            var exampleFunc = functionRepository.get(1);
            var mathContext = new MathContext(20, RoundingMode.HALF_UP);

            var solver = new NewtonSolver();

            BigDecimal x0 = BigDecimal.valueOf(4);

            var solverContext = SolverContext.forNewton(x0).withMultiplicity(2);

            var result = solver.solve(exampleFunc, solverContext, mathContext);

            ResultPrinter.print(result);

        } catch (NumericalMethodException e) {
            System.err.println("Newton method error: " + e.getMessage());
        }
    }
}
