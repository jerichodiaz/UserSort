package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item {
    private final SimpleStringProperty itemName;
    private final Set<Item> greater = new HashSet<>();

    public Item() {
        this(null);
    }

    public Item(String name) {
        this.itemName = new SimpleStringProperty(name);
    }

    public void setItemName(String itemName) {
        itemNameProperty().set(itemName);
    }

    public void addGreater(Item item) {
        List<Item> added = new ArrayList<>();
        added.add(item);
        item.greater.forEach( i -> {
            if(greater.add(i))
                added.add(i);
        });
        greater.add(item);
        Log.addLog(this, item, added);
    }
    public Set<Item> getGreater(){
        return greater;
    }
    public int getGreaterSize(){
        return greater.size();
    }

    public String getItemName() {
        return itemNameProperty().get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    @Override
    public boolean equals(Object obj) {
        if (getItemName() == null && ((Item) obj).getItemName() == null)
            return true;
        if (obj == this) return true;
        if (!(obj instanceof Item))
            return false;
        Item item = (Item) obj;
        return getItemName().equals(((Item) obj).getItemName());
    }

    @Override
    public int hashCode() {
        return itemName.hashCode();
    }
}
