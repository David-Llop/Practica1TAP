package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class Filtrate {
    public static ArrayList<Message> filter(Predicate<Message> predicate, ArrayList<Message> mailList){
        ArrayList<Message> result = new ArrayList<>();
        Collections.addAll(result, mailList.stream().filter(predicate).toArray(Message[]::new));
        return result;
    }

    public static class LessThanPredicate implements Predicate<Message>{

        int max;

        public LessThanPredicate(int max) {
            this.max = max;
        }

        /**
         * Evaluates this predicate on the given argument.
         *
         * @param message the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         */
        @Override
        public boolean test(Message message) {
            return message.getWordCount() < max;
        }
    }

    public static class ContainsPredicate implements Predicate<Message>{
        String word;

        public ContainsPredicate(String word) {
            this.word = word;
        }


        /**
         * Evaluates this predicate on the given argument.
         *
         * @param message the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         */
        @Override
        public boolean test(Message message) {
            return message.getText().contains(word) || message.getSubject().contains(word);
        }
    }

    public static class SenderIs implements Predicate<Message> {
        String  string;

        public SenderIs(String string) {
            this.string = string;
        }

        @Override
        public boolean test(Message message) {
            return message.getFrom().equals(string);
        }
    }

    public static class ContainsSubject implements Predicate<Message> {
        String str;

        public ContainsSubject(String str) {
            this.str = str;
        }

        @Override
        public boolean test(Message message) {
            return message.getSubject().contains(str);
        }
    }
}
