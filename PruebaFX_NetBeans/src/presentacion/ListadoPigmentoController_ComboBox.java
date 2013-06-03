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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Pigmento;
import logica.Controlador;
import logica.Pigmento;


public class ListadoPigmentoController_ComboBox implements Initializable, ControlledScreen{
   ScreensController myController;
    @FXML private static TableView<Base> tableView;
   private String cantidad;
    private String nombre;
    @FXML
    private TextField txtCantidad,baseEliminar,cantidadEliminar;
    @FXML
    private Label lblErrorCantidad,lblErrorBase,lblErrorPigmento;
    @FXML private ComboBox elegirPigmento;
    @FXML private ComboBox        elegirBase;
    @FXML private Button btnEliminar;
    @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                            @FXML MenuItem listadoBase;
                            @FXML MenuItem listadoProducto;
    @FXML MenuItem aditivoABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
    @FXML Menu inicio;
    Stage stage;
    Pigmento pigmento;
    Base base;
   @FXML
    public void eliminarPigmento(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
        Controlador controlador= Controlador.dameControlador();
       pigmento=(Pigmento) elegirPigmento.getSelectionModel().getSelectedItem();
       base=tableView.getSelectionModel().getSelectedItem();
       int answer= MessageBox.show(stage,
        "Estas seguro de que quieres eliminar este acabado?",
        "Information dialog",
        MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
        System.out.println(answer);
        if(answer==65536){
            controlador.eliminaBasePigmento(base, pigmento);
            cargarBases(pigmento.getId());
            baseEliminar.setText("");
            cantidadEliminar.setText("");
            btnEliminar.setDisable(true);
        }
        }

    public void anyadirPigmento(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
         Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       cantidad=txtCantidad.getText().trim().replace(',', '.');
       pigmento=controlador.getPigmento(elegirPigmento.getSelectionModel().getSelectedItem().toString());
       base=(Base)elegirBase.getSelectionModel().getSelectedItem();
       if(base==null){
           error=true;
           lblErrorBase.setText("Selecciona una Base");
       }else{
          lblErrorBase.setText("");
       }
        if(pigmento==null){
           error=true;
           lblErrorPigmento.setText("Selecciona un Pigmento");
       }else{
          lblErrorPigmento.setText("");
       }
     if(cantidad.equals("")){
   lblErrorCantidad.setText("Introduce cantidad");
   error=true;
    }else{
    lblErrorCantidad.setText(""); 
   }
       for(char x: cantidad.toCharArray()){
            if(Character.isLetter(x)){
                error=true;
                lblErrorCantidad.setText("No se pueden introducir letras");
                txtCantidad.setText("");
                break;
            }else{
               lblErrorCantidad.setText(""); 
            }
            } 
        
  
            if(!error){
         controlador.asociarBasePigmento(pigmento,base, Double.parseDouble(cantidad));
           cargarBases(pigmento.getId());
           txtCantidad.setText("");
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
        btnEliminar.setDisable(true);
      tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Base base= tableView.getSelectionModel().getSelectedItem();
                if(base!=null){
                    baseEliminar.setText(base.getNombre());
                    cantidadEliminar.setText(base.getPorcentaje()+"");
                    btnEliminar.setDisable(false);
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
       
       baseAPigmento.setDisable(true);
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
       aditivoABase.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoBases_ComboBox.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pigmento a Base");
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
        
       
        
       elegirPigmento.getSelectionModel().selectedIndexProperty().addListener(new
            ChangeListener<Number>() {
                public void changed(ObservableValue ov,
                    Number value, Number new_value) {
                    try {
                        //  label.setText(greetings[new_value.intValue()]);
                      cargarBases(((Pigmento)elegirPigmento.getItems().get(new_value.intValue())).getId());
                    } catch (DAOExcepcion ex) {
                        Logger.getLogger(ListadoBasesController_ComboBox.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });

       elegirPigmento.setItems(null);
        tableView.setEditable(true);
        tableView.setMaxWidth(520);
        TableColumn Id=new TableColumn("Id");
        TableColumn Nombre=new TableColumn("Nombre");
        TableColumn Porcentaje=new TableColumn("Porcentaje");
        Id.setMinWidth(100);
        Nombre.setMinWidth(270);
        Porcentaje.setMinWidth(150);
      Id.setCellValueFactory(new PropertyValueFactory<Base,String>("Id"));
      Nombre.setCellValueFactory(new PropertyValueFactory<Base,String>("Nombre"));
      Porcentaje.setCellValueFactory(new PropertyValueFactory<Base,String>("Porcentaje"));
      tableView.getColumns().addAll(Id,Nombre,Porcentaje);
      cargarPigmentos(); 
   // Nombre.setCellFactory(TextFieldTableCell.forTableColumn());
   /* Nombre.setOnEditCommit(
    new EventHandler<CellEditEvent<Pigmento, String>>() {
        @Override
        public void handle(CellEditEvent<Pigmento, String> t) {
               Controlador controlador = null;
               Pigmento pigmento=null;
               Stage stage=new Stage();
        try {
            controlador=controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoPigmentosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoPigmentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                pigmento=controlador.getPigmento(t.getNewValue());
            } catch (DAOExcepcion ex) {
                Logger.getLogger(ListadoPigmentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(ListadoPigmentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"El nombre del pigmento no  puede estar vacio",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
            if(pigmento==null && !t.getNewValue().trim().equals("")){
               ((Pigmento) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setNombre(t.getNewValue());
               Pigmento pigmentoA=  ((Pigmento) t.getTableView().getItems().get(
                t.getTablePosition().getRow()));
               pigmentoA.setNombre(t.getNewValue().trim());
                   try {
                       controlador.modificarPigmento(pigmentoA);
                   } catch (DAOExcepcion ex) {
                       Logger.getLogger(ListadoPigmentosController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               cargar();
            }
           
            if(pigmento!= null && !t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"Ese pigmento ya existe, introduzca un nombre distinto",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
    
            }
      
        }
    }
);*/
    
        }
    
    
    public void cargarBases(int idp) throws DAOExcepcion{
        System.out.println(idp);
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
            bases=controlador.getBases(idp);
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
      System.out.println(bases.size());    
       ObservableList<Base> basesConvertidosParaTabla = FXCollections.observableList(bases);  
       
      
       
       ArrayList<Base> basesChoiceBoxx = controlador.getBasesNoAsignadas(idp);
       
     /*  ObservableList<Base> basesOVChoiceBox = null;
       if(bases.size()>0){
       for(Base baseOV : aditivosTodos)
           for(Base baseOV2 : bases)
           if(!baseOV.getNombre().equals(baseOV2.getNombre()))basesChoiceBoxx.add(baseOV);
       
       basesOVChoiceBox = FXCollections.observableList(basesChoiceBoxx); 
       }else{
          
       basesOVChoiceBox = FXCollections.observableList(aditivosTodos); 
       }
       
       */
       ObservableList<Base> basesOVChoiceBox = FXCollections.observableList(basesChoiceBoxx); 
       elegirBase.setItems(basesOVChoiceBox);
       tableView.setItems(basesConvertidosParaTabla);
    }
    
    public  void cargarPigmentos(){
        
                    Controlador controlador = null;

  ArrayList<Pigmento> pigmentos=new ArrayList<Pigmento>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pigmentos=controlador.getPigmentos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
       ObservableList<Pigmento> pigmentos2 = FXCollections.observableList(pigmentos);  
       elegirPigmento.setItems(pigmentos2);
 
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
  
}