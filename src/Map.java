import java.util.Random;

public class Map {
    int mapSize;
    double percentageOfHouses;
    int minNumberOfHumansPerHouse;
    int maxNumberOfHumansPerHouse;
    Random rand = new Random();

    //House parameters
    int houseLootAmountRange;
    double houseWeaponLootPercentage;

    Map(int mapSize, double percentageOfHouses, int minNumberOfHumansPerHouse, int maxNumberOfHumansPerHouse, int houseLootAmountRange, double houseWeaponLootPercentage){
        this.mapSize = mapSize;
        this.percentageOfHouses = percentageOfHouses;
        this.minNumberOfHumansPerHouse = minNumberOfHumansPerHouse;
        this.maxNumberOfHumansPerHouse = maxNumberOfHumansPerHouse;
        this.houseLootAmountRange = houseLootAmountRange;
        this.houseWeaponLootPercentage = houseWeaponLootPercentage;
    }

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
