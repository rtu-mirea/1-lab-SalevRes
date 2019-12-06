package cells;

import java.awt.*;

public abstract class Cell {

    public Cell(){

    }
    public abstract void setIsShot();
    public abstract boolean getIsShot();
    public abstract boolean getIsDestroyed();
    public abstract boolean getShipCanBePlaced();
    public abstract void lockedToPlaceShip();
    public abstract void unlockedToPlaceShip();
    public abstract void setIsDestroyed();
    public abstract Color getColor(Boolean ally);
}
