import java.util.ArrayList;
import java.util.Random;
public class House extends Area{
    String areaType = "House";
    ArrayList<Item> availableLoot = new ArrayList<Item>();
    Random rand = new Random();
    int lootAmountRange;
    double weaponLootPercentage;
    House(int lootAmountRange, double weaponLootPercentage){
        for(int i = 0; i < rand.nextInt(lootAmountRange + 1); i++){
            if (rand.nextDouble() < weaponLootPercentage){
                //Weapon combat stat 5 - 20
                availableLoot.add(new Weapon(rand.nextInt(16 + 5)));
            }else{
                //Food hunger points 25 - 50
                availableLoot.add(new Food(rand.nextInt(26 + 25)));
            }
        }
    }
    public String getType(){
        return areaType;
    }
    public ArrayList<Item> getAvailableLoot(){
        return availableLoot;
    }
    public void updateAvailableLoot(ArrayList<Item> availableLoot){
        this.availableLoot = availableLoot;
    }

}
