package part3;

import part1.IMailStore;
import part1.InMemoryMailStore;
import part1.OnFileMailStore;

public class MemoryMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailstore() {
        return new InMemoryMailStore();
    }
}
