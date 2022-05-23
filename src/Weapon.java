public class Weapon extends Item{
    String itemType = "Weapon";
    int combatStat;

    Weapon(int combatStat){
        this.combatStat = combatStat;
    }
    Weapon(Weapon weapon){
        this.combatStat = weapon.combatStat;
    }

    public String getType(){
        return itemType;
    }
    public int getStat(){
        return combatStat;
    }
}
