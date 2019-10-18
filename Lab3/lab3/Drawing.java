package lab3;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lab3.cells.Cell;
import lab3.cells.WaterCell;

import java.util.Map;

public class Drawing {

    private static int[] lastShot = {-1 ,-1};
    private static int fieldSize = 10;
    private static int cellSize = 25;

    public static Rectangle buildRectangle(int x, int y,  Cell cell, String ally) {
        Rectangle rect = new Rectangle();
        rect.setX(x * cellSize);
        rect.setY(y * cellSize);
        rect.setHeight(cellSize);
        rect.setWidth(cellSize);
        rect.setFill(Color.rgb(0, 128, 255));
        rect.setStroke(Color.BLACK);
        if (cell instanceof WaterCell ){
            if(cell.getIsShot()){
            rect.setFill(Color.rgb(0,0,120));}
            /*else if(!cell.getShipCanBePlaced() & ally.equals("ally")){
                rect.setFill(Color.rgb(67, 111, 148));
            }*/
        }
        else {
            if(cell.getIsDestroyed()){
                rect.setFill(Color.rgb(90, 104, 117));
            }
            else if(cell.getIsShot()){
                rect.setFill(Color.rgb(255, 0, 0));
            }
            else if (ally.equals("ally")){
                rect.setFill(Color.rgb(0, 0, 0));
            }

        }
        rect.setOnMouseClicked(
                event -> {
                    lastShot[0] = (int)rect.getX()/cellSize;
                    lastShot[1] = (int)rect.getY()/cellSize;
                }
        );


        return rect;
    }

    public static Group buildGrid(String fieldTag,  BattleField field) {
        Game game = Game.getInstance();
        showCurrentTurn();
        Group panel = new Group();
        panel.setId(fieldTag);
        for (int y = 0; y != fieldSize; y++) {
            for (int x = 0; x != fieldSize; x++) {
                panel.getChildren().add(Drawing.buildRectangle(x, y, field.getCell(x , y), fieldTag));
            }
        }
        panel.setOnMouseClicked(event -> {
            if(game.bothFieldsFilled() & fieldTag.equals("enemy")){

                playingStage(field, fieldTag);


            }
            if (!game.bothFieldsFilled()) {

                placingStage(field, fieldTag);

            }

        });
        return panel;
    }

    private static void playingStage(BattleField field, String fieldTag){
        Game game = Game.getInstance();

        if (field.isTurnChangingShot(lastShot[0], lastShot[1])){
            field.makeShot(lastShot[0], lastShot[1]);
            try
            {
                Thread.sleep(0);
                game.switchTurn();
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        else{
            field.makeShot(lastShot[0], lastShot[1]);
            Main.redraw( field, fieldTag);
        }

        if (game.isEnemyShipsDestroyed()){
            Main.setInfoLabel("Победа игрока "+game.getCurrentPlayer().getLogin());
            game.clearFields();
        }

    }

    private static void placingStage(BattleField field, String fieldTag){
        Game game = Game.getInstance();

        if (fieldTag.equals("ally")){
            field = FieldGenerator.manualPlacement(field, Main.getCurrentToggle() ,lastShot[0], lastShot[1]);
            if(field.isFieldFilled()){
                game.switchTurn();
                game.startGame();
                return;
            }
            Main.redraw(field, fieldTag);

        }

    }

    private static void showCurrentTurn(){
        Game game = Game.getInstance();
        if (game.bothFieldsFilled()){
            Main.setInfoLabel("Ход игрока "+game.getCurrentPlayer().getLogin());
        }
        else{
            Main.setInfoLabel("Расстановка игрока "+game.getCurrentPlayer().getLogin());
        }
    }


}
