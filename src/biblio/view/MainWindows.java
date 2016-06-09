package biblio.view;

import java.io.IOException;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindows extends Application {

	private Stage windows;
	private AnchorPane rootLayout;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.windows = primaryStage;
		windows.setTitle("BiblioApp");

		initRootLayout();
	}

	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindows.class.getResource("InscriptionFXML.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            windows.setScene(scene);
            windows.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
