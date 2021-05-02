package in.rnayabed.nirbachon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage)
    {
        Scene s = new Scene(new Base());
        stage.setScene(s);
        stage.setFullScreen(true);

        stage.show();
    }


    public static void main(String[] args) 
    {
        launch(args);
    }
}
