package lab3.cells;

public abstract class Cell {

    public Cell(){

    }
    public abstract void setIsShot();
    public abstract boolean getIsShot();
    public abstract boolean getIsDestroyed();
    public abstract boolean getShipCanBePlaced();
    public abstract void lockedToPlaceShip();
    public abstract void setIsDestroyed();
}
