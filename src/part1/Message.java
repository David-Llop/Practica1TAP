package part1;

import java.util.Date;

public class Message {

    private String text, title, from, to;
    private Date maildate; //Date(int year, int month, int date, int hrs, int min)
    private int numberWords;

    public Message(String text, String title, String from, String to, Date maildate, int numberWords) {
        this.text = text;
        this.title = title;
        this.from = from;
        this.to = to;
        this.maildate = maildate;
        this.numberWords = numberWords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) { this.from = from; }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getMaildate() {
        return maildate;
    }

    public void setMaildate(Date maildate) {
        this.maildate = maildate;
    }

    public int getNumberWords() { return numberWords; }

    public void setNumberWords(int numberWords) { this.numberWords = numberWords; }
}
