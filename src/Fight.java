import java.util.ArrayList;
import java.util.Random;

/**
 * This class Fight is for creating fight when zombie and human are at the same place.
 */
public class Fight {
    Random rand = new Random();

    /**
     * This method named fight is where the coordinates of zombies and people are checked and,
     * in the case of the same places, a fight is carried out.
     *
     * @param humans is ArrayList from which the coordinates of humans are fetched.
     * @param zombies is ArrayList from which the coordinates of zombie are fetched.
     * @param mapSize size of simulation's map, necessary to check if the coordinates fit on the map.
     */
    public void fight(ArrayList<Human> humans, ArrayList<Zombie> zombies, int mapSize){
        int newZombie=0;
        int allCombatStatsZombies = 0;
        int allCombatStatsHumans = 0;
        int zombieCount =0;
        int humansCount =0;
        ArrayList<Human> humansToRemove = new ArrayList<>();
        ArrayList<Zombie> zombiesToRemove = new ArrayList<>();

        // x=i, y=j
        for (int i = 0; i < mapSize; i++)
            for(int j = 0; j < mapSize; j++){
                for(Zombie zombie : zombies)
                    if(zombie.x == i && zombie.y == j){
                        allCombatStatsZombies += zombie.combatStat;
                        zombiesToRemove.add(zombie);
                        zombieCount += 1;
                    }
                if(zombieCount !=0) {
                    for (Human human : humans)
                        if (human.x == i && human.y == j) {
                            allCombatStatsHumans += human.getCombatStat();
                            humansToRemove.add(human);
                            humansCount += 1;
                        }

                    if ((allCombatStatsZombies > allCombatStatsHumans) && humansCount !=0) {
                        for (Human human1 : humansToRemove) {
                            Human.humanPopulation -= 1;
                            humans.remove(human1);
                            Human.killedByZombie += 1;
                            newZombie += 1;
                        }

                        if(newZombie != 0)
                            for(int k = 0; k < newZombie; k++) {
                                zombies.add(new Zombie(30, i, j));
                            }

                    }else if(allCombatStatsZombies < allCombatStatsHumans && zombieCount != 0){
                        for (Zombie zombie1 : zombiesToRemove){
                            Zombie.zombiePopulation -= 1;
                            zombies.remove(zombie1);
                            Zombie.killedByHuman += 1;
                        }
                    }

                    newZombie = 0;
                    allCombatStatsHumans =0;
                    allCombatStatsZombies =0;
                    zombieCount =0;
                    humansCount =0;
                }
                humansToRemove.clear();
                zombiesToRemove.clear();
            }

    }

    /**
     * This method named fight_2 is new fight model, where zombies/humans are allowed to take revenge kills
     *
     * @param humans is ArrayList from which the coordinates of humans are fetched.
     * @param zombies is ArrayList from which the coordinates of zombie are fetched.
     * @param mapSize size of simulation's map, necessary to check if the coordinates fit on the map.
     * @param zombieCombatStat Number of zombie combat stats.
     */
    //New fight model, where zombies/humans are allowed to take revenge kills
    public void fight_2(ArrayList<Human> humans, ArrayList<Zombie> zombies, int mapSize, int zombieCombatStat){
        double combatStatMap[][] = new double[mapSize][mapSize];
        double zombieMap[][] = new double[mapSize][mapSize];
        double humanMap[][] = new double[mapSize][mapSize];

        ArrayList<Human> humansToRemove = new ArrayList<>();
        ArrayList<Zombie> zombiesToRemove = new ArrayList<>();
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                combatStatMap[i][j] = 0;
                zombieMap[i][j] = 0;
                humanMap[i][j] = 0;
            }
        }

        for(Human human : humans){
            combatStatMap[human.x][human.y] += human.getCombatStat();
            humanMap[human.x][human.y] += 1;
        }

        for(Zombie zombie : zombies){
            combatStatMap[zombie.x][zombie.y] -= zombie.combatStat;
            zombieMap[zombie.x][zombie.y] += 1;
        }

        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(combatStatMap[i][j] < 0){
                    double zombieHumanRatio = humanMap[i][j] / zombieMap[i][j];
                    if(zombieHumanRatio > 1){
                        zombieHumanRatio = 1;
                    }
                    if(rand.nextDouble() - 0.01 < zombieHumanRatio){
                        int zombieCount = (int) zombieMap[i][j];
                        zombieCount /= 2;
                        if(zombieCount == 0){
                            zombieCount += 1;
                        }
                        int humanRevengeCount = rand.nextInt(zombieCount);
                        for(Zombie zombie : zombies){
                            if(zombie.x == i && zombie.y == j){
                                if(humanRevengeCount > 0){
                                    humanRevengeCount -= 1;
                                    zombiesToRemove.add(zombie);
                                    Zombie.zombiePopulation -= 1;
                                    Zombie.killedByHuman += 1;
                                }
                            }
                        }
                    }
                    for(Human human : humans){
                        if(human.x == i && human.y == j){
                            humansToRemove.add(human);
                            Human.killedByZombie += 1;
                            Human.humanPopulation -= 1;
                            zombies.add(new Zombie(zombieCombatStat, i, j));
                        }
                    }
                }else if(combatStatMap[i][j] > 0){
                    double zombieHumanRatio = zombieMap[i][j] / humanMap[i][j];
                    if(zombieHumanRatio > 1){
                        zombieHumanRatio = 1;
                    }
                    if(rand.nextDouble() - 0.01 < zombieHumanRatio){
                        int zombieRevengeCount = rand.nextInt((int) humanMap[i][j]);
                        for(Human human : humans){
                            if(human.x == i && human.y == j){
                                if(zombieRevengeCount > 0){
                                    zombieRevengeCount -= 1;
                                    humansToRemove.add(human);
                                    Human.killedByZombie += 1;
                                    Human.humanPopulation -= 1;
                                    zombies.add(new Zombie(zombieCombatStat, i, j));
                                }
                            }
                        }
                    }
                    for(Zombie zombie : zombies){
                        if(zombie.x == i && zombie.y == j){
                            zombiesToRemove.add(zombie);
                            Zombie.zombiePopulation -= 1;
                            Zombie.killedByHuman += 1;
                        }
                    }
                }
            }
        }

        for(Human human1 : humansToRemove){
            humans.remove(human1);
        }

        for(Zombie zombie1: zombiesToRemove){
            zombies.remove(zombie1);
        }
        humansToRemove.clear();
        zombiesToRemove.clear();
    }
}
