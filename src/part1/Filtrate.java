package part1;

import java.util.ArrayList;
import java.util.Collections;

public class Filtrate {
    public static ArrayList<Message> contains (String word, ArrayList<Message> mailList){
        ArrayList<Message> result = new ArrayList<>();
        Collections.addAll(result, mailList.stream().filter(t->t.getSubject().contains(word) || t.getText().contains(word)).toArray(Message[]::new));
        return result;
    }

    public static ArrayList<Message> lessThan(int max_Words, ArrayList<Message> mailList){
        ArrayList<Message> result = new ArrayList<>();
        Collections.addAll(result, mailList.stream().filter(t->t.getWordCount() < max_Words).toArray(Message[]::new));
        return result;
    }
}
