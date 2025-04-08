package is.vidmot;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerApplication.class.getResource("heima-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.HEIMA, true);

        stage.setTitle("TourUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
