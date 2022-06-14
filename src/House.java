import java.util.ArrayList;
import java.util.Random;
/**
 * This class House is class which inherits from class Area.
 * In house are items to collect by the human like weapon or food.
 */
public class House extends Area{
    String areaType = "House";
    ArrayList<Item> availableLoot = new ArrayList<Item>();
    Random rand = new Random();

    /**
     * Constructor for adding weapon and food to the house.
     * @param lootAmountRange Number of loot amount range entered by the user.
     * @param weaponLootPercentage Number of weapon loot percentage entered by the user.
     */
    House(int lootAmountRange, double weaponLootPercentage){
        for(int i = 0; i < rand.nextInt(lootAmountRange) + 1; i++){
            if (rand.nextDouble() < weaponLootPercentage){
                //Weapon combat stat 5 - 95
                availableLoot.add(new Weapon(rand.nextInt(91) + 5));
            }else{
                //Food hunger points 15 - 25
                availableLoot.add(new Food(rand.nextInt(11) + 15));
            }
        }
    }
    /**
     * This method named getType is for returning type of area, in this class return "House".
     * @return type of area, in this class return "House".
     */
    public String getType(){
        return areaType;
    }

    /**
     * This method named getAvailableLoot is for returning ArrayList with available loot in a house.
     * @return ArrayList with available loot in a house.
     */
    public ArrayList<Item> getAvailableLoot(){
        return availableLoot;
    }

    /**
     * This method named updateAvailableLoot is for updating ArrayList with available loot in a house after using it.
     * @param availableLoot is ArrayList in which are items in a single house.
     */
    public void updateAvailableLoot(ArrayList<Item> availableLoot){
        this.availableLoot = availableLoot;
    }
}
