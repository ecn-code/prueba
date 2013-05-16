/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.sun.javafx.tk.quantum.FxEventLoop;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.*;
import jfx.messagebox.*;

/**
 *
 * @author Elias
 */
public class AnyadirProductoController implements Initializable {
    
    
    private String granulometria,nombre;
    @FXML
    private TextField txtGranulometria,txtNombre;
    @FXML
    private Label lblErrorGranulometria,lblErrorNombre;
    @FXML
   
    private void handleButtonAction(ActionEvent event) throws DAOExcepcion, DominioExcepcion, Throwable {
        Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       granulometria=txtGranulometria.getText(); 
       nombre=txtNombre.getText();
            granulometria=granulometria.replace(',', '.');
        for(char x: granulometria.toCharArray()){
            if(Character.isLetter(x)){
                error=true;
                lblErrorGranulometria.setText("No se pueden introducir letras");
                lblErrorGranulometria.setVisible(true);
                txtGranulometria.setText("");
                break;
            }
            if(granulometria.equals("")){
                 txtGranulometria.setText("La granulometria no puede estar vacia");
                error=true;
            }
           }
            if(!nombre.equals("")){
             Producto producto=controlador.getProducto(nombre);
            if(producto!=null){
            lblErrorNombre.setText("Ese nombre ya existe");
            lblErrorNombre.setVisible(true);
            txtNombre.setText("");
            error=true;
            }
                
            }else{
                lblErrorNombre.setText("El nombre no puede estar vacio");
                error=true;
            }
            if(!error){
           Producto producto=new Producto(0, nombre, Double.parseDouble(granulometria));
                controlador.insertarProducto(producto);  
                lblErrorGranulometria.setText("");
                lblErrorNombre.setText("");
                txtGranulometria.setText("");
                  txtNombre.setText("");     
                  int answer = MessageBox.show(stage,
						"El producto se ha creado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION| MessageBox.OK);
            }
            
                
            
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
