package part3;

import part1.IMailStore;
import part1.OnFileMailStore;
import part2.CipherEncryptor;
import part2.MailStoreEncode;
import part2.ReverseEncrypting;

/**
 * Factory that creates file mail store which reverses and ciphers the messages' body
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class FileWithWrappersFileMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailStore() {
        return new MailStoreEncode(new MailStoreEncode(new OnFileMailStore("EncodedMailStore.txt"), new CipherEncryptor()), new ReverseEncrypting());
    }
}
