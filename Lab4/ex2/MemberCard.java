package ex2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MemberCard {
    ArrayList<Record> records = new ArrayList<>();
    public MemberCard(){

    }
    /*public void addRecord(){
        Integer number;
        String author;
        String title;
        Date date1 = null;
        Date date2 = null;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("Введите инвентарный номер:");
            try{
                number=scan.nextInt();
            }
            catch (Exception e){
                number = null;
            }
            scan.nextLine();
        }while (number==null);


        System.out.println("Введите автора:");
        author=scan.nextLine();
        System.out.println("Введите название произведения:");
        title=scan.nextLine();

        do {
            System.out.println("Введите дату выдачи(в формате dd.mm.yyyy):");
        }while ((date1=getDateFromString(scan.nextLine()))==null);
        do {
            System.out.println("Введите дату возврата(в формате dd.mm.yyyy):");
        }while ((date2=getDateFromString(scan.nextLine()))==null);
        records.add(new Record(Integer.parseInt(number.toString()), author, title, date1, date2));


    }*/
    public void addRecord(Record record){
        records.add(record);
    }
    public static Date getDateFromString(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOne = null;

        try {
            dateOne = format.parse(date);
        } catch (Exception e) {
        }
        return dateOne;
    }

    public String getTitleByNumber(int number){
        Record record = findRecord(number);
        if (record!=null){
            return record.getTitle()+" "+record.getReturningDay().toString();
        }
        return "Книга не найдена";
    }
    public Record findRecord(int number){
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getNumber() == number) {
                return records.get(i);
            }
        }
        return null;
    }
    public boolean isSameAuthor(int number1, int number2){
        if (findRecord(number1).getAuthor().equals(findRecord(number2).getAuthor())){
            return true;

        }
        return false;
    }
    public ArrayList<Record> expiredBooksList(){
        ArrayList<Record> list=  new ArrayList<>();
        for (int i = 0; i <records.size() ; i++) {
            if (records.get(i).getDelta() > 28) {
                list.add(records.get(i));
            }



        }
        return list;
    }
    public void setReturningDayAsToday(int number){
        Record record = findRecord(number);
        if (record!=null){
            record.setReturningDayAsToday();
        }
    }
    public void writeRecordsToFile(String path){
        FileOutputStream file;
        String splitSymbol = ";";
        String data = "";
        for (int i = 0; i <this.records.size() ; i++) {
            data+=records.get(i).getNumber()+splitSymbol+records.get(i).getAuthor()+splitSymbol+records.get(i).getTitle()+
                    splitSymbol+records.get(i).getReceivingDay().getTime()+splitSymbol+records.get(i).getReturningDay().getTime()+"\n";

        }
        try {
            file= new  FileOutputStream(path);
            file.write(data.getBytes());

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    public void readRecordFromFile(String path){
        String data = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            int byteReader;
            while ((byteReader=fileInputStream.read())!=-1){
                data+=(char)byteReader;
                if (data.contains("\n")){
                    Record record = getRecordFromString(data.substring(0, data.indexOf("\n")));
                    if (record != null){
                        if (record.getDelta()>=28){
                            records.add(record);
                        }
                    }

                    data = "";
                }

            }
            Record lastRecord = (getRecordFromString(data));
            if (lastRecord!=null){
                if (lastRecord.getDelta()>=28){
                    records.add(lastRecord);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        //records=newRecords;
    }
    public Record getRecordFromString(String string){
        if (!string.isEmpty()){
            String[] list = string.split(";");
            return new Record(Integer.parseInt(list[0]),
                    list[1],
                    list[2],
                    new Date(Long.parseLong(list[3])),
                    new Date(Long.parseLong(list[4].replaceAll(" ", ""))));
        }
        return null;
    }

    public void whiteToRandomAccessFile(String path){
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            for (int i = 0; i < records.size(); i++) {
                raf.writeBytes(expandStringToWriting(records.get(i))+"\n");

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void rewriteRandomAccessFile(String path, int Number){
        ArrayList<Record> records = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            raf.seek(80+101*Number);
            raf.writeBytes(new Date().getTime()+String.join("", Collections.nCopies(20-Long.toString(new Date().getTime()).length(), " ")));
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    static String expandStringToWriting(Record record){
        String ret = "";
        ret+=record.getNumber()+String.join("", Collections.nCopies(20-Integer.toString(record.getNumber()).length(), " "));
        ret+=record.getAuthor()+String.join("", Collections.nCopies(20-record.getAuthor().length(), " "));
        ret+=record.getTitle()+String.join("", Collections.nCopies(20-record.getTitle().length(), " "));
        ret+=record.getReceivingDay().getTime()+String.join("", Collections.nCopies(20-Long.toString(record.getReceivingDay().getTime()).length(), " "));
        ret+=record.getReturningDay().getTime()+String.join("", Collections.nCopies(20-Long.toString(record.getReturningDay().getTime()).length(), " "));
        return ret;
    }
    public void clearArray(){
        this.records.clear();
    }
    public String printArray(){
        String data="";
        for (int i = 0; i <records.size() ; i++) {
            data+=records.get(i).toString();
        }
        return data;
    }

}
