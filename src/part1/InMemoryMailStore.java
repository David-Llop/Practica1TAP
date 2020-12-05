package part1;

public class InMemoryMailStore implements MailStore{

    /* TODO: taula[usuaris][missatges] on cada usuari té una llista amb els missatges que
        van destinats a ell?? --> List<List<Message>>
     */

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {

    }

    /**
     *Function that, given a {@link User}, returns the {@link Message} [] intended for it
     * @param user {@link User} for whom the mails are intended
     * @return {@link Message} [] of mails intended for the given user
     */
    @Override
    public Message[] getMail(String user) {

        return new Message[0];
    }
}
