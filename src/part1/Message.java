package part1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    private String from, to, subject, text;
    private Date sendDate;

    public Message(String from, String to, String subject, String text) {
        this.sendDate = new Date();
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public Message(String from, String to, String subject, String text, Date sendDate) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.sendDate = sendDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String toString(){
        return from + ";" + to + ";" + subject + ";" + text +";" + formatter.format(sendDate) + "\n";
    }

    public int getWordCount(){
        return text.split(" ").length;
    }
}
