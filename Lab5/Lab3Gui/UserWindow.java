import fields.Game;
import users.User;
import users.UserDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserWindow extends JFrame {
    JRadioButton user1;
    JRadioButton user2;
    JTextField username;
    JTextField password;
    JButton login;
    JButton register;
    UserDatabase database = UserDatabase.getInstance();
    public UserWindow(){

        super("Login Window");
        this.setBounds(100, 100, 300, 140);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(new BorderLayout());
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
        ButtonGroup group = new ButtonGroup();
        user1 = new JRadioButton("Игрок 1", true);
        user2 = new JRadioButton("Игрок 2");
        radioPanel.add(user1);
        radioPanel.add(user2);
        group.add(user1);
        group.add(user2);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        username = new JTextField();
        password = new JTextField();
        textPanel.add(username);
        textPanel.add(password);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        login = new JButton("Войти");
        login.addActionListener(new ButtonEventListener(this));
        register = new JButton("Зарегистрироваться");
        register.addActionListener(new Button2EventListener(this));

        buttonPanel.add(login);
        buttonPanel.add(register);

        this.getContentPane().add(radioPanel, BorderLayout.NORTH);
        this.getContentPane().add(textPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    class ButtonEventListener implements ActionListener {
        UserWindow window;
        public ButtonEventListener(UserWindow window){
            this.window = window;

        }
        public void actionPerformed (ActionEvent e) {
            if (user1.isSelected()){
                Game.getInstance().user1 = database.logging(username.getText(), password.getText());
            }
            else{
                Game.getInstance().user2 = database.logging(username.getText(), password.getText());
            }
            window.dispose();


        }
    }
    class Button2EventListener implements ActionListener {
        UserWindow window;
        public Button2EventListener(UserWindow window){
            this.window = window;

        }

        public void actionPerformed (ActionEvent e) {
            database.registration(new User(username.getText(), password.getText()));
            window.dispose();
        }
    }
}