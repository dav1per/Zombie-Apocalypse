/**
 * This class Item is for creating items which are in house.
 */

public abstract class Item {
    /**
     * This method named getType is returning type of item.
     * @return String with name of item.
     */
    abstract public String getType();

    /**
     * This method named getStat is for returning stat of item.
     * @return number which is stat of item.
     */
    abstract public int getStat();
}
