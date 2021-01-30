package part1;

import java.util.Comparator;

/**
 * Class implementing some predefined comparators
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class Sort {
    /**
     * Comparator to sort by new
     */
    public static class SortNewFirst implements Comparator<Message> {

        @Override
        public int compare(Message o1, Message o2) {
            return -1 * (o1.getSendDate().compareTo(o2.getSendDate()));
        }
    }

    /**
     * Comparator to sort by old
     */
    public static class SortOldFirst implements Comparator<Message> {

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getSendDate().compareTo(o2.getSendDate());
        }
    }

    /**
     * Comparator to sort alphabetically by sender
     */
    public static class SortSenderAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getFrom().compareTo(o2.getFrom());
        }
    }

    /**
     * Comparator to sort alphabetically reversed by sender
     */
    public static class SortSenderDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return -1*(o1.getFrom().compareTo(o2.getFrom()));
        }
    }

    /**
     * Comparator to sort alphabetically by subject
     */
    public static class SortSubjectAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getSubject().compareTo(o2.getSubject());
        }
    }

    /**
     * Comparator to sort alphabetically reversed by subject
     */
    public static class SortSubjectDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return -1*(o1.getSubject().compareTo(o2.getSubject()));
        }
    }

    /**
     * Comparator to sort by increasing message body word count
     */
    public static class SortWordsAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getWordCount()-o2.getWordCount();
        }
    }

    /**
     * Comparator to sort by descending message body word count
     */
    public static class SortWordsDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o2.getWordCount()-o1.getWordCount();
        }
    }
}
