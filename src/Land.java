import java.util.ArrayList;

public class Land extends Area{
    String areaType = "Land";
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
