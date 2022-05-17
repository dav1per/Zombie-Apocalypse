import static org.junit.Assert.assertEquals;

public class Test {
    @org.junit.jupiter.api.Test
    public void HumanMoveTest_1(){
        int mapSize = 2;
        Area Map[][] = new Area[mapSize][mapSize];
        Map[0][0] = new House();
        Map[0][1] = new Land();
        Map[1][0] = new Land();
        Map[1][1] = new Land();

        Human human1 = new Human(30, 0, 1);
        human1.selfMove(Map, mapSize);
        assertEquals(0, human1.y);
    }
    @org.junit.jupiter.api.Test
    public void HumanMoveTest_2(){
        int mapSize = 4;
        Area Map[][] = new Area[mapSize][mapSize];
        Map[0][0] = new House();
        Map[0][1] = new Land();
        Map[0][2] = new Land();
        Map[0][3] = new House();

        for(int i = 1; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                Map[i][j] = new Land();
            }
        }

        Human human1 = new Human(30, 0, 2);
        human1.selfMove(Map, mapSize);
        assertEquals(3, human1.y);
        assertEquals(0, human1.x);
    }
}
