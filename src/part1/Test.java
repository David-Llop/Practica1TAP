package part1;

import java.text.ParseException;
import java.util.Date;
// MAIN
public class Test
{

    public static void main (String[] Args) throws ParseException {

        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop99 = new User("Llop99", "David", User.formatter.parse("18-10-1999"));
    }
}
