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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logica.*;
import jfx.messagebox.*;

/**
 *
 * @author Elias
 */
public class AnyadirAcabadoController implements Initializable,ControlledScreen{
    
    ScreensController myController;
    private String factor,nombre;
    @FXML
    private TextField txtFactor,txtNombre;
    @FXML
    private Label lblErrorFactor,lblErrorNombre;
    @FXML
   
    private void handleButtonAction(ActionEvent event) throws DAOExcepcion, DominioExcepcion, Throwable {
        Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       factor=txtFactor.getText(); 
       nombre=txtNombre.getText();
            factor=factor.replace(',', '.');
        for(char x: factor.toCharArray()){
            if(Character.isLetter(x)){
                error=true;
                lblErrorFactor.setText("No se pueden introducir letras");
                lblErrorFactor.setVisible(true);
                txtFactor.setText("");
                break;
            }
            if(factor.equals("")){
                 txtFactor.setText("El factor no puede estar vacio");
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
           Acabado acabado=new Acabado(0, nombre, Double.parseDouble(factor));
                controlador.insertarAcabado(acabado);  
                lblErrorFactor.setText("");
                lblErrorNombre.setText("");
                txtFactor.setText("");
                  txtNombre.setText("");     
                  int answer = MessageBox.show(stage,
						"El acabado se ha creado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                  FXMLTableViewController.cargar();
                   myController.setScreen(ScreensFramework.MAIN_SCREEN);
            }
    }
    @FXML  private void buttonCancelar(ActionEvent event) throws DAOExcepcion, DominioExcepcion, Throwable {
        myController.setScreen(ScreensFramework.MAIN_SCREEN);
    }
    @FXML AnchorPane anchorPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
myController=screenPage;
    }
}
