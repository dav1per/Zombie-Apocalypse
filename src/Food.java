/**
 * This class Food is class which inherits from class Item.
 */
public class Food extends Item{
    String itemType = "Food";
    int hungerPoints;

    /**
     * Constructor Food sets hunger points.
     * @param hungerPoints random number of hunger points in this item in house.
     */
    Food(int hungerPoints){
        this.hungerPoints = hungerPoints;
    }

    /**
     * This method getType is for returning type of item.
     * @return type of item, in this class "Food".
     */
    @Override
    public String getType() {
        return itemType;
    }

    /**
     * This method getStat is for returning number of hunger points.
     * @return hunger points of this object.
     */
    public int getStat(){
        return hungerPoints;
    }
}

