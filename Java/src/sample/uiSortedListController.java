package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Diaz, Jericho Hans
 * On 3/17/2018
 */
public class uiSortedListController implements Initializable{
    private List<Item> items;
    @FXML private ListView<String> table;
    private ObservableList<String> sItems = FXCollections.observableArrayList();
    private Clipboard clipboard;
    public uiSortedListController(List<Item> items){
        clipboard = Clipboard.getSystemClipboard();
        this.items = items;
        for(int i = 0; i < items.size(); i++){
            sItems.add((i+1)+". "+items.get(i).getItemName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(sItems);
    }

    @FXML private void copyData(){
        String content="";
        for(int i = 0; i < items.size(); i++){
            content += (i+1) + ". " + items.get(i).getItemName() + "\n";
        }
        ClipboardContent c = new ClipboardContent();
        c.putString(content);
        clipboard.setContent(c);
    }
    @FXML private void copyDataNoNumber(){
        String content="";
        for(int i = 0; i < items.size(); i++){
            content += items.get(i).getItemName() + "\n";
        }
        ClipboardContent c = new ClipboardContent();
        c.putString(content);
        clipboard.setContent(c);
    }
}
