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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Aditivo;
import logica.Base;
import logica.Controlador;
import logica.Base;


public class ListadoBasesController implements Initializable, ControlledScreen{
    ScreensController myController;
    @FXML private static TableView<Base> tableView;
    private String nombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblErrorNombre;

        @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                            @FXML MenuItem listadoBase;
                            @FXML MenuItem listadoProducto;
    @FXML MenuItem aditivoABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
        @FXML TextField txtNombreEliminar;
    @FXML Button botonEliminar;
    @FXML Menu inicio;
    Stage stage;
        @FXML
    public void eliminarBase(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
        
        Stage stage=new Stage();
        Controlador controlador=null;
        Base base= tableView.getSelectionModel().getSelectedItem();
        int answer= MessageBox.show(stage,
        "Estas seguro de que quieres eliminar este base?",
        "Information dialog",
        MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
        System.out.println(answer);
        if(answer==65536){
            controlador=controlador.dameControlador();
            controlador.eliminarBase(base);
            txtNombreEliminar.setText("");
            botonEliminar.setDisable(true);
            cargar();
        }
    }
    public void anyadirBase(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
        Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       nombre=txtNombre.getText();
            if(!nombre.equals("")){
             Base base=controlador.getBase(nombre);
            if(base!=null){
            lblErrorNombre.setText("Ese base ya existe");
            txtNombre.setText("");
            error=true;
            }else{
              lblErrorNombre.setText("");  
            }
                
            }else{
                lblErrorNombre.setText("Debes rellenar el nombre");
                error=true;
            }
            if(!error){
           Base base=new Base(0, nombre);
                controlador.insertarBase(base);  
                lblErrorNombre.setText("");
                  txtNombre.setText("");     
                  int answer = MessageBox.show(stage,
						"El base se ha creado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
        
        botonEliminar.setDisable(true);
      tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Base base= tableView.getSelectionModel().getSelectedItem();
                if(base!=null){
                    txtNombreEliminar.setText(base.getNombre());
                    botonEliminar.setDisable(false);
                    txtNombre.setText("");
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
       
       listadoBase.setDisable(true);
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
                   root = FXMLLoader.load(getClass().getResource("ListadoBases_ComboBox.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Aditivo a Base");
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
      
        tableView.setEditable(true);
        tableView.setMaxWidth(520);
        TableColumn Id=new TableColumn("Id");
        TableColumn Nombre=new TableColumn("Nombre");
        Id.setMinWidth(100);
        Nombre.setMinWidth(420);
      Id.setCellValueFactory(new PropertyValueFactory<Base,String>("Id"));
      Nombre.setCellValueFactory(new PropertyValueFactory<Base,String>("Nombre"));
      tableView.getColumns().addAll(Id,Nombre);
      
      cargar();
   Nombre.setCellFactory(TextFieldTableCell.forTableColumn());
    Nombre.setOnEditCommit(
    new EventHandler<CellEditEvent<Base, String>>() {
        @Override
        public void handle(CellEditEvent<Base, String> t) {
               Controlador controlador = null;
               Base base=null;
               Stage stage=new Stage();
        try {
            controlador=controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                base=controlador.getBase(t.getNewValue());
            } catch (DAOExcepcion ex) {
                Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"El nombre del base no  puede estar vacio",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
            if(base==null && !t.getNewValue().trim().equals("")){
               ((Base) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setNombre(t.getNewValue());
               Base baseA=  ((Base) t.getTableView().getItems().get(
                t.getTablePosition().getRow()));
               baseA.setNombre(t.getNewValue().trim());
                   try {
                       controlador.modificarBase(baseA);
                   } catch (DAOExcepcion ex) {
                       Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               cargar();
            }
           
            if(base!= null && !t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"Ese base ya existe, introduzca un nombre distinto",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
    
            }
      
        }
    }
);
    
        }
    
    
    public static void cargar(){
        
                    Controlador controlador = null;

  ArrayList<Base> bases=new ArrayList<Base>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bases=controlador.getBases();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
       ObservableList<Base> base = FXCollections.observableList(bases);  
       tableView.setItems(base);
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
    
}