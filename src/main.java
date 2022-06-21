/**
 * @author Wiktoria Majewska, Igor Wlodarczyk
 * Grupa: abc123
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * This class main is the main class in project.
 * There is main method.
 */
public class main {

    static int humanPopulation = 10000;
    static int zombiePopulation = 35;
    static int mapSize = 100;
    static int humanCombatStat = 30;
    static int zombieCombatStat = 50;
    static double percentageOfHouses = 0.20;
    static int houseLootAmountRange = 25;
    static double houseWeaponLootPercentage = 0.1;
    static int numberOfEpochs = 5000;

    /**
     * This method named menu is responsible for displaying the menu.
     */
    public static void menu(){
        System.out.println(" Menu");
        System.out.println("1. Start simulation");
        System.out.println("2. Show starting parameters");
        System.out.println("3. Change starting parameters");
        System.out.println("4. Exit");
    }

    /**
     * This method named showParameters is responsible for displaying current parameters.
     */
    public static void showParameters(){
        System.out.println("humanPopulation = " + humanPopulation);
        System.out.println("zombiePopulation = " + zombiePopulation);
        System.out.println("mapSize = " + mapSize);
        System.out.println("humanCombatStat = " + humanCombatStat);
        System.out.println("zombieCombatStat = " + zombieCombatStat);
        System.out.println("percentageOfHouses = " + percentageOfHouses);
        System.out.println("houseLootAmountRange = " + houseLootAmountRange);
        System.out.println("houseWeaponLootPercentage = " + houseWeaponLootPercentage);
        System.out.println("numberOfEpochs = " + numberOfEpochs);
    }

    /**
     * This methode named changeParameters is responsible for changing parameters
     * from current to those given by the user.
     */
    public static void changeParameters(){
        Scanner scan = new Scanner(System.in);
        System.out.println("current humanPopulation = " + humanPopulation);
        System.out.print("new humanPopulation = ");
        humanPopulation = scan.nextInt();

        System.out.println("current zombiePopulation = " + zombiePopulation);
        System.out.print("new zombiePopulation = ");
        zombiePopulation = scan.nextInt();

        System.out.println("current mapSize = " + mapSize);
        System.out.print("new mapSize = ");
        mapSize = scan.nextInt();

        System.out.println("current humanCombatStat = " + humanCombatStat);
        System.out.print("new humanCombatStat = ");
        humanCombatStat = scan.nextInt();

        System.out.println("current zombieCombatStat = " + zombieCombatStat);
        System.out.print("new zombieCombatStat = ");
        zombieCombatStat = scan.nextInt();

        System.out.println("current percentageOfHouses = " + percentageOfHouses);
        System.out.print("new percentageOfHouses = ");
        percentageOfHouses = scan.nextDouble();

        System.out.println("current houseLootAmountRange = " + houseLootAmountRange);
        System.out.print("new houseLootAmountRange = ");
        houseLootAmountRange = scan.nextInt();

        System.out.println("current houseWeaponLootPercentage = " + houseWeaponLootPercentage);
        System.out.print("new houseWeaponLootPercentage = ");
        houseWeaponLootPercentage = scan.nextDouble();

        System.out.println("current numberOfEpochs = " + numberOfEpochs);
        System.out.print("new numberOfEpochs = ");
        numberOfEpochs = scan.nextInt();
    }

    /**
     * This method named main is where the entire simulation is turned on.
     * @param args String array. Is compulsory in main
     *             method in java in order to receive
     *             the parameters for the input.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        while(start){
            menu();
            int n = scan.nextInt();
            switch(n){
                case 1:
                    scan.nextLine();
                    System.out.println("Enter the name of the simulation file");
                    String filename = scan.nextLine();
                    Simulation simulation1 = new Simulation(
                            humanPopulation,
                            zombiePopulation,
                            mapSize,
                            humanCombatStat,
                            zombieCombatStat,
                            percentageOfHouses,
                            houseLootAmountRange,
                            houseWeaponLootPercentage,
                            numberOfEpochs);
                    simulation1.Simulation(filename);
                    start = false;
                    break;
                case 2:
                    scan.nextLine();
                    showParameters();
                    break;
                case 3:
                    scan.nextLine();
                    changeParameters();
                    break;
                case 4:
                    start = false;
            }
        }

    }


}
