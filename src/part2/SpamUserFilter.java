package part2;

import part1.Message;

import java.util.ArrayList;
import java.util.Collection;

public class SpamUserFilter extends Observer{
    @Override
    public void update(ArrayList<Message> newValue, ArrayList<Message> spam) {
        spam.addAll((Collection<? extends Message>) newValue.stream().filter(t->t.getFrom().contains("spam")));
       newValue.removeAll((Collection<?extends Message>) newValue.stream().filter(t->t.getText().length()>20));
    }
}
