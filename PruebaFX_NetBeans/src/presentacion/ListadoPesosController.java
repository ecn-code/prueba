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
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Peso;
import logica.Controlador;
import logica.Producto;


public class ListadoPesosController implements Initializable, ControlledScreen{
    @FXML private static TableView<Peso> tableView;
    ScreensController myController;
    private String pesoMin,pesoMax;
    @FXML
    private TextField txtPesoMin,txtPesoMax,txtNombreEliminar,txtFactorEliminar;
   @FXML private Button btnAnyadir;
    @FXML
    private Label lblErrorPesoMin;
         @FXML
    private Label   lblErrorPesoMax;
            
          @FXML MenuItem listadoProducto;
    @FXML MenuItem listadoPeso;
    @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                            @FXML MenuItem listadoBase;
    @FXML MenuItem aditivoABase,concentracionABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
    @FXML Menu inicio;
    Stage stage;
    @FXML
   
    public void anyadirPeso(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
        Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       pesoMin=txtPesoMin.getText().trim().replace(',', '.'); 
       pesoMax=txtPesoMax.getText().trim().replace(',', '.');
          
        for(char x: pesoMin.toCharArray()){
            if(Character.isLetter(x)){
                error=true;
                lblErrorPesoMin.setText("No se pueden introducir letras");
                txtPesoMin.setText("");
                break;
            }else{
               lblErrorPesoMin.setText(""); 
            }
            }
            if(pesoMin.equals("")){
                 lblErrorPesoMin.setText("Debes rellenar el peso minimo");
                error=true;
            }
            
        for(char x: pesoMax.toCharArray()){
            if(Character.isLetter(x)){
                error=true;
                lblErrorPesoMax.setText("No se pueden introducir letras");
                txtPesoMax.setText("");
                break;
            }else{
               lblErrorPesoMax.setText(""); 
            }
            }
            if(pesoMax.equals("")){
                 lblErrorPesoMax.setText("Debes rellenar el peso minimo");
                error=true;
            }
           
            
            if(!error){
               
           Peso peso=new Peso(0,Double.parseDouble(pesoMin) , Double.parseDouble(pesoMax));
                controlador.modificarPeso(peso);
                lblErrorPesoMin.setText("");
                lblErrorPesoMax.setText("");     
                  int answer = MessageBox.show(stage,
						"El peso se ha guardado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
               
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
    Controlador controlador = null;
        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoPesosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoPesosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Peso peso=null;
        try {
            peso = controlador.getPeso();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoPesosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(peso!=null){
           txtPesoMin.setText(peso.getPesoMin()+"");
           txtPesoMax.setText(peso.getPesoMax()+"");
        }else{
            peso=new Peso(0,0.0,0.0);
             txtPesoMin.setText(0.0+"");
           txtPesoMax.setText(0.0+"");
        try {
            controlador.insertarPeso(peso);
            
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoPesosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        txtPesoMin.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                {
                    try {
                        anyadirPeso(null);
                    } catch (DAOExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DominioExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListadoProductosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        txtPesoMax.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                {
                    try {
                        anyadirPeso(null);
                    } catch (DAOExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DominioExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListadoProductosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        stage = ObjetoCompartido.dameLo().getStage();
       listadoAcabado.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoAcabados.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
  
        stage.setTitle("Acabados");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       listadoProducto.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoProductos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
  
        stage.setTitle("Productos");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       
       
       listadoPigmento.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoPigmentos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pigmentos");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       listadoBase.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoBases.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Bases");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       listadoAditivo.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoAditivos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Aditivos");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       baseAPigmento.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoPigmento_ComboBox.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pigmento a Base");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
      aditivoABase.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("AnyadirAditivosABase.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Aditivo a Base");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       concentracionABase.setOnAction(new EventHandler<ActionEvent>() {

          @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoConcentracionBase.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
    
        stage.setTitle("Añadir concentracion a base");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
      inicio.setOnShown(new EventHandler<Event>() {

          @Override
           public void handle(Event t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
    
        stage.setTitle("Inicio");
        stage.setScene(new Scene(root));
        stage.show();
        inicio.hide();
           }
       });
 
      calcular.setOnShown(new EventHandler<Event>() {

          @Override
           public void handle(Event t) {

               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("Calcular.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Calcular");
        stage.setScene(new Scene(root));
        stage.show();
        calcular.hide();
           }
       });
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }    
}