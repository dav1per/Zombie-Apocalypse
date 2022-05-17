public class Weapon extends Item{
    String itemType = "Weapon";
    int combatStat;

    Weapon(int combatStat){
        this.combatStat = combatStat;
    }

    public String getType(){
        return itemType;
    }
}
