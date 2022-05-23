import java.util.ArrayList;

public abstract class Area {


    abstract public String getType();
    abstract public ArrayList<Item> getAvailableLoot();
    abstract public void updateAvailableLoot(ArrayList<Item> availableLoot);
}
