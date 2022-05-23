public class Food extends Item{
    String itemType = "Food";
    int hungerPoints;
    Food(int hungerPoints){
        this.hungerPoints = hungerPoints;
    }
    @Override
    public String getType() {
        return itemType;
    }
    public int getStat(){
        return hungerPoints;
    }
}
