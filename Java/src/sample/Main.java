package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setControllerFactory(control -> new Controller(new Model()));

        primaryStage.setTitle("List sorting");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
