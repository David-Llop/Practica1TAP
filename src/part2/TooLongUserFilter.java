package part2;

import part1.Message;

import java.util.ArrayList;
import java.util.Collection;

public class TooLongUserFilter extends Observer{
    @Override
    public void update(ArrayList<Message> newValue) {
       newValue.removeAll((Collection<?>) newValue.stream().filter(t->t.getText().length()>20));
    }
}
