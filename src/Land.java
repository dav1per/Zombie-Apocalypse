import java.util.ArrayList;

/**
 * This class Land is class which inherits from class Area.
 */
public class Land extends Area{
    String areaType = "Land";
    ArrayList<Item> availableLoot = new ArrayList<Item>();

    /**
     * This method named getType is returning type of area.
     * In this class it is "Land".
     * @return String with type of area, in this class it is "Land".
     */
    public String getType(){
        return areaType;
    }

    /**
     *This method named getAvailableLoot is for returning ArrayList with available loot in the current field.
     * @return ArrayList with available loot in the current field.
     */
    public ArrayList<Item> getAvailableLoot(){
        return availableLoot;
    }

    /**
     * This method named updateAvailableLoot is for updating ArrayList with available loot in the current field after using it.
     * @param availableLoot ArrayList in which are items in the current field.
     */
    public void updateAvailableLoot(ArrayList<Item> availableLoot){
        this.availableLoot = availableLoot;
    }
}
