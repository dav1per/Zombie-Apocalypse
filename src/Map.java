import java.util.Random;

/**
 * This class Map is where the simulation's map is created.
 */
public class Map {
    int mapSize;
    double percentageOfHouses;
    Random rand = new Random();

    //House parameters
    int houseLootAmountRange;
    double houseWeaponLootPercentage;

    /**
     * Constructor Map is for saving data provided by the user.
     * @param mapSize The number of map size.
     * @param percentageOfHouses The number of percentage of houses.
     * @param houseLootAmountRange The number of house loot amount range.
     * @param houseWeaponLootPercentage The number of weapon loot percentage.
     */
    Map(int mapSize, double percentageOfHouses, int houseLootAmountRange, double houseWeaponLootPercentage){
        this.mapSize = mapSize;
        this.percentageOfHouses = percentageOfHouses;
        this.houseLootAmountRange = houseLootAmountRange;
        this.houseWeaponLootPercentage = houseWeaponLootPercentage;
    }

    /**
     * This method name Area is for creating Map.
     * Map place where simulation is done.
     * @return Array with Area objects.
     */
    public Area [][] Generator(){
        Area Map[][] = new Area[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(rand.nextDouble() < percentageOfHouses){
                    Map[i][j] = new House(houseLootAmountRange, houseWeaponLootPercentage);
                }else{
                    Map[i][j] = new Land();
                }
            }
        }
        return Map;
    }
}
