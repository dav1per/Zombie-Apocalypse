import java.util.ArrayList;

public class Fight {
        public void fight(ArrayList<Human> humans, ArrayList<Zombie> zombies, int mapSize){
            int newZombie=0;
            int allCombatStatsZombies = 0;
            int allCombatStatsHumans = 0;
            int zombieCount =0;
            int humansCount =0;
            ArrayList<Human> humansToRemove = new ArrayList<>();

            // x=i, y=j
            for (int i=0; i< mapSize ; i++)
                for(int j=0; j<mapSize; j++){
                    for(Zombie zombie : zombies)
                        if(zombie.x == i && zombie.y==j){
                            zombieCount +=1;
                            allCombatStatsZombies += zombie.combatStat;
                        }
                    if(zombieCount !=0) {
                        for (Human human : humans)
                            if (human.x == i && human.y == j) {
                                allCombatStatsHumans += human.combatStat;
                                humansToRemove.add(human);
                                humansCount +=1;
                            }

                        if ((allCombatStatsZombies > allCombatStatsHumans) && humansCount !=0) {
                            for (Human human1 : humansToRemowe) {
                                human1.healthPoints = 0;
                                Human.humanPopulation -= 1;
                                humans.remove(human1);
                                Human.killedByZombie += 1;
                                newZombie += 1;
                            }

                            if(newZombie!=0)
                                for(int k=0; k<newZombie; k++) {
                                    zombies.add(new Zombie(30, i, j));
                                }

                        }

                        newZombie = 0;
                        allCombatStatsHumans =0;
                        allCombatStatsZombies =0;
                        zombieCount =0;
                        humansCount =0;
                    }
                    humansToRemove.clear();
                }

        }
}
