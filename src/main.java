import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        int mapSize = 100;
        Area Map[][] = new Area[mapSize][mapSize];
        Map[0][0] = new House();
        System.out.print(Map[0][0].getType());


    }
}
