package com.company.Records;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record implements Serializable {

    private int number;
    private String author;
    private String title;
    private Date date1;
    private Date date2;

    public int getNumber() {
        return number;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Date getReceivingDay() {
        return date1;
    }

    public Date getReturningDay() {
        return date2;
    }

    public int getDelta() {
        return delta;
    }
    public void setReturningDayAsToday(){
        date2 = new Date();
    }


    private int delta;
    public Record(int number, String author ,String title, Date receivingDay, Date returningDay){
        this.number = number;
        this.author = author;
        this.title = title;
        this.date1 = receivingDay;
        this.date2 = returningDay;
        delta = setDelta(returningDay,receivingDay);
    }

    public Record(int number, String author ,String title, String receivingDay, String returningDay){
        this.number = number;
        this.author = author;
        this.title = title;
        this.date1 = getDateFromString(receivingDay);
        this.date2 = getDateFromString(returningDay);
        delta = setDelta(this.date1,this.date2);
    }

    int setDelta(Date dateOne, Date dateTwo){
        long difference = dateOne.getTime() - dateTwo.getTime();
        return (int)(difference / (24 * 60 * 60 * 1000));
    }
    private Date getDateFromString(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOne = null;

        try {
            dateOne = format.parse(date);
        } catch (Exception e) {
        }
        return dateOne;
    }

    public static boolean isDate(String date){
        try{
            int day = Integer.parseInt(date.substring(0,2));
            int month = Integer.parseInt(date.substring(3,5));
            int year = Integer.parseInt(date.substring(6,10));
            if (date.length() ==10){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public String toString(){
        return number+" "+author+" "+title+" "+date1+" "+date2;
    }


}
