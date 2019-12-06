package com.company;

import com.company.Records.MemberCard;
import com.company.Records.Record;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AddRecordWindow extends JFrame {
    private MainWindow mainWindow;
    private JTextField input1 = new JTextField("", 5);
    private JLabel label1 = new JLabel("Номер");

    private JTextField input2 = new JTextField("", 5);
    private JLabel label2 = new JLabel("Автор");

    private JTextField input3 = new JTextField("", 5);
    private JLabel label3 = new JLabel("Название");

    private JTextField input4 = new JTextField("", 5);
    private JLabel label4 = new JLabel("Дата выдачи");

    private JTextField input5 = new JTextField("", 5);
    private JLabel label5 = new JLabel("Дата приема");
    private JLabel label6 = new JLabel("<html>Даты записываются<br> в формате <br>dd.mm.yyyy");


    private JButton button = new JButton("OK");

    public AddRecordWindow(MainWindow window) {

        super("Новая запись");
        this.setBounds(100, 100, 300, 350);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        mainWindow = window;
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(6, 2, 2, 2));
        container.add(label1);
        container.add(input1);
        container.add(label2);
        container.add(input2);
        container.add(label3);
        container.add(input3);
        container.add(label4);
        container.add(input4);
        container.add(label5);
        container.add(input5);
        container.add(label6);
        container.add(button);
        button.addActionListener(new AddRecordWindow.ButtonEventListener(this));
        container.add(button);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                window.setEnabled(true);
                e.getWindow().dispose();
            }
        });
    }

    class ButtonEventListener implements ActionListener {
        AddRecordWindow window;
        public ButtonEventListener(AddRecordWindow window){
            this.window = window;
        }
        public void actionPerformed (ActionEvent e) {
            MemberCard card = MemberCard.getMemberCard();
            if (Record.isDate(input4.getText()) & Record.isDate(input5.getText()) & input1.getText().matches("\\d+")){
                Record record = new Record(Integer.parseInt(input1.getText()), input2.getText(),input3.getText(),input4.getText(),input5.getText());
                card.addRecord(record);
            }
            mainWindow.fillScrollPane();
            mainWindow.setEnabled(true);
            window.dispose();
        }
    }


}