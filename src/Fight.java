import java.util.ArrayList;
import java.util.Random;

public class Fight {
        public void fight(ArrayList<Human> humans, ArrayList<Zombie> zombies){
            int newZombie=0;
            Random rand = new Random();
            for(Human human : humans)
                for(Zombie zombie : zombies)
                    if(human.x== zombie.x)
                        if(human.y == zombie.y)
                            if(zombie.combatStat > human.combatStat){
                                human.healthPoints = 0;
                                human.humanPopulation -=1;
                                humans.remove(human);
                                Human.killedByZombie +=1;
                                newZombie +=1;
                                zombie.zombiePopulation +=1;
                            }

            if(newZombie!=0){
                for(int i=0; i<newZombie; i++) {
                    zombies.add(new Zombie(30, rand.nextInt(100), rand.nextInt(100)));
                }
            }
        }
}