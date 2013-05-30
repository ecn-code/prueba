/*
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package presentacion;

import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.Aditivo;
import logica.Controlador;



public class ListadoAditivosController implements Initializable, ControlledScreen{
    @FXML private static TableView<Aditivo> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    ScreensController myController;
    @FXML
    protected void addPerson(ActionEvent event) throws IOException {
       /* Stage stage=new Stage();
         Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("AnyadirProducto.fxml"));
         Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();*/
        myController.setScreen(ScreensFramework.ANYADIR_PRODUCTO_SCREEN);

       /* ObservableList<Producto> data = tableView.getItems();
        data.add(new Producto(Integer.parseInt(firstNameField.getText()),

            lastNameField.getText()
        ));
        
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");   */
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
  cargar();
    }
    
    public static void cargar(){
        
                    Controlador controlador = null;

  ArrayList<Aditivo> aditivos=new ArrayList<Aditivo>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            aditivos=controlador.getAditivos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  for(int i=0;i<aditivos.size();i++){
                      System.out.println(aditivos.get(i));
                  }
       ObservableList<Aditivo> aditivo = FXCollections.observableList(aditivos);  
       tableView.setItems(aditivo);
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
    
}