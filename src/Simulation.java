import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    ArrayList<Human> humans = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    int mapSize;
    int numberOfEpochs;
    int epoch = 0;
    Random rand = new Random();
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
    ){
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

        for(Zombie zombie : zombies){
            zombie.selfMove(Map, mapSize, humans);
        }

    }

    public void Simulation(){
        while(epoch < numberOfEpochs + 1 && zombies.size() > 0 && humans.size() > 0){
            Epoch();
        }
    }



}
