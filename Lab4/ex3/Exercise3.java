package ex3;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Exercise3 {
    public static void copy(String path1, String path2){
        String data = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path1);
            int byteReader;
            while ((byteReader=fileInputStream.read())!=-1){
                data+=(char)byteReader;}
            FileOutputStream file= new FileOutputStream(path2);
            file.write(data.getBytes());
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public static void buffCopy(String path1, String path2){
        try{
            BufferedReader inb = new BufferedReader(new FileReader(path1), 128);
            BufferedWriter outb = new BufferedWriter(new FileWriter(path2), 128);
            char [] buf = new char[128];
            while (inb.read(buf) != -1){
                outb.write(buf);

            }
            inb.close();
            outb.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }
        
        
    }
    public static void readUTFFile(String path){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"));
            String line;
            System.out.println(Charset.defaultCharset().name());
            while ((line = in.readLine()) != null){
                System.out.println(line);

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
