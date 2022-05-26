import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.lang.Math;


public class Human {
    static int humanPopulation = 0;
    static int killedByZombie = 0;
    static int starvedToDeath = 0;
    int combatStat;
    int x, y;
    int healthPoints;
    int hungerPoints;
    int visionRange;
    int direction;
    int liftingCapacity = 5;
    boolean alive = true;
    Random rand = new Random();
    ArrayList<Food> backpack = new ArrayList<>();
    Weapon weapon = new Weapon(0);
    //Saved x, y coordinates of 5 last visited houses
    int visitedHouses = 5;
    int xVisitedHouses[] = new int [visitedHouses];
    int yVisitedHouses[] = new int [visitedHouses];

    Human(int combatStat, int x, int y){
        this.combatStat = combatStat;
        this.x = x;
        this.y = y;
        humanPopulation += 1;
        healthPoints = 100;
        hungerPoints = 0;
        visionRange = 10;
        liftingCapacity = 5;
        direction = rand.nextInt(4);
        /*
        Direction of movement
        0 - North
        1 - West
        2 - South
        3 - East
         */
        for(int i = 0; i < visitedHouses; i++){
            xVisitedHouses[i] = -1;
            yVisitedHouses[i] = -1;
        }
    }

    public void selfCheck(){
        if(hungerPoints == 100){
            healthPoints -= 10;
        }
        if(hungerPoints == 0 && healthPoints < 100){
            healthPoints += 5;
            if(healthPoints > 100){
                healthPoints = 100;
            }
        }
        if(healthPoints <= 0){
            alive = false;
            humanPopulation -= 1;
            starvedToDeath += 1;
        }
    }
    public void eatIfHungry(){
        if(hungerPoints > 0){
            while(backpack.size() > 0 && hungerPoints > 0){
                hungerPoints -= backpack.get(0).getStat();
                backpack.remove(0);
                if(hungerPoints < 0){
                    hungerPoints = 0;
                }
            }
        }
    }
    public void getHungry(){
        if(hungerPoints < 100){
            hungerPoints += 10;
            if(hungerPoints > 100){
                hungerPoints = 100;
            }
        }
    }

    public void lootHouses(Area Map[][]){
        if(Map[x][y].getType() == "House"){
            ArrayList<Item> availableLoot = Map[x][y].getAvailableLoot();
            for(int i = 0; i < availableLoot.size(); i++){
                if(availableLoot.get(i).getType() == "Weapon"){
                    //Comparing weapon combat stat in order to choose the stronger one
                    if(availableLoot.get(i).getStat() > weapon.combatStat){
                        int oldWeaponCombatStat = weapon.combatStat;
                        weapon = new Weapon(availableLoot.get(i).getStat());
                        availableLoot.set(i, new Weapon(oldWeaponCombatStat));
                    }
                }else{
                    if(backpack.size() < liftingCapacity){
                        backpack.add(new Food(availableLoot.get(i).getStat()));
                        availableLoot.remove(i);
                        i -= 1;
                    }
                }
            }

            Map[x][y].updateAvailableLoot(availableLoot);
            //Saving the coordinates of the last five visited houses to prevent humans from looting the same house twice
            for(int j = 1; j < visitedHouses; j++){
                xVisitedHouses[j] = xVisitedHouses[j - 1];
                yVisitedHouses[j] = yVisitedHouses[j - 1];
            }
            xVisitedHouses[0] = x;
            yVisitedHouses[0] = y;
        }
    }

    public double getCombatStat(){
        return (combatStat + weapon.combatStat) * healthPoints / 100;
    }

    public void selfMove(Area Map[][], int mapSize){
        int xDestination = 0, yDestination = 0;
        double objectiveDistance = Math.sqrt((mapSize * mapSize) * 2);
        double distanceCheck;
        boolean houseFound = false;

        //Looking for houses
        //i - x
        //j - y
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(Math.pow(i - x, 2) + Math.pow(j - y, 2) < Math.pow(visionRange, 2)){
                    if(Objects.equals(Map[i][j].getType(), "House")) {
                        if (!(i == x && j == y)) {
                            for(int c = 0; c < visitedHouses; c++){
                                if(i != xVisitedHouses[c] && j != xVisitedHouses[c]){
                                    //House found
                                    houseFound = true;
                                    distanceCheck = Math.sqrt(Math.pow(i - x, 2) + Math.pow(j - y, 2));
                                    if (distanceCheck < objectiveDistance) {
                                        xDestination = i;
                                        yDestination = j;
                                        objectiveDistance = distanceCheck;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int xCheck, yCheck;
        if(houseFound){
            /*
            Checking 8 possible moves, choosing the best one to achieve the destination.
             */
            distanceCheck = Math.sqrt((mapSize * mapSize) * 2);
            xCheck = x;
            yCheck = y;

            //Move no. 1
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y && y < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - y, 2))){
                    if(!Objects.equals(Map[x + 1][y].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - y, 2));
                        xCheck = x + 1;
                        yCheck = y;
                    }
                }
            }

            //Move no. 2
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x + 1][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x + 1;
                        yCheck = y + 1;
                    }
                }
            }

            //Move no. 3
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x + 1][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x + 1;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 4
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y && y < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - y, 2))){
                    if(!Objects.equals(Map[x - 1][y].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - y, 2));
                        xCheck = x - 1;
                        yCheck = y;
                    }
                }
            }

            //Move no. 5
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x - 1][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x - 1;
                        yCheck = y + 1;
                    }
                }
            }

            //Move no. 6
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x - 1][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x - 1;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 7
            if(x < mapSize && 0 <= x && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 8
            if(x < mapSize && 0 <= x && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x;
                        yCheck = y + 1;
                    }
                }
            }
            x = xCheck;
            y = yCheck;
        }else{
            switch(direction % 4){
                case 0:
                    xDestination = x;
                    yDestination = y + 10;
                    break;
                case 1:
                    xDestination = x - 10;
                    yDestination = y;
                    break;
                case 2:
                    xDestination = x;
                    yDestination = y - 10;
                    break;
                case 3:
                    xDestination = x + 10;
                    yDestination = y;
                    break;
            }
            distanceCheck = Math.sqrt((mapSize * mapSize) * 2) * 1000;
            xCheck = x;
            yCheck = y;

            //Move no. 1
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y && y < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - y, 2))){
                    if(!Objects.equals(Map[x + 1][y].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - y, 2));
                        xCheck = x + 1;
                        yCheck = y;
                    }
                }
            }

            //Move no. 2
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x + 1][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x + 1;
                        yCheck = y + 1;
                    }
                }
            }

            //Move no. 3
            if(x + 1 < mapSize && 0 <= x + 1 && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x + 1][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x + 1), 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x + 1;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 4
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y && y < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - y, 2))){
                    if(!Objects.equals(Map[x - 1][y].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - y, 2));
                        xCheck = x - 1;
                        yCheck = y;
                    }
                }
            }

            //Move no. 5
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x - 1][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x - 1;
                        yCheck = y + 1;
                    }
                }
            }

            //Move no. 6
            if(x - 1 < mapSize && 0 <= x - 1 && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x - 1][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - (x - 1), 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x - 1;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 7
            if(x < mapSize && 0 <= x && 0 <= y - 1 && y - 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y - 1), 2))){
                    if(!Objects.equals(Map[x][y - 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y - 1), 2));
                        xCheck = x;
                        yCheck = y - 1;
                    }
                }
            }

            //Move no. 8
            if(x < mapSize && 0 <= x && 0 <= y + 1 && y + 1 < mapSize){
                if(distanceCheck > Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y + 1), 2))){
                    if(!Objects.equals(Map[x][y + 1].getType(), "Water")){
                        distanceCheck = Math.sqrt(Math.pow(xDestination - x, 2) + Math.pow(yDestination - (y + 1), 2));
                        xCheck = x;
                        yCheck = y + 1;
                    }
                }
            }
            x = xCheck;
            y = yCheck;

            if(rand.nextInt(100) + 1 < 20){
                direction += 1;
            }
        }


    }

    public void humanEverydayRoutine(Area Map[][], int mapSize){
        getHungry();
        selfCheck();
        selfMove(Map, mapSize);
        lootHouses(Map);
        eatIfHungry();
    }

}
