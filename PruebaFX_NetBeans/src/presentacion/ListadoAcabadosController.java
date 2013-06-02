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
import logica.Acabado;
import logica.Controlador;
import logica.Producto;


public class ListadoAcabadosController implements Initializable, ControlledScreen{
    @FXML private static TableView<Acabado> tableView;
    ScreensController myController;
    private String factor,nombre;
    @FXML
    private TextField txtFactor,txtNombre;
    @FXML private Button botonEliminar;
    @FXML
    private Label lblErrorFactor,lblErrorNombre,nombreEliminar,factorEliminar;
            
          @FXML MenuItem listadoProducto;
    @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                            @FXML MenuItem listadoBase;
    @FXML MenuItem aditivoABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
    @FXML Menu inicio;
    Stage stage;
    @FXML
    public void eliminarAcabado(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
        Stage stage=new Stage();
        Controlador controlador=null;
        Acabado acabado= tableView.getSelectionModel().getSelectedItem();
        int answer= MessageBox.show(stage,
        "Estas seguro de que quieres eliminar este acabado?",
        "Information dialog",
        MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
        System.out.println(answer);
        if(answer==65536){
            controlador=controlador.dameControlador();
            controlador.eliminarAcabado(acabado);
            nombreEliminar.setText("");
            factorEliminar.setText("");
            
            cargar();
        }
    }
    public void anyadirAcabado(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
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
                txtFactor.setText("");
                break;
            }else{
               lblErrorFactor.setText(""); 
            }
            }
            if(factor.trim().equals("")){
                 lblErrorFactor.setText("Debes rellenar el factor");
                error=true;
            }
           
            if(!nombre.equals("")){
             Acabado acabado=controlador.getAcabado(nombre);
            if(acabado!=null){
            lblErrorNombre.setText("Ese acabado ya existe");
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
           Acabado acabado=new Acabado(0, nombre, factor.toString());
                controlador.insertarAcabado(acabado);  
                lblErrorFactor.setText("");
                lblErrorNombre.setText("");
                txtFactor.setText("");
                  txtNombre.setText("");     
                  int answer = MessageBox.show(stage,
						"El acabado se ha creado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){

        stage = ObjetoCompartido.dameLo().getStage();
          
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
       
       listadoAcabado.setDisable(true);
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
        tableView.setMaxWidth(400);
        TableColumn Id=new TableColumn("Id");
        TableColumn Nombre=new TableColumn("Nombre");
        TableColumn Factor=new TableColumn("Factor");
        Id.setMinWidth(100);
        Factor.setMinWidth(100);
        Nombre.setMinWidth(200);
      Id.setCellValueFactory(new PropertyValueFactory<Acabado,String>("Id"));
      Nombre.setCellValueFactory(new PropertyValueFactory<Acabado,String>("Nombre"));
      Factor.setCellValueFactory(new PropertyValueFactory("Factor"));
      tableView.getColumns().addAll(Id,Nombre,Factor);
      tableView.setTableMenuButtonVisible(true);
      botonEliminar.setDisable(true);
      tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Acabado acabado= tableView.getSelectionModel().getSelectedItem();
                if(acabado!=null){
                    nombreEliminar.setText(acabado.getNombre());
                    factorEliminar.setText(acabado.getFactor());
                    botonEliminar.setDisable(false);
                }
            }
        });


    Nombre.setCellFactory(TextFieldTableCell.forTableColumn());
    Nombre.setOnEditCommit(
    new EventHandler<CellEditEvent<Acabado, String>>() {
        @Override
        public void handle(CellEditEvent<Acabado, String> t) {
               Controlador controlador = null;
               Acabado acabado=null;
               Stage stage=new Stage();
        try {
            controlador=controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                acabado=controlador.getAcabado(t.getNewValue());
            } catch (DAOExcepcion ex) {
                Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"El nombre del acabado no  puede estar vacio",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
            if(acabado==null && !t.getNewValue().trim().equals("")){
               ((Acabado) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setNombre(t.getNewValue());
               Acabado acabadoA=  ((Acabado) t.getTableView().getItems().get(
                t.getTablePosition().getRow()));
               acabadoA.setNombre(t.getNewValue().trim());
                   try {
                       controlador.modificarAcabado(acabadoA);
                   } catch (DAOExcepcion ex) {
                       Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               cargar();
            }
           
            if(acabado!= null && !t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"Ese producto ya existe, introduzca un nombre distinto",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
    
            }
      
        }
    }
);
    Factor.setCellFactory(TextFieldTableCell.forTableColumn());
    Factor.setOnEditCommit(
    new EventHandler<CellEditEvent<Acabado, String>>() {
        @Override
        public void handle(CellEditEvent<Acabado, String> t) {
            Controlador controlador=null;
            Acabado nuevoAcabado=null;
            Stage stage=new Stage();
            boolean hayLetras=false;
            try {
                controlador=controlador.dameControlador();
            } catch (DAOExcepcion ex) {
                Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DominioExcepcion ex) {
                Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             if(!t.getNewValue().trim().equals("")){
                for(char x: t.getNewValue().toCharArray()){
            if(Character.isLetter(x)){
               int answer = MessageBox.show(stage,
						"El factor no puede contener letras",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
               hayLetras=true; 
               cargar();
                break;
            }
                }
                if(!hayLetras){
               nuevoAcabado=((Acabado) t.getTableView().getItems().get(
                t.getTablePosition().getRow()));
               nuevoAcabado.setFactor(t.getNewValue().trim().replace(',', '.'));
                try {
                    controlador.modificarAcabado(nuevoAcabado);
                } catch (DAOExcepcion ex) {
                    Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
               ((Acabado) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setFactor(t.getNewValue());
               cargar();
                }
            }else{
               int answer = MessageBox.show(stage,
						"El factor no puede estar vacio",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();  
             }
        }
    }
);
  cargar();
    }
    
    public static void cargar(){
        
                    Controlador controlador = null;

  ArrayList<Acabado> acabados=new ArrayList<Acabado>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            acabados=controlador.getAcabados();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAcabadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
       ObservableList<Acabado> acabado = FXCollections.observableList(acabados);  
       tableView.setItems(acabado);
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
    
}