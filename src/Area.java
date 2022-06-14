import java.util.ArrayList;
/**
 * This class Area is for creating different types of places in simulation's map.
 */

public abstract class Area {

    /**
     *This method named getType is for returning type of area on a single field.
     * @return String with name of type of area.
     */
    abstract public String getType();

    /**
     * This method named getAvailableLoot is for returning ArrayList with available loot in a current field.
     * @return ArrayList with available loot in a current field.
     */
    abstract public ArrayList<Item> getAvailableLoot();

    /**
     * This method named updateAvailableLoot is for updating available loot
     * in an ArrayList with items in the current field.
     * @param availableLoot is ArrayList in which are items in a current field.
     */
    abstract public void updateAvailableLoot(ArrayList<Item> availableLoot);
}