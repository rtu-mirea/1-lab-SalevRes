package lab3;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab3.users.User;
import lab3.users.UserDatabase;

public class UserWindow {

    public static void newWindow(String title){
        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        BorderPane pane = new BorderPane();
        UserDatabase db = UserDatabase.getInstance();
        Game game = Game.getInstance();
        ToggleGroup selectUserGroup = new ToggleGroup();
        RadioButton user1 = new RadioButton("Пользователь 1");
        RadioButton user2 = new RadioButton("Пользователь 2");
        user1.setToggleGroup(selectUserGroup);
        user1.setSelected(true);
        user2.setToggleGroup(selectUserGroup);
        HBox userPanel = new HBox();
        userPanel.getChildren().addAll(user1, user2);
        pane.setTop(userPanel);


        TextField login = new TextField();
        login.textProperty().addListener(
                (observable,oldValue,newValue)-> {
                    if(newValue.length() > 15) login.setText(oldValue);
                }
        );

        login.setPromptText("Введите логин");
        TextField password = new TextField();
        password.setPromptText("Введите пароль");
        password.textProperty().addListener(
                (observable,oldValue,newValue)-> {
                    if(newValue.length() > 15) password.setText(oldValue);
                }
        );
        FlowPane fieldsPane = new FlowPane();
        fieldsPane.getChildren().addAll(login,  password);
        fieldsPane.setOrientation(Orientation.VERTICAL);
        pane.setCenter(fieldsPane);

        Button signInButton = new Button("Вход");
        signInButton.setOnMouseClicked(event -> {
            if(db.logging(login.getText(), password.getText()).equals(db.getDefaultUser())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Пользователя нет в базе данных");
                alert.showAndWait();
            }
            else{
                RadioButton player = (RadioButton) selectUserGroup.getSelectedToggle();
                switch (player.getText()){
                    case ("Пользователь 1"):
                        game.setFirstUser(db.logging(login.getText(), password.getText()));
                    case ("Пользователь 2"):
                        game.setSecondUser(db.logging(login.getText(), password.getText()));
                }
                window.close();
            }
        });
        signInButton.setCancelButton(true);
        Button signUpButton = new Button("Регистрация");
        signUpButton.setOnMouseClicked(event -> {


            if(db.registration(new User(login.getText(), password.getText()))){
                window.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Данный пользователь уже зарегистрирован");
                alert.showAndWait();
            }
        });
        signUpButton.setCancelButton(true);
        HBox buttonsPanel = new HBox();
        buttonsPanel.getChildren().addAll(signInButton,signUpButton);
        pane.setBottom(buttonsPanel);

        Scene scene = new Scene(pane, 250,130);
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle(title);
        window.show();

    }


}