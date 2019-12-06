package fields;

import cells.*;
import ships.Ship;

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
    private boolean shipNearCell(int x, int y){
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (inRangeOfTen(i, j)){
                    if (field[i][j] instanceof ShipCell){
                        return true;
                    }
                }

            }
        }
        return false;

    }
    private void unsetCellsClosedAroundShip(int xStart, int xFinish, int yStart, int yFinish){
        for (int i = xStart-1; i<=xFinish+1;i++){
            for (int j = yStart-1; j<=yFinish+1;j++){
                if(inRangeOfTen(i, j)){
                    if (!shipNearCell(i,j)){
                        field[i][j].unlockedToPlaceShip();
                    }

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
    public void removeShip(int x, int y){
        Cell cell = getCell(x, y);
        for (int i = 0; i < shipsList.size(); i++) {
            for (int j = 0; j <shipsList.get(i).getShipBody().length ; j++) {
                if (cell == shipsList.get(i).getShipBody()[j]){
                    Ship ship = shipsList.get(i);
                    decrementCountOf(ship.getShipName());
                    int[] head = getShipHeadIndex(ship);
                    removeShipCells(ship.getShipBody());
                    if (ship.getIsHorizontal()){
                        unsetCellsClosedAroundShip(head[0], head[0]+ship.getSize()-1, head[1], head[1]);
                    }
                    else{
                        unsetCellsClosedAroundShip(head[0], head[0], head[1], head[1]+ship.getSize()-1);
                    }


                    shipsList.remove(ship);
                    return;

                }

            }

        }

    }
    private int[] getShipHeadIndex(Ship ship){
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (ship.getShipBody()[0] == field[j][k]){
                    return new int[] {j,k};
                }
            }
        }

        return null;
    }
    private void removeShipCells(Cell[] cells){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (cells[i] == field[j][k]){
                        field[j][k]=new WaterCell();
                    }
                }
            }

        }
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
