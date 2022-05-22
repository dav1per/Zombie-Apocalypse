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
    @org.junit.jupiter.api.Test
    public void HumanMoveTest_3(){
        int mapSize = 3;
        Area Map[][] = new Area[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                Map[i][j] = new Land();
            }
        }
        
        Human human1 = new Human(30, 1, 1);
        int xExpected = 0;
        int yExpected = 0;
        switch(human1.direction){
            case 0:
                xExpected = 1;
                yExpected = 2;
                break;
            case 1:
                xExpected = 0;
                yExpected = 1;
                break;
            case 2:
                xExpected = 1;
                yExpected = 0;
                break;
            case 3:
                xExpected = 2;
                yExpected = 1;
                break;
        }
        human1.selfMove(Map, mapSize);
        assertEquals(yExpected, human1.y);
        assertEquals(xExpected, human1.x);
    }
     
    @org.junit.jupiter.api.Test
    public void ZombieMoveTest1(){
        int mapSize = 7;
        ArrayList<Human> humans = new ArrayList<>();
        humans.add(new Human(30,3,2));
        humans.add(new Human(30,2,1));
        humans.add(new Human(30, 3,6));

        Area Map[][] = new Area[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                Map[i][j] = new Land();
            }
        }
        Zombie zombie1 = new Zombie(35,4,6);

        zombie1.selfMove(Map, mapSize, humans);
        assertEquals(3, zombie1.x);
        assertEquals(6, zombie1.y);

    }
}
