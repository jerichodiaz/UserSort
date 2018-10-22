package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton, start;
    @FXML
    private TableView table;

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Item, String> name = new TableColumn("List");
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));

        table.setItems(model.getList());
        table.getColumns().addAll(name);
        leftButton.setDisable(true);
        rightButton.setDisable(true);

    }

    @FXML
    private void onLeftButtonClick() {
        model.leftClick();
        if (!model.finished)
            setButtonText();
        else
            onFinish();
    }

    @FXML
    private void onRightButtonClick() {
        model.rightClick();
        if (!model.finished)
            setButtonText();
        else
            onFinish();
    }
    @FXML private void undoClick(){
        if(Log.logs.size()>0) {
            model.undo();
            setButtonText();
        }
    }

    @FXML
    private void sortStart() {
        model.start();
        Log.logs.clear();
        if (model.getList().size() >= 2) {
            leftButton.setDisable(false);
            rightButton.setDisable(false);
            setButtonText();
            start.setDisable(true);
        }
    }

    private void setButtonText() {
        leftButton.setText(model.getLeftItemName());
        rightButton.setText(model.getRightItemName());
    }

    private void onFinish() {
        start.setDisable(false);
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        model.setSortedList();
        showSortedList();
    }

    @FXML
    private void openListEditor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("uiList.fxml"));
            UiListController listController = new UiListController(model);
            loader.setControllerFactory(c -> listController);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    listController.save();
                    start.setDisable(false);
                }
            });
            stage.setTitle("List editor");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    @FXML
    private void showSortedList() {
        if(model.getList().size()!=0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("uiSortedList.fxml"));
                uiSortedListController listController = new uiSortedListController(model.getList());
                loader.setControllerFactory(c -> listController);

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Sorted List");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
