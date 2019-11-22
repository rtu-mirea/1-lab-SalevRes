package ex1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Exercise1 {
    ArrayList<File> folders = new ArrayList<>();
    public void createByPath(String path){

        File file = new File(path);
        int indexOFLastBackspace = file.getAbsolutePath().lastIndexOf("\\");

        if (file.getAbsolutePath().substring(indexOFLastBackspace).contains(".")){
            createDir(file.getAbsolutePath().substring(0, indexOFLastBackspace+1));
        }
        else{
            createDir(file.getAbsolutePath());
            return;
        }
        try{
            file.createNewFile();
            if (file.exists()){
                System.out.println(file.getAbsolutePath()+" создан");
            }
            else{
                System.out.println(file.getAbsolutePath()+" не создан");
            }
            folders.add(file);
        }
        catch (Exception e){
            System.out.println(file.getAbsolutePath());
            e.printStackTrace();
        }
    }


    public void createDir(String absPath){
        absPath+="\\";
        String search = "";
        int index = 0;
        do {
            search+=absPath.substring(index, index+absPath.substring(index).indexOf("\\")+1);
            index += absPath.substring(index).indexOf("\\")+1;
            File subFile = new File(search);
            if (!subFile.exists()){
                subFile.mkdir();
                folders.add(subFile);
            }

        } while (!search.equals(absPath));
        System.out.println("Путь "+absPath+" создан");
    }

    
    //второе упражнение
    public static boolean isFile(File file, boolean soutInfo){
        String path = file.getAbsolutePath();
        if (file.isFile()){
            if (soutInfo){
                System.out.println("Родительская папка: "+path.substring(0, path.lastIndexOf("\\")+1));
                System.out.println("Название файла: "+path.substring(path.lastIndexOf("\\")+1));
            }

            return true;
        }
        return false;

    }
    public static boolean isDir(File file, boolean soutInfo){
        String path = file.getAbsolutePath();
        if (file.isDirectory()){
            if (soutInfo){
                System.out.println("Название папки: "+path.substring(path.lastIndexOf("\\")+1));
            }

            return true;
        }
        return false;

    }
    public static long SizeInKbCounter(File file){
        long summ = 0;
        if (file.exists()){
            if (file.isFile()) {
                return (file.length() / 1024);
            } else {
                for (int i = 0; i < file.listFiles().length; i++) {
                    summ += Exercise1.SizeInKbCounter(file.listFiles()[i]);
                }
                return summ;
            }
        }
        else {
            return 0;
        }

    }
    public static void SizeInKb(File file){
        if (file.isFile()){
            System.out.println("Файл '"+file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1)+"' имеет размер: "+Exercise1.SizeInKbCounter(file)+" Кбайт.");
        }
        else{
            System.out.println("Содержимое папки '"+file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1)+"' имеет размер: "+Exercise1.SizeInKbCounter(file)+" Кбайт.");
        }

    }
    public static boolean fileExist(String fileName){
        return new File(fileName).exists();
    }
    public static String fullPath(File file){return file.getAbsolutePath();}

    //третье упражнение
    public static void fileListingOne(String path){
        File dir = new File(path);
        if (dir.exists()){
            for (int i =0;i<dir.list().length;i++){
                System.out.println(dir.list()[i]);
            }
        }

    }
    public static void fileListingTwo(String path){
        File dir = new File(path);
        if (dir.exists()){
            for (int i =0;i<dir.listFiles().length;i++){
                String absPath = dir.listFiles()[i].getAbsolutePath();
                System.out.println(absPath.substring(absPath.lastIndexOf("\\")));
            }
            System.out.println("Количество элементов в папке: " + dir.listFiles().length );
        }

    }
    public int dirsCount(String path){
        File[] files = new File(path).listFiles();
        int count = 0;
        for (int i = 0; i <files.length ; i++) {
            if (files[i].isDirectory()){
                count++;
            }
        }
        return count;
    }

    public void deleteAllFolders(){
        for (int i=0;i<folders.size();i++){
            File file = folders.get(i);
            if (file.exists()){
                if (isDir(file, false)) {
                    deleteAllFolders(new ArrayList<>(Arrays.asList(file.listFiles())));
                }

                file.delete();
            }


        }

    }
    private void deleteAllFolders(ArrayList<File> paths){
        for (int i=0;i<paths.size();i++){
            File file = paths.get(i);
            if (file.exists()){
                if (isDir(file, false)) {
                    deleteAllFolders(new ArrayList<>(Arrays.asList(file.listFiles())));
                    }

                file.delete();
            }


        }

    }
    public void clearAll(){
        this.deleteAllFolders(this.folders);
        this.folders.clear();
    }
}
