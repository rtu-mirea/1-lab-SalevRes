import ex1.Exercise1;
import ex2.MemberCard;
import ex2.Record;
import ex3.Exercise3;
import ex4.ClassSerializableFile;
import ex4.MyStringEditerControl;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        test1();
        test2();
        test3();
        test4();
    }
    public static void test1(){
        Exercise1 ex = new Exercise1();
        ex.createByPath("File1.txt");
        System.out.println(Exercise1.fileExist("File1.txt"));
        ex.createByPath("C://File2.txt");
        System.out.println(Exercise1.fileExist("C://File2.txt"));
        ex.createByPath("C://randompath//File3.txt");
        System.out.println(Exercise1.fileExist("C://randompath//File3.txt"));
        ex.createByPath("folder1//folder2//folder3");
        System.out.println(Exercise1.fileExist("//folder1//folder2//folder3"));

        Exercise1.isFile(new File("File1.txt"), true);
        Exercise1.isDir(new File("//folder1//folder2//folder3"), true);
        //ex.folders.get(0).getAbsolutePath();
        Exercise1.SizeInKb(new File("C:\\Users\\Salev\\Documents\\GitHub\\1-lab-SalevRes\\"));

        ex.createByPath(System.getProperty("user.dir")+"//folder5");
        Exercise1.fileListingOne("//folder5");
        Exercise1.fileListingTwo("//folder5");
        //ex.deleteAllFolders();

    }
    public static void test2(){
        MemberCard card = new MemberCard();
        card.addRecord(new Record(32, "fadfasdf", "Fadfsfd",
                MemberCard.getDateFromString("11.11.2017"), MemberCard.getDateFromString("09.12.2017")));
        card.addRecord(new Record(39, "ufg2erasdf", "abgdfsfg2ed",
                MemberCard.getDateFromString("11.10.2017"), MemberCard.getDateFromString("09.12.2017")));
        card.addRecord(new Record(39, "ufg2erasdf", "abgdfsfg2ed",
                MemberCard.getDateFromString("11.03.2018"), MemberCard.getDateFromString("17.03.2018")));

        card.writeRecordsToFile("CardFile.myfile");
        card.clearArray();
        card.readRecordFromFile("CardFile.myfile");
        card.printArray();

        card.whiteToRandomAccessFile("RAFfile.myfile");
        card.rewriteRandomAccessFile("RAFfile.myfile", 1);

    }
    public static void test3(){
        Exercise3.copy("A.txt", "B.txt");
        Exercise3.buffCopy("A.txt", "B.txt");
        Exercise3.readUTFFile("src//A2.txt");



    }
    public static void test4(){
        MyStringEditerControl control1 = new MyStringEditerControl("fsdafdsafdsfasdf3124d21\nerqwfqwg");
        MyStringEditerControl control2 = new MyStringEditerControl("vy3y4tutrjhtrwcrv");
        ClassSerializableFile serClass = new ClassSerializableFile("file1.dfqw");
        serClass.serialization(control1);
        MyStringEditerControl control3 = serClass.deserialization();
        System.out.println(control3.getEditer().getText());

        serClass.controls.add(control1);
        serClass.controls.add(control2);
        serClass.arraySerialization();
        serClass.controls.clear();
        serClass.arrayDeserialization();
        for (int i = 0; i < serClass.controls.size(); i++) {
            System.out.println(serClass.controls.get(i).getEditer().getText());

        }

    }
}
