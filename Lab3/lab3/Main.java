package lab3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static HBox fieldBox;
    private static ToggleGroup shipTypesGroup;
    private static ToggleGroup orientationGroup;
    private static Label infoLabel;


    @Override
    public void start(Stage primaryStage) throws Exception{
        infoLabel = new Label("");
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        HBox shipPanel = new HBox();
        HBox choicePanel = new HBox();
        HBox bottomPanel = new HBox();
        control.setPrefHeight(40F);
        control.setSpacing(10F);
        control.setAlignment(Pos.BASELINE_CENTER);
        VBox center = new VBox();


        orientationGroup = new ToggleGroup();
        RadioButton horizontalButton = new RadioButton("Горизонтально");
        RadioButton verticalButton = new RadioButton("Вертикально");
        horizontalButton.setToggleGroup(orientationGroup);
        horizontalButton.setSelected(true);
        verticalButton.setToggleGroup(orientationGroup);

        shipTypesGroup = new ToggleGroup();
        RadioButton button1 = new RadioButton("Линкор");
        button1.setToggleGroup(shipTypesGroup);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("Крейсер");
        button2.setToggleGroup(shipTypesGroup);
        RadioButton button3 = new RadioButton("Эсминец");
        button3.setToggleGroup(shipTypesGroup);
        RadioButton button4 = new RadioButton("Субмарина");
        button4.setToggleGroup(shipTypesGroup);
        choicePanel.getChildren().addAll(horizontalButton, verticalButton);
        shipPanel.getChildren().addAll( button1, button2, button3, button4);
        bottomPanel.getChildren().addAll(choicePanel, shipPanel);

        fieldBox = new HBox();
        fieldBox.setPadding(new Insets(15, 12, 15, 12));
        fieldBox.setSpacing(10.0);
        fieldBox.setAlignment(Pos.BASELINE_CENTER);
        center.getChildren().addAll(fieldBox, infoLabel);
        center.setAlignment(Pos.CENTER);
        border.setCenter(center);
        border.setTop(control);

        choicePanel.setAlignment(Pos.BASELINE_LEFT);
        shipPanel.setAlignment(Pos.BASELINE_RIGHT);
        bottomPanel.setAlignment(Pos.BOTTOM_CENTER);
        border.setBottom(bottomPanel);

        Game game = Game.getInstance();
        Button randomPlacementButton = new Button("Случайная расстановка");
        randomPlacementButton.setOnMouseClicked(event -> {
            game.clearFields();
            game.startGame();
            game.shipsPlacing(true);
        });
        randomPlacementButton.setCancelButton(true);

        Button manualPlacementButton = new Button("Ручная расстановка");
        manualPlacementButton.setOnMouseClicked(event -> {
            game.clearFields();
            game.startGame();
            game.shipsPlacing(false);
        });
        manualPlacementButton.setCancelButton(true);

        Button openLoginWindow = new Button("Вход");
        openLoginWindow.setOnMouseClicked(event -> {
            UserWindow.newWindow("Вход/Регистрация");
        });
        openLoginWindow.setCancelButton(true);

        control.getChildren().addAll(randomPlacementButton, manualPlacementButton, openLoginWindow);
        primaryStage.setScene(new Scene(border, 900, 400));
        primaryStage.setTitle("Battle Ship");
        primaryStage.setResizable(false);
        primaryStage.show();





    }

    public static void redraw(BattleField field, String fieldTag){
        if (fieldTag == "ally"){
            fieldBox.getChildren().remove(0);
            fieldBox.getChildren().add(0, Drawing.buildGrid("ally",  field));
        }
        else{
            fieldBox.getChildren().remove(1);
            fieldBox.getChildren().add(Drawing.buildGrid("enemy",   field));
        }

    }

    public static String getCurrentToggle(){
        RadioButton button =(RadioButton) shipTypesGroup.getSelectedToggle();
        return button.getText();

    }

    public static HBox getFieldsContainer(){
        return fieldBox;
    }

    public static boolean verticalOrientation(){
        RadioButton button =(RadioButton) orientationGroup.getSelectedToggle();
        return button.getText().equals("Вертикально");
    }

    public static void setInfoLabel(String text){
        infoLabel.setText(text);
    }






}
