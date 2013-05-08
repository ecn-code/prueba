/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package helloworld;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller class of the HelloWorld sample.
 */
public class HelloWorldController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML //  fx:id="button"
    private Button button; // Value injected by FXMLLoader

    /**
     * Initializes the controller class.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'HelloWorld.fxml'.";

        if (button != null) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Hello World");
                }
            });
        }
    }
}
