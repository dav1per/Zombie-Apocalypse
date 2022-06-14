/*
Grupa: abc123
Wiktoria Majewska
Igor Wlodarczyk
 */
import java.io.IOException;

/**
 * This class main is the main class in project.
 * There is main method.
 */
public class main {

    /**
     * This method named main is where the entire simulation is turned on.
     * @param args String array. Is compulsory in main
     *             method in java in order to receive
     *             the parameters for the input.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Simulation simulation1 = new Simulation(
                10150,
                30,
                100,
                15,
                50,
                0.20,
                25,
                0.1,
                5000);
        simulation1.Simulation("symulacja1");
    }
}
