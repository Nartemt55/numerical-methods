import demo.SampleScenario;

/**
 * Главный класс приложения.
 * Демонстрирует работу численных методов через предустановленные сценарии.
 *
 * @author nartemt
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        SampleScenario.bisectionScenario();
        SampleScenario.newtonScenario();
        SampleScenario.newtonScenarioWithMultiplicity();
    }
}
