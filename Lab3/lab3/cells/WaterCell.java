package lab3.cells;

public class WaterCell extends Cell {

    public WaterCell(){

    }
    private boolean shipCanBePlaced = true;
    private boolean isShot = false;


    public void setIsShot(){
        isShot = true;
    }
    public boolean getIsShot(){
        return isShot;
    }

    public void lockedToPlaceShip(){shipCanBePlaced = false;}
    public boolean getShipCanBePlaced(){return shipCanBePlaced;}

    public boolean getIsDestroyed(){return false;}
    public void setIsDestroyed(){}

}
