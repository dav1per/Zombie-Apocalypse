import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Simulation simulation1 = new Simulation(
                10000,
                4,
                100,
                5,
                20,
                0.6,
                240,
                0.05,
                5000);
        simulation1.Simulation("symulacja1");
    }
}
