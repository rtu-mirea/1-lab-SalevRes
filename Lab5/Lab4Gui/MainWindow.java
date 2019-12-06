package com.company;

import com.company.Records.MemberCard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    private MemberCard card = MemberCard.getMemberCard();
    private JButton button1 = new JButton("Добавить");
    private JButton button2 = new JButton("Удалить");
    private JButton button3 = new JButton("Сохранить");
    private JButton button4 = new JButton("Открыть");
    JList<String> list;
    JPanel listContainer;
    public MainWindow() {
        super("Main Window");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        listContainer = new JPanel();
        listContainer.setLayout( new BoxLayout(listContainer, BoxLayout.X_AXIS) );
        listContainer.add(new JScrollPane(list));

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout( new BoxLayout(buttonContainer, BoxLayout.X_AXIS) );
        button1.addActionListener(new Button1EventListener(this));
        buttonContainer.add(button1);
        button2.addActionListener(new Button2EventListener(this));
        buttonContainer.add(button2);
        button3.addActionListener(new Button3EventListener(this));
        buttonContainer.add(button3);
        button4.addActionListener(new Button4EventListener(this));
        buttonContainer.add(button4);

        container.add(listContainer, BorderLayout.NORTH);
        container.add(buttonContainer, BorderLayout.SOUTH);

    }

    public void fillScrollPane(){


        listContainer.remove(0);
        list = new JList<String>(card.getList());
        listContainer.add(list, 0);
        listContainer.revalidate();
        listContainer.repaint();

    }

    class Button1EventListener implements ActionListener {
        MainWindow mainWindow;
        public Button1EventListener(MainWindow window){
            this.mainWindow = window;
        }
        public void actionPerformed (ActionEvent e) {
            AddRecordWindow window  = new AddRecordWindow(mainWindow);
            window.setVisible(true);
            mainWindow.setEnabled(false);
        }
    }

    class Button2EventListener implements ActionListener {
        MainWindow mainWindow;
        public Button2EventListener(MainWindow window){
            this.mainWindow = window;
        }
        public void actionPerformed (ActionEvent e) {
            int index = this.mainWindow.list.getSelectedIndex();
            if (index>=0){
                card.delete(index);
            }
            mainWindow.fillScrollPane();
        }
    }

    class Button3EventListener implements ActionListener {
        MainWindow mainWindow;
        public Button3EventListener(MainWindow window){
            this.mainWindow = window;
        }
        public void actionPerformed (ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Сохранить файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                card.writeRecordsToFile(fileopen.getSelectedFile().getAbsolutePath());
                mainWindow.fillScrollPane();
            }
        }
    }
    class Button4EventListener implements ActionListener {
        MainWindow mainWindow;
        public Button4EventListener(MainWindow window){
            this.mainWindow = window;
        }
        public void actionPerformed (ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                card.readRecordFromFile(fileopen.getSelectedFile().getAbsolutePath());
                mainWindow.fillScrollPane();
            }

        }
    }

}