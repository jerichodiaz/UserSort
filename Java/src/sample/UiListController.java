package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UiListController implements Initializable {
    @FXML private TextArea area;
    private Model model;

    public UiListController(Model model){
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.getList().forEach(e -> area.appendText(e.getItemName() + "\n"));
    }
    public void save(){
        ArrayList<Item> temp = new ArrayList<>();
        for (String s : area.getText().split("\n")) {
            if(!s.isEmpty())
            temp.add(new Item(s));
        }
        model.setList(FXCollections.observableArrayList(temp));
    }


}
