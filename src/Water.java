import java.util.ArrayList;

public class Water extends Area{
    String areaType = "Water";
    ArrayList<Item> availableLoot = new ArrayList<Item>();
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
