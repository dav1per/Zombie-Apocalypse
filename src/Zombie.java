import java.util.ArrayList;
import java.util.Objects;

/**
 * This class Zombie is for creating zombie and their lives.
 */

public class Zombie {
    static int zombiePopulation = 0;
    static int killedByHuman = 0;
    int combatStat;
    int x, y;
    int visionRange;


    /**
     * Constructor which sets the parameters provided by the user and increase zombie population.
     *
     * @param combatStat sets zombie's combat stats.
     * @param x number of the x zombie's location on the map.
     * @param y number of the y zombie's location on the map.
     */
    Zombie(int combatStat, int x, int y) {
        this.combatStat = combatStat;
        this.x = x;
        this.y = y;
        zombiePopulation += 1;
        visionRange = 40;

    }



    /**
     * This methode named selfMove is responsible for zombie's move to the nearest human.
     * @param Map simulation's map where zombie can move, Array with types of area.
     * @param mapSize size of simulation's map.
     * @param humans ArrayList of humans, necessary to check where the nearest human is.
     */

    public void selfMove(Area Map[][], int mapSize, ArrayList <Human> humans) {
        int xHumanLocation, yHumanLocation;
        int xTheNearestHuman = 101, yTheNearestHuman = 101;
        double theShortestDistance = Math.sqrt(mapSize * mapSize * 2);
        double distanceFromHuman;
        boolean humanFound = false;

        // Looking for the nearest human
        for (Human human : humans) {
            xHumanLocation = human.x;
            yHumanLocation = human.y;
            distanceFromHuman = Math.sqrt(Math.pow(xHumanLocation - x, 2) + Math.pow(yHumanLocation - y, 2));

            if (distanceFromHuman <= visionRange) {
                if (theShortestDistance > distanceFromHuman) {
                    xTheNearestHuman = xHumanLocation;
                    yTheNearestHuman = yHumanLocation;
                    theShortestDistance = distanceFromHuman;
                }
                humanFound = true;
            }

        }

        int xCheck, yCheck;
        if (humanFound) {
            /*
           Checking 8 possible moves, choosing the best one to achieve the destination.
             */
            xCheck = x;
            yCheck = y;


                //Move no. 1
                if (x + 1 < mapSize && 0 <= x + 1 && 0 <= y && y < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - y, 2))) {
                        if (!Objects.equals(Map[x + 1][y].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - y, 2));
                            xCheck = x + 1;
                            yCheck = y;
                        }
                    }
                }

                //Move no. 2
                if (x + 1 < mapSize && 0 <= x + 1 && 0 <= y + 1 && y + 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - (y + 1), 2))) {
                        if (!Objects.equals(Map[x + 1][y + 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - (y + 1), 2));
                            xCheck = x + 1;
                            yCheck = y + 1;
                        }
                    }
                }

                //Move no.3
                if (x + 1 < mapSize && 0 <= x + 1 && 0 <= y - 1 && y - 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - (y - 1), 2))) {
                        if (!Objects.equals(Map[x + 1][y - 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x + 1), 2) + Math.pow(yTheNearestHuman - (y - 1), 2));
                            xCheck = x + 1;
                            yCheck = y - 1;
                        }
                    }
                }

                //Move no. 4
                if (x - 1 < mapSize && 0 <= x - 1 && 0 <= y && y < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - y, 2))) {
                        if (!Objects.equals(Map[x - 1][y].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - y, 2));
                            xCheck = x - 1;
                            yCheck = y;
                        }
                    }
                }

                //Move no. 5
                if (x - 1 < mapSize && 0 <= x - 1 && 0 <= y + 1 && y + 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - (y + 1), 2))) {
                        if (!Objects.equals(Map[x - 1][y + 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - (y + 1), 2));
                            xCheck = x - 1;
                            yCheck = y + 1;
                        }
                    }
                }

                //Move no. 6
                if (x - 1 < mapSize && 0 <= x - 1 && 0 <= y - 1 && y - 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - (y - 1), 2))) {
                        if (!Objects.equals(Map[x - 1][y - 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - (x - 1), 2) + Math.pow(yTheNearestHuman - (y - 1), 2));
                            xCheck = x - 1;
                            yCheck = y - 1;
                        }
                    }
                }

                //Move no.7
                if (x < mapSize && 0 <= x && 0 <= y - 1 && y - 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - x, 2) + Math.pow(yTheNearestHuman - (y - 1), 2))) {
                        if (!Objects.equals(Map[x][y - 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - x, 2) + Math.pow(yTheNearestHuman - (y - 1), 2));
                            xCheck = x;
                            yCheck = y - 1;
                        }
                    }
                }

                //Move no. 8
                if (x < mapSize && 0 <= x && 0 <= y + 1 && y + 1 < mapSize) {
                    if (theShortestDistance > Math.sqrt(Math.pow(xTheNearestHuman - x, 2) + Math.pow(yTheNearestHuman - (y + 1), 2))) {
                        if (!Objects.equals(Map[x][y + 1].getType(), "Water")) {
                            theShortestDistance = Math.sqrt(Math.pow(xTheNearestHuman - x, 2) + Math.pow(yTheNearestHuman - (y + 1), 2));
                            xCheck = x;
                            yCheck = y + 1;
                        }
                    }
                }
                x = xCheck;
                y = yCheck;
        }
    }
}
