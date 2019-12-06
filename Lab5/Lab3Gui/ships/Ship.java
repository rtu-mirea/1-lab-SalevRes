package ships;

import cells.*;
import java.util.Random;

public class Ship {

    private Cell[] shipBody;
    private boolean isHorizontal = true;
    private boolean destroyed = false;
    private boolean isPlaced = false;



    private String shipName;

    public Ship(int shipLength){
        switch (shipLength){
            case (4):
                shipName = "Линкор";
                break;
            case (3):
                shipName= "Крейсер";
                break;
            case (2):
                shipName ="Эсминец";
                break;
            default:
                shipName = "Субмарина";
                shipLength = 1;
        }
        shipBody = new Cell[shipLength];
        fillBody();

    }
    public String getShipName() {
        return shipName;
    }

    private void fillBody(){
        for (int i = 0; i<shipBody.length;i++){
            shipBody[i]=new ShipCell();
        }
    }
    public boolean isShot(){
        for (int i =0; i<shipBody.length;i++){
            if (shipBody[i].getIsShot()==false){
                return false;
            }
        }
        setDestroyed();
        return true;
    }
    private void setDestroyed(){
        if (!destroyed){
            destroyed = true;
            for (int i = 0; i<shipBody.length; i++){
                shipBody[i].setIsDestroyed();
            }
        }
    }
    public boolean isNotDestroyed(){
        return !destroyed;
    }


    public Cell[] getShipBody(){
        return shipBody;
    }
    public int getSize(){
        return shipBody.length;
    }

    public void setHorizontal(){
        isHorizontal=true;
    }

    public void setVertical(){
        isHorizontal = false;
    }

    public boolean getIsHorizontal(){
        return isHorizontal;
    }
    public boolean getIsVertical(){
        return !isHorizontal;
    }

    public boolean getIsPlaced(){
        return isPlaced;
    }
    public void setIsPlaced(){
        isPlaced=true;
    }
    public void setRandomOrientation(){
        isHorizontal = new Random().nextBoolean();
    }

}
