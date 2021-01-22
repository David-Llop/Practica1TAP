package part3;

import part1.IMailStore;
import part1.OnFileMailStore;
import part2.CipherEncripter;
import part2.MailStoreEncode;
import part2.ReverseEnripting;

public class FileWithWrappersFileMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailstore() {
        return new MailStoreEncode(new MailStoreEncode(new OnFileMailStore("EncodedMailStore.txt"), new ReverseEnripting()), new CipherEncripter());
    }
}
