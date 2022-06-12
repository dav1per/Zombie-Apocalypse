import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException {
        Simulation simulation1 = new Simulation(
                1,
                250,
                40,
                10,
                60,
                0.40,
                15,
                0.3,
                5000);
        simulation1.Simulation("symulacja1");
    }
}
