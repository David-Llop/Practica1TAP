package part3;

import part1.IMailStore;

public class RedisMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailstore() {
        return JedisAddapter.getInstance();
    }
}
