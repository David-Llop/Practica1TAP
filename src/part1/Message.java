package part1;

import java.util.Date;

public class Message {
    private String text, from, to, subject;
    private Date date;
    private int nWords;

    public Message(String text, String from, String to, String subject, Date date, int nWords) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.date = date;
        this.nWords = nWords;
    }

    public void setText(String text) { this.text = text; }

    public void setFrom(String from) { this.from = from; }

    public void setTo(String to) {this.to = to; }

    public void setSubject(String subject) { this.subject = subject; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public int getnWords() { return nWords; }

    public void setnWords(int nWords) { this.nWords = nWords; }

    public String getFrom() { return from; }

    public String getTo() { return to; }

    public String getSubject() { return subject; }

    public String getText() { return text; }
}
