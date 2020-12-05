package part1;

public class Message {

    private String from, to, subject, text;

    public Message(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
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
        return from + ";" + to + ";" + subject + ";" + text + "\n";
    }
}
