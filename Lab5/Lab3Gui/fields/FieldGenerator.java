package fields;
import ships.Ship;

import java.util.Random;

public class FieldGenerator {
    public FieldGenerator(){
    }

    public static BattleField randomGeneration(){
        Ship[] pull = new Ship[] {new Ship(1), new Ship(1),new Ship(1),new Ship(1),
                new Ship(2),new Ship(2),new Ship(2),
                new Ship(3),new Ship(3),
                new Ship(4)};
        BattleField field = new BattleField();
        for (int i = 9; i>=0; i--){
            pull[i].setRandomOrientation();
            while (!pull[i].getIsPlaced()){
                field.placeShip(pull[i], new Random().nextInt(10),new Random().nextInt(10));
            }

        }
        return field;
    }

    public static BattleField manualPlacement(BattleField field, String shipType, int x, int y, boolean isVertical){
        Ship ship = new Ship(1);
        switch (shipType){
            case ("Линкор"):
                ship = new Ship(4);
                break;
            case ("Крейсер"):
                ship = new Ship(3);
                break;
            case ("Эсминец"):
                ship = new Ship(2);
                break;
            case ("Субмарина"):
                ship = new Ship(1);
        }
        if (isVertical){
            ship.setVertical();
        }
        field.placeShip(ship, x, y);
        return field;

    }
}
