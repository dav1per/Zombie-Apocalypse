/*
Grupa: abc123
Wiktoria Majewska
Igor Wlodarczyk
 */
import java.io.IOException;


public class main {
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
