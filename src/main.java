import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        /*Simulation simulation1 = new Simulation(
                4000,
                100,
                25,
                5,
                30,
                1,
                1500,
                0.05,
                5000
        );
        simulation1.Simulation();*/
        FileWriter csvWriter = new FileWriter("data.csv");

        int humanPopulation;
        int zombiePopulation;
        int mapSize;
        int humanCombatStat=25;
        int zombieCombatStat=30;
        double percentageOfHouses;
        int houseLootAmountRange;
        double houseWeaponLootPercentage=0.8;
        int numberOfEpochs;
        int choice;

        csvWriter.append("Number of epochs");
        csvWriter.append(",");
        csvWriter.append("number of humans population before");
        csvWriter.append(",");
        csvWriter.append("number of humans population now");
        csvWriter.append(",");
        csvWriter.append("number of humans killed by zombie");
        csvWriter.append(",");
        csvWriter.append("number of zombie population before");
        csvWriter.append(",");
        csvWriter.append("number of zombie population");
        csvWriter.append("\n");

        do{
            System.out.println("Number of humans population: ");
            Scanner scanner0 = new Scanner(System.in);
            humanPopulation = scanner0.nextInt();
            System.out.println("Number of zombie population: ");
            Scanner scanner1 = new Scanner(System.in);
            zombiePopulation = scanner1.nextInt();
            System.out.println("Map size: ");
            Scanner scanner2 = new Scanner(System.in);
            mapSize = scanner2.nextInt();
            System.out.println("Percentage of houses: ");
            Scanner scanner3 = new Scanner(System.in);
            percentageOfHouses = scanner3.nextDouble();
            System.out.println("House loot amount range: ");
            Scanner scanner4 = new Scanner(System.in);
            houseLootAmountRange = scanner4.nextInt();
            System.out.println("Number of epochs: ");
            Scanner scanner5 = new Scanner(System.in);
            numberOfEpochs = scanner5.nextInt();


            Simulation simulation = new Simulation(humanPopulation, zombiePopulation, mapSize, humanCombatStat, zombieCombatStat, percentageOfHouses, houseLootAmountRange, houseWeaponLootPercentage, numberOfEpochs);
            simulation.Simulation(csvWriter);
            System.out.println("0-break, 1-new simulation");
            Scanner scannerchoice = new Scanner(System.in);
            choice = scannerchoice.nextInt();
        }while(choice!=0);

        csvWriter.flush();
        csvWriter.close();
        System.out.println("the data has been stored in the \"data.csv\" file");

    }
}
