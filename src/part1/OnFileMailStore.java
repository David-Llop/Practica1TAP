package part1;

public class OnFileMailStore implements MailStore{
    @Override
    public void sendMail(Message mail) {

    }

    @Override
    public Message[] getMail(User user) {
        return new Message[0];
    }
}
