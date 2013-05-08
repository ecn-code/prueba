package presentacion;

import helloworld.Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AnyadirProducto extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		   try {
	            AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("HelloWorld.fxml"));
	            Scene scene = new Scene(page);
	            primaryStage.setScene(scene);
	            primaryStage.setTitle("Hello World Sample");
	            primaryStage.show();
	        } catch (Exception ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	}

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[])null);
	}
}
