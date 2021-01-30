package part3;

import part1.IMailStore;

/**
 * Defines the methods needed by any Mail Store factory
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public interface IMailStoreFactory {
    public IMailStore createMailStore();
}
