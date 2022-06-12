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
    ) {
        Human.killedByZombie =0;
        Zombie.zombiePopulation =0;
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

        //Super human test
        /*
        humans.add(new Human(250000, 0, 0));
        */
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

    public void Simulation(String filename)throws IOException{
        FileWriter csvWriter = new FileWriter(filename+".csv");
        csvWriter.append("Epoch number");
        csvWriter.append(",");
        csvWriter.append("number of human population");
        csvWriter.append(",");
        csvWriter.append("number of humans killed by zombie");
        csvWriter.append(",");
        csvWriter.append("number of humans starved to death");
        csvWriter.append(",");
        csvWriter.append("number of zombie population");
        csvWriter.append(",");
        csvWriter.append("number of zombies killed by humans");
        csvWriter.append("\n");

        savingResults(epoch, Human.humanPopulation, Human.killedByZombie, Human.starvedToDeath, Zombie.zombiePopulation, Zombie.killedByHuman, csvWriter);
        while(epoch < numberOfEpochs && zombies.size() > 0 && humans.size() > 0){
            Epoch();
            savingResults(epoch, Human.humanPopulation, Human.killedByZombie, Human.starvedToDeath, Zombie.zombiePopulation, Zombie.killedByHuman, csvWriter);
        }
        csvWriter.flush();
        csvWriter.close();
        System.out.println("the data has been stored in the "+filename+".csv file");


    }
    public static void savingResults(int epoch, int humanPopulation, int humansKilledByZombie, int humansStarvedToDeath, int zombiePopulation, int zombiesKilledByHumans, FileWriter csvWriter) throws IOException {

        csvWriter.append(Integer.valueOf(epoch).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humanPopulation).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humansKilledByZombie).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(humansStarvedToDeath).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(zombiePopulation).toString());
        csvWriter.append(",");
        csvWriter.append(Integer.valueOf(zombiesKilledByHumans).toString());
        csvWriter.append(",");
        csvWriter.append("\n");



        /*
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
        */


    }



}
