package part2;

import java.util.ArrayList;

public class ReverseEnripting implements Strategy{
    @Override
    public String encode(String message) {
        StringBuilder aux = new StringBuilder(message);
        return aux.reverse().toString();
    }

    @Override
    public String decode(String message) {
        StringBuilder aux = new StringBuilder(message);
        return aux.reverse().toString();
    }
}
