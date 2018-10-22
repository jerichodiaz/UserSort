package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Model {
    private Item leftItem = new Item();
    private Item rightItem = new Item();
    private final ObservableList<Item> list = FXCollections.observableArrayList();
    public boolean finished = false;

    public ObservableList<Item> getList() {
        return list;
    }

    public void setList(ObservableList<Item> newList) {
        list.clear();
        for (Item item : newList) {
            list.add(item);
        }
    }

    public void leftClick() {
        //leftItem.incRank();
        //rightItem.decRank();
        int repeat = 0;
        System.out.println("chose: "+leftItem.getItemName()+" over "+rightItem.getItemName());
        leftItem.addGreater(rightItem);
        do  {
            if (repeat > list.size() * list.size()) {
                finished = true;
                break;
            }
            setRandomItems();
            repeat++;
        }while(leftItem.getGreater().stream().anyMatch(i -> i.equals(rightItem)) || rightItem.getGreater().stream().anyMatch(i -> i.equals(leftItem)));
    }

    public void undo(){
        Log log = Log.getRecentAndDelete();
        log.parent.getGreater().removeAll(log.added);
        leftItem = log.parent;
        rightItem = log.child;
    }

    public void rightClick() {
        //rightItem.incRank();
        //leftItem.decRank();
        int repeat = 0;
        System.out.println("chose: "+rightItem.getItemName()+" over "+leftItem.getItemName());
        rightItem.addGreater(leftItem);
        do {
            if (repeat > list.size() * list.size()) {
                finished = true;
                break;
            }
            setRandomItems();
            repeat++;
        }while (rightItem.getGreater().stream().anyMatch(i -> i.equals(leftItem)) || leftItem.getGreater().stream().anyMatch(i -> i.equals(rightItem)));
    }

    public void setSortedList(){
        List<Item> sortedList = new ArrayList<>();
        list.forEach(i -> {
            sortedList.add(i);
            Collections.sort(sortedList, Comparator.comparingInt(Item::getGreaterSize).reversed());
        });
        list.setAll(sortedList);
    }

    public void start() {
        finished = false;
        leftItem = new Item();
        rightItem = new Item();
        if (list.size() >= 2) {
            setRandomItems();
        }
    }

    public void setRandomItems() {
        do {
            int rand1 = (int) (Math.random() * list.size());
            int rand2 = (int) (Math.random() * list.size());
            leftItem = list.get(rand1);
            rightItem = list.get(rand2);
        } while (leftItem.equals(rightItem));
    }

    public String getLeftItemName() {
        return leftItem.getItemName();
    }

    public String getRightItemName() {
        return rightItem.getItemName();
    }
}
