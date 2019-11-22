package ex4;

import ex2.Record;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClassSerializableFile {
    String path;
    public ArrayList<MyStringEditerControl> controls = new ArrayList<>();
    public ClassSerializableFile(String path){
        this.path = path;
    }

    public void serialization(MyStringEditerControl control){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
        {

            oos.writeObject(control);
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
    public MyStringEditerControl deserialization (){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path)))
        {
            return (MyStringEditerControl)ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void arraySerialization(){
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(controls);
            oos.close();
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void arrayDeserialization(){
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            controls = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
