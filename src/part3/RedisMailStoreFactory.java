package part3;

import part1.IMailStore;

/**
 * Factory that creates redis mail store
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class RedisMailStoreFactory implements IMailStoreFactory{
    @Override
    public IMailStore createMailStore() {
        return JedisAdapter.getInstance();
    }
}
