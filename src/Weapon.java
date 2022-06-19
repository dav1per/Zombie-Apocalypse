/**
 * This class Weapon is class which inherits from class Item.
 */
public class Weapon extends Item{
    String itemType = "Weapon";
    int combatStat;


    /**
     * Constructor Weapon is for saving combat stats.
     * @param combatStat Number of combat stats.
     */
    Weapon(int combatStat){
        this.combatStat = combatStat;
    }

    /**
     * Constructor Weapon is for saving combat stats to object from Weapon class.
     * @param weapon Object from Weapon class.
     */
    Weapon(Weapon weapon){
        this.combatStat = weapon.combatStat;
    }


    /**
     * This method named getType is for returning type of item.
     * In this class it is "Weapon".
     * @return String with type of item. In this class "Weapon".
     */
    public String getType(){
        return itemType;
    }


    /**
     * This method getStat is for returning combat stats which this weapon has.
     * @return Number of weapon's combat stats.
     */
    public int getStat(){
        return combatStat;
    }
}
