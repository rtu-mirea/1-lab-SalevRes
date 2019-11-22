package ex4;

import java.io.Serializable;
import java.util.Scanner;

public class MyStringEditerControl implements Serializable {
    private MyStringEditer editer;
    String text;
    public MyStringEditerControl(String text){
        editer = new MyStringEditer(text);
        this.text = text;
    }

    public void setTextFromConsole(){
        Scanner scanner = new Scanner(System.in);
        editer = new MyStringEditer(scanner.next());
    }
    public MyStringEditer getEditer(){
        return editer;
    }
}
