/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebafx_netbeans;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Elias
 */
public class SampleController implements Initializable {
    
    
    private String granulometria,nombre;
   // private Producto producto;
    private boolean errorLetra=false;
    private boolean errorNombre=false;
    @FXML
    private TextField txtGranulometria,txtNombre;
    @FXML
    private Label lblErrorGranulometria,lblErrorNombre;
    private char[] array;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       granulometria=txtGranulometria.getText(); 
       nombre=txtNombre.getText();
            granulometria=granulometria.replace(',', '.');
        for(char x: granulometria.toCharArray()){
            if(Character.isLetter(x)){
                errorLetra=true;
                System.out.println(Character.isLetter(x));
                break;
            }
           }
        
            if(errorLetra){
            lblErrorGranulometria.setText("No se pueden introducir letras");
            lblErrorGranulometria.setVisible(true);
            txtGranulometria.setText("");
            }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
