import fields.BattleField;
import fields.FieldGenerator;
import fields.Game;
import ships.Ship;

import java.awt.*;
import java.awt.event.*;
import javax.jws.soap.SOAPBinding;
import javax.swing.*;

public class MainWindow extends JFrame {
    DrawField drawField1;
    DrawField drawField2;
    Game game = Game.getInstance();
    JRadioButton battleshipButton;
    JRadioButton cruiserButton;
    JRadioButton destroyerButton;
    JRadioButton submarineButton;
    JRadioButton vertical;
    JRadioButton horizontal;
    JLabel label;
    JPanel radioPanel;
    public MainWindow() {
        super("Main Window");
        this.setBounds(100, 100, 650, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BorderLayout());
        radioPanel = new JPanel();
        battleshipButton   = new JRadioButton("Линкор(4)", true);
        cruiserButton    = new JRadioButton("Крейсер(3)");
        destroyerButton = new JRadioButton("Эсминец(2)");
        submarineButton = new JRadioButton("Субмарина(1)");
        horizontal=new JRadioButton("Горизонтально", true);;
        vertical=new JRadioButton("Вертикально");
        ButtonGroup shipTypeGroup = new ButtonGroup();
        shipTypeGroup.add(battleshipButton);
        shipTypeGroup.add(cruiserButton);
        shipTypeGroup.add(destroyerButton);
        shipTypeGroup.add(submarineButton);
        radioPanel.add(battleshipButton);
        radioPanel.add(cruiserButton);
        radioPanel.add(destroyerButton);
        radioPanel.add(submarineButton);
        ButtonGroup orientationGroup = new ButtonGroup();
        orientationGroup.add(horizontal);
        orientationGroup.add(vertical);
        radioPanel.add(horizontal);
        radioPanel.add(vertical);


        drawField1 = new DrawField(true);
        drawField2 = new DrawField(false);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        label = new JLabel();
        JButton button = new JButton("Рандомная генерация");
        button.addActionListener(new ButtonEventListener());

        JButton button2 = new JButton("Перезапустить игру");
        button2.addActionListener(new Button2EventListener());

        JButton button3 = new JButton("Вход");
        button3.addActionListener(new Button3EventListener());

        JPanel fieldContainer = new JPanel();
        fieldContainer.setLayout( new BoxLayout(fieldContainer, BoxLayout.X_AXIS) );
        fieldContainer.add(drawField1);
        fieldContainer.add(drawField2);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout( new BoxLayout(buttonContainer, BoxLayout.X_AXIS) );

        buttonContainer.add(button);
        buttonContainer.add(button2);
        buttonContainer.add(button3);
        buttonContainer.add(label);
        label.setText(game.getCurrentCondition());
        drawField1.changeField(game.getAllyField());
        drawField2.changeField(game.getEnemyField());
        this.getContentPane().add(fieldContainer, BorderLayout.CENTER);
        this.getContentPane().add(radioPanel, BorderLayout.SOUTH);
        this.getContentPane().add(buttonContainer, BorderLayout.NORTH);
        setVisible(true);
    }
    void showShipParameters(boolean flag){
        radioPanel.setVisible(flag);
    }
    void setFieldsEnabled(boolean value){
        drawField1.setEnabled(value);
        drawField2.setEnabled(value);
    }

    void swapFields(){
        game.switchTurn();
        drawField1.changeField(game.getAllyField());
        drawField2.changeField(game.getEnemyField());
    }
    int getShipLength(){
        if (battleshipButton.isSelected()){
            return 4;
        }
        else if (cruiserButton.isSelected()){
            return 3;
        }
        else if (destroyerButton.isSelected()){
            return 2;
        }
        else {
            return 1;
        }
    }


    class ButtonEventListener implements ActionListener {


        public void actionPerformed (ActionEvent e) {
            setFieldsEnabled(true);
            game.randomGeneration();
            drawField1.changeField(game.getAllyField());
            drawField2.changeField(game.getEnemyField());
            label.setText(game.getCurrentCondition());
            showShipParameters(false);
        }
    }
    class Button2EventListener implements ActionListener {


        public void actionPerformed (ActionEvent e) {
            setFieldsEnabled(true);
            game.startPlacing();
            drawField1.changeField(game.getAllyField());
            drawField2.changeField(game.getEnemyField());
            label.setText(game.getCurrentCondition());
            showShipParameters(true);
        }
    }
    class Button3EventListener implements ActionListener {


        public void actionPerformed (ActionEvent e) {
            new UserWindow();
        }
    }


    class DrawField extends JPanel {
        private static final int PREF_W = 25;
        private static final int PREF_H = 25;
        private int[] lastShot = {-1 ,-1};

        private BattleField field = new BattleField();
        private boolean ally;
        public DrawField(boolean ally){

            this.ally = ally;
            this.setBounds(0, 0, 250, 250);
            setPreferredSize(new Dimension(250, 250));
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    if (game.placingStage){
                        if (ally & me.getButton() == MouseEvent.BUTTON3){
                            game.getAllyField().removeShip(me.getX()/PREF_W, me.getY()/PREF_H);
                            repaint();
                        }
                        if (ally & me.getButton() == MouseEvent.BUTTON1){
                            Ship ship = new Ship(getShipLength());
                            if (vertical.isSelected()){
                                ship.setVertical();
                            }
                            game.getAllyField().placeShip(ship, me.getX()/PREF_W, me.getY()/PREF_H);
                            if (!game.checkIsFieldsFilled()){
                                if (game.getAllyField().isFieldFilled()){
                                    swapFields();
                                }
                            }
                            else{
                                swapFields();
                                showShipParameters(false);
                            }
                        }
                    }
                    else if(game.gameIsFinished()){
                        setFieldsEnabled(false);

                    }
                    else{
                        if (!ally&me.getButton() == MouseEvent.BUTTON1){
                            lastShot[0] = me.getX()/PREF_W;
                            lastShot[1] = me.getY()/PREF_H;
                            if (field.isTurnChangingShot(lastShot[0], lastShot[1])){
                                field.makeShot(lastShot[0], lastShot[1]);
                                swapFields();
                            }
                            else{
                                field.makeShot(lastShot[0], lastShot[1]);
                            }
                        }
                    }
                    repaint();
                    label.setText(game.getCurrentCondition());
                }
            });

        }

        public void changeField(BattleField field) {
            this.field = field;

            repaint();
        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(PREF_W, PREF_H);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int i = 0; i < field.getField().length; i++) {
                for (int j = 0; j <field.getField()[i].length ; j++) {
                    g2.setColor(field.getField()[i][j].getColor(ally));
                    g2.fillRect(PREF_H*i, PREF_W*j, PREF_H, PREF_W);
                }
            }
            g2.setColor(Color.BLACK);
            for (int i = 0; i <= 250; i += 25) {
                g.drawLine(i, 0, i, 250);
            }

            for (int i = 0; i <= 250; i += 25) {
                g.drawLine(0, i, 250, i);
            }
        }



    }



}