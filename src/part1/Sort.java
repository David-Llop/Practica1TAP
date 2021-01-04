package part1;

import java.util.Comparator;

public class Sort {
    public static class SortNewFirst implements Comparator<Message> {

        @Override
        public int compare(Message o1, Message o2) {
            return -1 * (o1.getSendDate().compareTo(o2.getSendDate()));
        }
    }

    public static class SortOldFirst implements Comparator<Message> {

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getSendDate().compareTo(o2.getSendDate());
        }
    }

    public static class SortSenderAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getFrom().compareTo(o2.getFrom());
        }
    }
    public static class SortSenderDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return -1*(o1.getFrom().compareTo(o2.getFrom()));
        }
    }

    public static class SortSubjectAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getSubject().compareTo(o2.getSubject());
        }
    }
    public static class SortSubjectDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return -1*(o1.getSubject().compareTo(o2.getSubject()));
        }
    }
    public static class SortWordsAsc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o1.getWordCount()-o2.getWordCount();
        }
    }
    public static class SortWordsDesc implements Comparator<Message>{

        @Override
        public int compare(Message o1, Message o2) {
            return o2.getWordCount()-o1.getWordCount();
        }
    }
}
