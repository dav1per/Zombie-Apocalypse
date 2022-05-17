import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        int mapSize = 2;
        Area Map[][] = new Area[mapSize][mapSize];
        Map[0][0] = new House();
        Map[0][1] = new Land();
        Map[1][0] = new Land();
        Map[1][1] = new Land();

        Human human1 = new Human(30, 0, 1);
        System.out.print(human1.x);
        System.out.print(human1.y);
        System.out.println();
        human1.selfMove(Map, mapSize);
        System.out.print(human1.x);
        System.out.print(human1.y);


    }
}
