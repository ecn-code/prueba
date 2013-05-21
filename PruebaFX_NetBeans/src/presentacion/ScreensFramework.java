/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author roberto
 */
public class ScreensFramework extends Application{

     public static final String MAIN_SCREEN = "fxml_tableview"; 
     public static final String MAIN_SCREEN_FXML = "fxml_tableview.fxml"; 
     public static final String ANYADIR_PRODUCTO_SCREEN = "anyadirProducto"; 
     public static final String ANYADIR_PRODUCTO_SCREEN_FXML = "AnyadirProducto.fxml"; 
     public static final String ROULETTE_SCREEN = "roulette"; 
     public static final String ROULETTE_SCREEN_FXML ="roulette.fxml"; 

     @Override 
     public void start(Stage primaryStage) { 

       ScreensController mainContainer = new ScreensController(); 
       mainContainer.loadScreen(ScreensFramework.MAIN_SCREEN, 
                            ScreensFramework.MAIN_SCREEN_FXML); 
       mainContainer.loadScreen(ScreensFramework.ANYADIR_PRODUCTO_SCREEN, 
                            ScreensFramework.ANYADIR_PRODUCTO_SCREEN_FXML); 
       mainContainer.loadScreen(ScreensFramework.ROULETTE_SCREEN, 
                            ScreensFramework.ROULETTE_SCREEN_FXML); 

       mainContainer.setScreen(ScreensFramework.MAIN_SCREEN); 

       Group root = new Group(); 
       root.getChildren().addAll(mainContainer); 
       Scene scene = new Scene(root); 
       primaryStage.setScene(scene); 
       primaryStage.show(); 
    
}
      public static void main(String[] args) {
        launch(args);
    }
}
