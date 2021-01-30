package part2;

import part1.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Observer that filters the messages whose sender's username contains "spam" or "Spam" and moves them to the spam list
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class SpamUserFilter extends Observer{
    @Override
    public void update(ArrayList<Message> newValue, ArrayList<Message> spam) {
        spam.addAll(newValue.stream().filter(message -> message.getFrom().contains("spam") || message.getFrom().contains("Spam")).collect(Collectors.toCollection(ArrayList::new)));
        newValue.removeAll(newValue.stream().filter(message -> message.getFrom().contains("spam") || message.getFrom().contains("Spam")).collect(Collectors.toCollection(ArrayList::new)));
    }
}
