package part3;

import part1.IMailStore;

public interface IMailStoreFactory {
    public IMailStore createMailstore();
}
