package part2;

/**
 * Implements the strategy to encrypt by reversing the string
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class ReverseEncrypting implements IStrategy {
    /**
     * Given a string, returns it encoded
     * @param message message to encode
     * @return message encoded
     */
    @Override
    public String encode(String message) {
        StringBuilder aux = new StringBuilder(message);
        return aux.reverse().toString();
    }

    /**
     * Given an encoded string, decodes it
     * @param message encoded message
     * @return decoded message
     */
    @Override
    public String decode(String message) {
        StringBuilder aux = new StringBuilder(message);
        return aux.reverse().toString();
    }
}
