import java.util.ArrayList;

/**
 * This class Water is class which inherits from class Area.
 */
public class Water extends Area{
    String areaType = "Water";
    ArrayList<Item> availableLoot = new ArrayList<Item>();

    /**
     * This method named getType is for returning type of area on the current field.
     * In this class it is "Water".
     * @return String with type of area, in this class "Water".
     */
    public String getType(){
        return areaType;
    }
    /**
     * This method named getAvailableLoot id for returning Array with available loot on the current field.
     * @return Array with Items.
     */
    public ArrayList<Item> getAvailableLoot(){return availableLoot;}


    /**
     * This method named updateAvailableLoot is for updating available loot in the current field.
     * @param availableLoot is ArrayList in which are items in the current field.
     */
    public void updateAvailableLoot(ArrayList<Item> availableLoot){
        this.availableLoot = availableLoot;
    }
}
