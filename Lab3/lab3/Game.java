package lab3;


import lab3.users.User;

public class Game {
    private User user1 = new User("Player 1", "None");
    private User user2 = new User("Player 2", "None");
    private static Game instance;
    private BattleField field1 = new BattleField();
    private BattleField field2 = new BattleField();

    private Game(){

    }
    public  static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void startGame(){
        Main.getFieldsContainer().getChildren().clear();
        Main.getFieldsContainer().getChildren().add(Drawing.buildGrid("ally", field1));
        Main.getFieldsContainer().getChildren().add(Drawing.buildGrid("enemy", field2));
    }

    public void shipsPlacing(boolean random){
        if(random){
            FieldGenerator generator = new FieldGenerator();
            field1 = generator.randomGeneration();
            field2 = generator.randomGeneration();
            Main.redraw( field1, "ally");
            Main.redraw( field2, "enemy");
        }

    }
    public void switchTurn(){
        BattleField clone = field1;
        field1 = field2;
        field2 = clone;
        User userClone = user1;
        user1 = user2;
        user2=userClone;
        Main.redraw( field1, "ally");
        Main.redraw( field2, "enemy");
    }

    public boolean bothFieldsFilled(){
        return field1.isFieldFilled() & field2.isFieldFilled();
    }

    public void clearFields(){
        field1 = new BattleField();
        field2 = new BattleField();
    }
    public void setFirstUser(User user){
        if (user.equals(user2)){
            User clone = new User("","");
            clone.setLogin(user2.getLogin()+"(2)");
            user1 = clone;
            return;
        }
        user1 = user;
    }
    public void setSecondUser(User user){
        if (user.equals(user1)){
            User clone = new User("","");
            clone.setLogin(user1.getLogin()+"(2)");
            user2 = clone;
            return;
        }
        user2 = user;
    }
    public boolean isEnemyShipsDestroyed(){
        return field2.isAllShipsDestroyed();
    }

    public User getCurrentPlayer(){
        return user1;
    }
    public User getNextPlayer(){
        return user2;
    }
    public BattleField getAllyField(){
        return field1;
    }
    public BattleField getEnemyField(){
        return field2;
    }

}
