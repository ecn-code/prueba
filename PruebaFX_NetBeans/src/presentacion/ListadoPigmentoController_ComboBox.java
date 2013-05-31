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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
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
    private TextField txtCantidad;
    @FXML
    private Label lblErrorNombre;
    @FXML private ChoiceBox elegirPigmento,elegirBase;
    Pigmento pigmento;
    Base base;
   @FXML
    public void eliminarPigmento(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
        Controlador controlador= Controlador.dameControlador();
       pigmento=(Pigmento) elegirPigmento.getSelectionModel().getSelectedItem();
       base=tableView.getSelectionModel().getSelectedItem();
       controlador.eliminaBasePigmento(base, pigmento);
       cargarBases(pigmento.getId());
    
        }

    public void anyadirPigmento(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
         Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       cantidad=txtCantidad.getText();
       pigmento=controlador.getPigmento(elegirPigmento.getSelectionModel().getSelectedItem().toString());
       base=(Base)elegirBase.getSelectionModel().getSelectedItem();
    if(cantidad.equals(""))
             error=true;
            if(!error){
         controlador.asociarBasePigmento(pigmento,base, Double.parseDouble(cantidad));
           cargarBases(pigmento.getId());
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
        
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
        tableView.setMaxWidth(400);
        TableColumn Id=new TableColumn("Id");
        TableColumn Nombre=new TableColumn("Nombre");
        TableColumn Porcentaje=new TableColumn("Porcentaje");
        Id.setMinWidth(100);
        Nombre.setMinWidth(100);
        Porcentaje.setMinWidth(100);
      Id.setCellValueFactory(new PropertyValueFactory<Base,String>("Id"));
      Nombre.setCellValueFactory(new PropertyValueFactory<Base,String>("Nombre"));
      Porcentaje.setCellValueFactory(new PropertyValueFactory<Base,String>("Porcentaje"));
      tableView.getColumns().addAll(Id,Nombre,Porcentaje);
      tableView.setTableMenuButtonVisible(true);
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