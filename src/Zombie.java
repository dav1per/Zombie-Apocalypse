import java.util.ArrayList;
import java.util.Objects;


public class Zombie {
    static int zombiePopulation = 0;
    int combatStat;
    int x, y;
    int visionRange;

    Zombie(int combatStat, int x, int y) {
        this.combatStat = combatStat;
        this.x = x;
        this.y = y;
        zombiePopulation += 1;
        visionRange = 10;

    }
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
