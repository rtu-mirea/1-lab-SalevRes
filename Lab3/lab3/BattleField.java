package lab3;
import lab3.cells.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BattleField {


    private Map<String,Integer> shipsCount = new HashMap<String,Integer>();
    private Cell[][] field;
    private ArrayList<Ship> shipsList = new ArrayList<>();
    public BattleField(){
        field = new Cell[10][10];
        setAllFieldsAsWaterCell();
        shipsCount.put("Линкор",0);
        shipsCount.put("Крейсер",0);
        shipsCount.put("Эсминец",0);
        shipsCount.put("Субмарина",0);
    }

    private void setAllFieldsAsWaterCell(){
        for (int i =0; i<10;i++){
            for (int j=0;j<10;j++){
                field[i][j] = new WaterCell();
            }
        }
    }
    private boolean canSetHorizontalShip(Ship ship, int x, int y){
        for (int i = x; i<x+ship.getSize(); i++){
            if (inRangeOfTen(i, y)){
                if (!field[i][y].getShipCanBePlaced()){
                    return false;
                }

            }
            else
                return false;
        }
        return true;
    }
    private boolean canSetVerticalShip(Ship ship, int x, int y){
        for (int i = y; i<y+ship.getSize(); i++){
            if (inRangeOfTen(x, i)){
                if (!field[x][i].getShipCanBePlaced()){
                    return false;
                }
            }
            else
                return false;
        }
        return true;
    }
    private boolean inRangeOfTen(int x, int y){
        return (x>=0 & x<=9 & y>=0 & y<=9);
    }

    private void setCellsClosedAroundShip(int xStart, int xFinish, int yStart, int yFinish){
        for (int i = xStart-1; i<=xFinish+1;i++){
            for (int j = yStart-1; j<=yFinish+1;j++){
                if(inRangeOfTen(i, j)){
                    field[i][j].lockedToPlaceShip();
                }
            }
        }
    }

    public Cell[][] getField() {
        return field;
    }
    public Cell getCell(int x, int y){
        return field[x][y];
    }

    public void placeShip(Ship ship, int x, int y){
        if (incrementCountOf(ship.getShipName())){
            if (ship.getIsHorizontal() & canSetHorizontalShip(ship, x, y) ){
                for (int i = 0;i<ship.getSize(); i++){
                    field[i+x][y] = ship.getShipBody()[i];
                }
                setCellsClosedAroundShip(x, x+ship.getSize()-1, y, y);
                ship.setIsPlaced();
                shipsList.add(ship);
            }
            else if (ship.getIsVertical() & canSetVerticalShip(ship, x, y)){
                for (int i = 0;i<ship.getSize(); i++){
                    field[x][i+y] = ship.getShipBody()[i];
                }
                setCellsClosedAroundShip(x, x, y, y+ship.getSize()-1);
                ship.setIsPlaced();
                shipsList.add(ship);
            }
            else{
                decrementCountOf(ship.getShipName());
            }

        }







    }
    /*public String getStringField(){
        String ret = "\\ 0 1 2 3 4 5 6 7 8 9\n";
        for (int i =0; i<10;i++){
            ret+=i+" ";
            for (int j=0;j<10;j++){
                if (field[j][i] instanceof WaterCell){
                    ret+="0 ";
                }
                else if(field[j][i] instanceof ShipCell  & !field[j][i].getIsDestroyed()){
                    ret+="X ";
                }
                else if (field[j][i] instanceof ShipCell ){
                    ret+="D ";
                }
            }
            ret+="\n";
        }
        return ret;

    }*/
    public void makeShot(int x, int y){
        if (inRangeOfTen(x,y)){
            if (!field[x][y].getIsShot()) {
                field[x][y].setIsShot();
            }

            for(int i=0;i<shipsList.size();i++){
                shipsList.get(i).isShot();
            }
        }


    }
    public boolean isTurnChangingShot(int x, int y){
        if (inRangeOfTen(x,y)){
            return !((!field[x][y].getIsShot() & field[x][y] instanceof ShipCell) || field[x][y].getIsShot());
        }
        return false;

    }
    public boolean isAllShipsDestroyed(){
        for (int i =0; i<shipsList.size();i++){
            if (shipsList.get(i).isNotDestroyed()){
                return false;
            }
        }
        return true;
    }

    public boolean incrementCountOf(String shipName){
        switch (shipName){
            case ("Линкор"):
                if (shipsCount.get(shipName)<1){
                    shipsCount.replace(shipName, 1);
                    return true;
                }
                break;
            case ("Крейсер"):
                if (shipsCount.get(shipName)<2){
                    shipsCount.replace(shipName,shipsCount.get(shipName)+1);
                    return true;
                }
                break;
            case ("Эсминец"):
                if (shipsCount.get(shipName)<3){
                    shipsCount.replace(shipName,shipsCount.get(shipName)+1);
                    return true;
                }
                break;
            case ("Субмарина"):
                if (shipsCount.get(shipName)<4){
                    shipsCount.replace(shipName,shipsCount.get(shipName)+1);
                    return true;
                }
                break;
        }
        return false;
    }

    public void decrementCountOf(String shipName){
        if (shipsCount.get(shipName)>0){
            shipsCount.replace(shipName,(shipsCount.get(shipName))-1);
        }

    }

    public boolean isFieldFilled(){
        return shipsList.size()==10;
    }


}
