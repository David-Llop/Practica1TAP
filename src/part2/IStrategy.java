package part2;

/**
 * Defines the functions needed by a Encoder or Encryptor
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public interface IStrategy {
    /**
     * Given a string, returns it encoded
     * @param message message to encode
     * @return message encoded
     */
    public String encode(String message);

    /**
     * Given an encoded string, decodes it
     * @param message encoded message
     * @return decoded message
     */
    public String decode(String message);
}
