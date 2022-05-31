import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    ArrayList<Human> humans = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    int mapSize;
    int numberOfEpochs;
    int epoch = 0;
    int numberOfZombieInitially;
    int numberOfHumansInitially;
    Random rand = new Random();
    Fight fight1 = new Fight();
    Area Map [][];
    Simulation(
            int humanPopulation,
            int zombiePopulation,
            int mapSize,
            int humanCombatStat,
            int zombieCombatStat,
            double percentageOfHouses,
            int houseLootAmountRange,
            double houseWeaponLootPercentage,
            int numberOfEpochs
    )throws IOException{
        Human.killedByZombie =0;
        Zombie.zombiePopulation =0;
        this.numberOfHumansInitially = humanPopulation;
        this.numberOfZombieInitially = zombiePopulation;
        this.numberOfEpochs = numberOfEpochs;
        this.mapSize = mapSize;
        for(int i = 0; i < humanPopulation; i++){
            humans.add(new Human(humanCombatStat, rand.nextInt(mapSize), rand.nextInt(mapSize)));
        }
        for(int i = 0; i < zombiePopulation; i++){
            zombies.add(new Zombie(zombieCombatStat, rand.nextInt(mapSize), rand.nextInt(mapSize)));
        }

        //Creating a map
        Map map1 = new Map(mapSize, percentageOfHouses, houseLootAmountRange, houseWeaponLootPercentage);
        Map = map1.Generator();
    }

    public void Epoch(){
        //Epoch counter
        epoch += 1;
        for(Human human : humans){
            human.humanEverydayRoutine(Map, mapSize);
        }

        //checking if every human is alive
        ArrayList<Human> humansToRemove = new ArrayList<>();
        for(Human human : humans){
            if(human.alive != true){
                humansToRemove.add(human);
            }
        }
        for(Human human1 : humansToRemove){
            humans.remove(human1);
        }
        humansToRemove.clear();

        for(Zombie zombie : zombies){
            zombie.selfMove(Map, mapSize, humans);
        }

        fight1.fight(humans, zombies, mapSize);

    }

    public void Simulation(FileWriter csvWriter)throws IOException{
        /*
        System.out.println("Statystyki poczatkowe");
        System.out.println("Liczba epok: "+epoch);
        System.out.println("Liczba ludzi: "+Human.humanPopulation);
        System.out.println("Liczba zombie: "+Zombie.zombiePopulation);
        System.out.println("Ludzie zabici przez zombie: "+Human.killedByZombie);
        System.out.println("Ludzie zabici przez glod: "+Human.starvedToDeath);
        System.out.println("Zombie zabite przez ludzi: "+Zombie.killedByHuman);
         */
        while(epoch < numberOfEpochs && zombies.size() > 0 && humans.size() > 0){
            Epoch();
        }
        /*
        System.out.println("Statystyki koncowe");
        System.out.println("Liczba epok: "+epoch);
        System.out.println("Liczba ludzi: "+Human.humanPopulation);
        System.out.println("Liczba zombie: "+Zombie.zombiePopulation);
        System.out.println("Ludzie zabici przez zombie: "+Human.killedByZombie);
        System.out.println("Ludzie zabici przez glod: "+Human.starvedToDeath);
        System.out.println("Zombie zabite przez ludzi: "+Zombie.killedByHuman);
        */
        savingResults("data.csv", this.numberOfEpochs, this.numberOfHumansInitially , Human.humanPopulation, Human.killedByZombie, this.numberOfZombieInitially, Zombie.zombiePopulation, csvWriter);

    }
    public static void savingResults(String fileName,int numberOfEpochs,int humansPopulationBefore, int humansPopulation,int humansKilledByZombie,int zombiePopulationBefore, int zombiePopulation, FileWriter csvWriter) throws IOException {

        csvWriter.append(Integer.valueOf(numberOfEpochs).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humansPopulationBefore).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humansPopulation).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humansKilledByZombie).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(zombiePopulationBefore).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(zombiePopulation).toString());
        csvWriter.append("\n");

    }



}
