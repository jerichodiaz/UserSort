package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diaz, Jericho Hans
 * On 3/17/2018
 */
public class Log {
    public static List<Log> logs = new ArrayList<>();
    public Item parent;
    public Item child;
    public List<Item> added;
    public Log(Item parent, Item child, List<Item> added){
        this.parent = parent;
        this.child = child;
        this.added = added;
    }
    public static void addLog(Item parent, Item child, List<Item> added){
        logs.add(new Log(parent, child, added));
    }
    public static Log getRecent(){
        return logs.get(logs.size()-1);
    }
    public static Log getRecentAndDelete(){
        Log log = logs.get(logs.size()-1);
        logs.remove(logs.size()-1);
        return log;
    }
}
