package com.company.Records;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class MemberCard {

    ArrayList<Record> records = new ArrayList<>();
    private static final MemberCard INSTANCE = new MemberCard();
    private MemberCard(){

    }
    public static MemberCard getMemberCard(){
        return INSTANCE;
    }

    public void addRecord(Record record){
        records.add(record);
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
    public void delete(int index){
        records.remove(index);
    }
    public String[] getList(){
        String[] list = new String[records.size()];
        for (int i = 0; i <records.size() ; i++) {
            list[i] = records.get(i).toString();

        }
        return list;
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
            return;
        }


    }
    public void readRecordFromFile(String path){
        ArrayList<Record> newRecords= new ArrayList<>();

        String data = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            int byteReader;
            while ((byteReader=fileInputStream.read())!=-1){
                data+=(char)byteReader;
                if (data.contains("\n")){
                    Record record = getRecordFromString(data.substring(0, data.indexOf("\n")));
                    newRecords.add(record);

                    data = "";
                }

            }
            records = newRecords;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Record getRecordFromString(String string){
        String[] list = string.split(";");
        return new Record(Integer.parseInt(list[0]), list[1], list[2], new Date(Long.parseLong(list[3])),new Date(Long.parseLong(list[4].replaceAll(" ", ""))));

    }
    public static void whiteToRandomAccessFile(String path, ArrayList<Record> records){
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            for (int i = 0; i < records.size(); i++) {
                raf.writeBytes(expandStringToWriting(records.get(i))+"\n");

            }

        }
        catch (Exception e){

        }

    }
    public static void rewriteRandomAccessFile(String path, int Number){
        ArrayList<Record> records = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            raf.seek(80+101*Number);
            raf.writeBytes(new Date().getTime()+String.join("", Collections.nCopies(20-Long.toString(new Date().getTime()).length(), " ")));
        }
        catch (Exception e){

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
}
