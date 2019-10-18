package lab3.cells;

public class ShipCell extends Cell {

    public ShipCell(){

    }
    private boolean isDestroyed = false;
    private boolean isShot = false;

    public void setIsShot(){
        isShot = true;
    }
    public boolean getIsShot(){
        return isShot;
    }

    public boolean getShipCanBePlaced(){return false;}
    public void lockedToPlaceShip(){}

    public boolean getIsDestroyed(){return isDestroyed;}
    public void setIsDestroyed(){isDestroyed = true;}
}
