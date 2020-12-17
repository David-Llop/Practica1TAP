package part2;

import part1.Message;

import java.util.ArrayList;

public abstract class Observer {
    public abstract void update(ArrayList<Message> newValue);
}