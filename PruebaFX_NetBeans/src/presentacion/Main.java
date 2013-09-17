/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author roberto
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       ObjetoCompartido.dameLo().setStage(primaryStage);
       primaryStage.setTitle("Calculadora de Pigmentos");
       Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
       Scene myScene = new Scene(myPane);
       primaryStage.setScene(myScene);
       primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
