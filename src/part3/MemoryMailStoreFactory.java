package part3;

import part1.IMailStore;
import part1.InMemoryMailStore;
import part1.OnFileMailStore;

/**
 * Factory that creates memory mail store
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class MemoryMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailStore() {
        return new InMemoryMailStore();
    }
}
