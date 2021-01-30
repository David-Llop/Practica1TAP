package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Contains predefined filters
 */
public class Filtrate {

    /**
     * Class implementing the predicate to filtrate messages with less than the given words in the body.
     * @author David Llop Roig
     * @author Anna Julia Naval
     */
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

    /**
     * Class implementing the predicate to filtrate messages containing the given word, either in the subject or the body.
     */
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

    /**
     * Class implementing the predicate to filtrate messages whose sender has the given username
     */
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

    /**
     * Class implementing the predicate to filtrate messages containing the given word, at least in the subject.
     */
    public static class ContainsSubject implements Predicate<Message> {
        String str;

        public ContainsSubject(String str) {
            this.str = str;
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
            return message.getSubject().contains(str);
        }
    }
}
