package part1;

import java.util.Date;

public class Message {
//   * part1.Message
//   + de: part1.User envia (username)
//   + per: part1.User rep (username)

    private String text, title, from, to;
    private Date maildate; //Date(int year, int month, int date, int hrs, int min)

    public Message(String text, String title, String from, String to, Date maildate) {
        this.text = text;
        this.title = title;
        this.from = from;
        this.to = to;
        this.maildate = maildate;
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

    public void setFrom(String from) {
        this.from = from;
    }

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
}
