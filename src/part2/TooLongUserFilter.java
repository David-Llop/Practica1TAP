package part2;

import part1.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class TooLongUserFilter extends Observer{
    @Override
    public void update(ArrayList<Message> newValue, ArrayList<Message> spam) {
        spam.addAll(newValue.stream().filter(message -> message.getText().length()>20).collect(Collectors.toCollection(ArrayList::new)));
        newValue.removeAll(newValue.stream().filter(message -> message.getText().length()>20).collect(Collectors.toCollection(ArrayList::new)));
    }
}
