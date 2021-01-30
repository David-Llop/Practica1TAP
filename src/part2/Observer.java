package part2;

import part1.Message;

import java.util.ArrayList;

/**
 * Defines the methods needed by the observers
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public abstract class Observer {
    public abstract void update(ArrayList<Message> newValue, ArrayList<Message> spam);
}