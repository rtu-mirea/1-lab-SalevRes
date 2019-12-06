package cells;

import java.awt.*;

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
    public void unlockedToPlaceShip(){shipCanBePlaced = true;}
    public boolean getShipCanBePlaced(){return shipCanBePlaced;}

    public boolean getIsDestroyed(){return false;}
    public void setIsDestroyed(){}
    public Color getColor(Boolean ally){

        if(this.getIsShot()){
            return new Color(0,0,120);
        }
        else if (!this.getShipCanBePlaced() & ally){
            return new Color(67, 111, 148);
        }
        else{
            return new Color (0, 128, 255);
        }

    }

}
