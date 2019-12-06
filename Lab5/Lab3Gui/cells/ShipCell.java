package cells;

import java.awt.*;

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
    public void unlockedToPlaceShip(){}
    public boolean getIsDestroyed(){return isDestroyed;}
    public void setIsDestroyed(){isDestroyed = true;}
    public Color getColor(Boolean ally){
        if(this.getIsDestroyed()){
            return new Color(90, 104, 117);
        }
        else if(this.getIsShot()){
            return new Color(255, 0, 0);
        }
        if (ally){
            return new Color(0, 0, 0);
        }
        else{
            return new Color (0, 128, 255);
        }

    }

}
