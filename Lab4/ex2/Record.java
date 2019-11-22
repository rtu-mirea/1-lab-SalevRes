package ex2;

import java.io.Serializable;
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
    int setDelta(Date dateOne, Date dateTwo){
        long difference = dateOne.getTime() - dateTwo.getTime();
        return (int)(difference / (24 * 60 * 60 * 1000));
    }

    public String toString(){
        return number+" "+author+" "+title+" "+date1+" "+date2;
    }


}
