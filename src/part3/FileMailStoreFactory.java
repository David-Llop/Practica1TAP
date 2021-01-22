package part3;

import part1.IMailStore;
import part1.OnFileMailStore;

public class FileMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailstore() {
        return new OnFileMailStore("MailStore.txt");
    }
}
