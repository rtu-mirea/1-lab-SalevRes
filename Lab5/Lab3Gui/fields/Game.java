package fields;

import users.User;

public class Game {
    public User user1 = new User("Player 1", "None");
    public User user2 = new User("Player 2", "None");
    private static Game instance;
    private BattleField field1 = new BattleField();
    private BattleField field2 = new BattleField();
    public boolean placingStage = true;
    private Game(){
    }
    public  static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    public void switchTurn(){
        User user = user1;
        user1= user2;
        user2 = user;

        BattleField swap = field1;
        field1 = field2;
        field2 = swap;
    }

    public void startPlacing(){
        placingStage = true;
        field1 = new BattleField();
        field2 = new BattleField();
    }
    public void randomGeneration(){
        placingStage = false;
        field1 = FieldGenerator.randomGeneration();
        field2 = FieldGenerator.randomGeneration();
    }
    public boolean checkIsFieldsFilled(){
        if (field1.isFieldFilled() & field2.isFieldFilled()){
            placingStage = false;
            return true;
        }
        return false;
    }
    public String getCurrentCondition(){
        if (placingStage){
            return "Расстановка игрока " + user1.getLogin();
        }
        else {
            if (field1.isAllShipsDestroyed()){
                return "Победил игрок "+user2.getLogin();
            }
            else if (field2.isAllShipsDestroyed()){
                return "Победил игрок "+user1.getLogin();
            }
            return "Ход игрока "+user1.getLogin();
        }
    }
    public BattleField getAllyField(){
        return field1;
    }
    public BattleField getEnemyField(){
        return field2;
    }
    public boolean gameIsFinished(){
        if (field1.isAllShipsDestroyed() | field2.isAllShipsDestroyed()){
            return true;
        }
        return false;
    }



}
