package ex4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClassTextFile {
    String path;
    public ClassTextFile(String path){
        if (new File(path).exists()){
            this.path = path;
        }
    }
    public MyStringEditer read(){
        File file = new File(path);
        Scanner sc;
        String line="";
        try {

            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                 line += sc.nextLine().trim();
            }

            return new MyStringEditer(line);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }
}
