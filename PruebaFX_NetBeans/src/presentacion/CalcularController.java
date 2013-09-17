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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Color;
import logica.Pigmento;
import logica.Controlador;
import logica.ObjetoResultado;
import logica.Peso;
import logica.Pigmento;
import logica.Producto;
import logica.Resultado;
import static presentacion.ListadoProductosController.cargar;

public class CalcularController implements Initializable, ControlledScreen{
   ScreensController myController;
    @FXML private static TableView<Resultado> tableView;
    private String comprobarCantidad;
    private Double cantidad;
    Producto producto;
    Acabado acabado;
    Pigmento pigmento;
    @FXML private TextField txtCantidad;
    @FXML private ComboBox comboProducto,comboAcabado,comboPigmento;
    @FXML private Label lblErrorAcabado,lblErrorProducto,lblErrorCantidad,lblErrorPigmento;
      @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                    @FXML MenuItem listadoPeso;
                            @FXML MenuItem listadoBase;
                            @FXML MenuItem listadoProducto;
    @FXML MenuItem aditivoABase,concentracionABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
    @FXML Menu inicio;
    Stage stage;
    @FXML
    
    public void calcular(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
    ArrayList<ArrayList<Base>> basesYConcentraciones =new ArrayList<ArrayList<Base>>();
    ArrayList<Base> basesResultado=new ArrayList<Base>();
    boolean correcto=true;
    pigmento=(Pigmento)comboPigmento.getSelectionModel().getSelectedItem();
    producto=(Producto)comboProducto.getSelectionModel().getSelectedItem();
    acabado=(Acabado)comboAcabado.getSelectionModel().getSelectedItem();
    comprobarCantidad=txtCantidad.getText().trim().replace(',', '.');
        for(char x: comprobarCantidad.toCharArray()){
            if(Character.isLetter(x)){
                correcto=false;
                lblErrorCantidad.setText("No se pueden introducir letras");
                txtCantidad.setText("");
                break;
            }else{
               lblErrorCantidad.setText(""); 
            }
            } 
        
   if(comprobarCantidad.equals("")){
   lblErrorCantidad.setText("Introduce cantidad");
   correcto=false;
    }else{
    lblErrorCantidad.setText(""); 
   }
    if(pigmento==null){
        lblErrorPigmento.setText("Selecciona pigmento");
        correcto=false;
    }else{
        lblErrorPigmento.setText("");
    }
    if(acabado==null){
        lblErrorAcabado.setText("Selecciona acabado");
        correcto=false;
    }else{
        lblErrorAcabado.setText("");
    }
    if(producto==null){
        lblErrorProducto.setText("Selecciona producto");
        correcto=false;
    }else{
        lblErrorProducto.setText("");
    }
        
    if(correcto){
    cantidad=Double.parseDouble(comprobarCantidad);
    ArrayList<Color> bases=getBases(pigmento);
    for(int i=0;i<bases.size();i++){
        basesYConcentraciones.add(basesConConcentracion(bases.get(i)));
    }
    ArrayList<Resultado> resultadoParaTabla=new ArrayList<Resultado>();
    if(basesYConcentraciones.size()==1){
        resultadoParaTabla=combinacion1Bases(basesYConcentraciones,pigmento,acabado,producto,cantidad);
    }else if(basesYConcentraciones.size()==2){
        resultadoParaTabla=combinacion2Bases(basesYConcentraciones,pigmento,acabado,producto,cantidad);
    }else if(basesYConcentraciones.size()==3){
        resultadoParaTabla=combinacion3Bases(basesYConcentraciones,pigmento,acabado,producto,cantidad);
    }else if(basesYConcentraciones.size()==4){
       resultadoParaTabla=combinacion4Bases(basesYConcentraciones,pigmento,acabado,producto,cantidad);  
    }
    int m,n;
    Resultado[] resultados=new Resultado[resultadoParaTabla.size()];
    for(int i=0;i<resultadoParaTabla.size();i++){
        resultados[i]=resultadoParaTabla.get(i);
    }   
         Resultado aux;
    for (int i = 0; i < resultados.length - 1; i++) {
        for (int x = i + 1; x < resultados.length; x++) {
            if (Double.parseDouble(resultados[x].getTotal()) < Double.parseDouble(resultados[i].getTotal())) {
                aux = resultados[i];
                resultados[i] = resultados[x];
                resultados[x] = aux;
            }
        }
    }
          
    ArrayList<Resultado> resultadosOrdenados=new ArrayList<Resultado>();
    for(int j=0;j<resultados.length;j++)
        resultadosOrdenados.add(resultados[j]);
    
    ObservableList<Resultado> basesTabla = FXCollections.observableList(resultadosOrdenados);
     tableView.setItems(basesTabla);
    }
    }
    
    public Double calculo(Base _base,Pigmento _pigmento,Acabado _acabado,Producto _producto,Double _cantidad) throws DAOExcepcion, DominioExcepcion{
        Controlador controlador= Controlador.dameControlador();
        Color color=new Color(_base.getId(),"",0.0);
        double polvoBase=controlador.getPorcentaje(color, pigmento);
        Double factor=Double.parseDouble(_producto.getFactor())*Double.parseDouble(_acabado.getFactor())*polvoBase;
        Double factorAditivos=(100-_base.getConcentracion())/_base.getConcentracion();
        Double resultado100g=factor+factor*factorAditivos;
        Double resultadParaCantidadSeleccionada=(resultado100g*_cantidad)/0.1;
        return Math.round(resultadParaCantidadSeleccionada*100)/100d;
    }
 
     public ArrayList<Resultado> combinacion4Bases(ArrayList<ArrayList<Base>> bases,Pigmento _pigmento,Acabado _acabado,Producto _producto,Double _cantidad) throws DAOExcepcion, DominioExcepcion{
         Controlador controlador=Controlador.dameControlador();
       ArrayList<Resultado> combinacionFinal=new ArrayList<>();
       Peso peso= controlador.getPeso();
        String producto=_pigmento.getNombre()+"  "+_cantidad+" Kg  para  "+ _producto.getNombre()+"  acabado  al  "+_acabado.getNombre()+"";
       String nombre1=controlador.getColor(bases.get(0).get(0).getId()).getNombre();
       String nombre2=controlador.getColor(bases.get(1).get(0).getId()).getNombre();
       String nombre3=controlador.getColor(bases.get(2).get(0).getId()).getNombre();
       String nombre4=controlador.getColor(bases.get(3).get(0).getId()).getNombre();
      for(int i = 0;i<bases.get(0).size();i++){
           for(int j=0;j<bases.get(1).size();j++){
               for(int s=0;s<bases.get(2).size();s++){
                   for(int t=0;t<bases.get(3).size();t++){
               double cantidad1=calculo(bases.get(0).get(i), _pigmento, _acabado, _producto, _cantidad);
               double cantidad2=calculo(bases.get(1).get(j), _pigmento, _acabado, _producto, _cantidad);
               double cantidad3=calculo(bases.get(2).get(s), _pigmento, _acabado, _producto, _cantidad);
               double cantidad4=calculo(bases.get(3).get(t), _pigmento, _acabado, _producto, _cantidad);
               double total=Math.round((cantidad1+cantidad2+cantidad3+cantidad4)*100)/100d;
               ArrayList<Base> combinacionSimple=new ArrayList<Base>();
                    if(total>peso.getPesoMin() && total<peso.getPesoMax()){
                   String nombre1fin=nombre1+" "+bases.get(0).get(i).getConcentracion().toString()+"%";
                   String nombre2fin=nombre2+" "+bases.get(1).get(j).getConcentracion().toString()+"%";
                   String nombre3fin=nombre3+" "+bases.get(2).get(s).getConcentracion().toString()+"%";
                   String nombre4fin=nombre4+" "+bases.get(3).get(t).getConcentracion().toString()+"%";
                   String cantidad1Tabla=cantidad1+"";
                   String cantidad2Tabla=cantidad2+"";
                   String cantidad3Tabla=cantidad3+"";
                   String cantidad4Tabla=cantidad4+"";
                   String totalTabla=total+"";
                   Resultado resultado=new Resultado(nombre1fin, cantidad1Tabla);
                   resultado.setBase2(nombre2fin);
                   resultado.setCant2(cantidad2Tabla);
                   resultado.setBase3(nombre3fin);
                   resultado.setCant3(cantidad3Tabla);
                   resultado.setBase4(nombre4fin);
                   resultado.setCant4(cantidad4Tabla);
                   resultado.setTotal(totalTabla);
                   resultado.setProductoSeleccionado(producto);
                   combinacionFinal.add(resultado);
               }
                   }
               }
           }
       }
      return combinacionFinal;
    }
    
       public ArrayList<Resultado> combinacion3Bases(ArrayList<ArrayList<Base>> bases,Pigmento _pigmento,Acabado _acabado,Producto _producto,Double _cantidad) throws DAOExcepcion, DominioExcepcion{
       Controlador controlador=Controlador.dameControlador();
       ArrayList<Resultado> combinacionFinal=new ArrayList<>();
       Peso peso= controlador.getPeso();
       String nombre1=controlador.getColor(bases.get(0).get(0).getId()).getNombre();
       String nombre2=controlador.getColor(bases.get(1).get(0).getId()).getNombre();
       String nombre3=controlador.getColor(bases.get(2).get(0).getId()).getNombre();
      String producto=_pigmento.getNombre()+"  "+_cantidad+" Kg  para  "+ _producto.getNombre()+"  acabado  al  "+_acabado.getNombre()+"";
      for(int i = 0;i<bases.get(0).size();i++){
           for(int j=0;j<bases.get(1).size();j++){
               for(int s=0;s<bases.get(2).size();s++){
               double cantidad1=calculo(bases.get(0).get(i), _pigmento, _acabado, _producto, _cantidad);
               double cantidad2=calculo(bases.get(1).get(j), _pigmento, _acabado, _producto, _cantidad);
               double cantidad3=calculo(bases.get(2).get(s), _pigmento, _acabado, _producto, _cantidad);
               double total=Math.round((cantidad1+cantidad2+cantidad3)*100)/100d;
               ArrayList<Base> combinacionSimple=new ArrayList<Base>();
                if(total>peso.getPesoMin() && total<peso.getPesoMax()){
                   String nombre1fin=nombre1+" "+bases.get(0).get(i).getConcentracion().toString()+"%";
                   String nombre2fin=nombre2+" "+bases.get(1).get(j).getConcentracion().toString()+"%";
                   String nombre3fin=nombre3+" "+bases.get(2).get(s).getConcentracion().toString()+"%";
                   String cantidad1Tabla=cantidad1+"";
                   String cantidad2Tabla=cantidad2+"";
                   String cantidad3Tabla=cantidad3+"";
                   String totalTabla=total+"";
                   Resultado resultado=new Resultado(nombre1fin, cantidad1Tabla);
                   resultado.setBase2(nombre2fin);
                   resultado.setCant2(cantidad2Tabla);
                   resultado.setBase3(nombre3fin);
                   resultado.setCant3(cantidad3Tabla);
                   resultado.setTotal(totalTabla);
                   resultado.setProductoSeleccionado(producto);
                   combinacionFinal.add(resultado);          
               }
               }
           }
       }
      return combinacionFinal;
    }
       
    public ArrayList<Resultado> combinacion2Bases(ArrayList<ArrayList<Base>> bases,Pigmento _pigmento,Acabado _acabado,Producto _producto,Double _cantidad) throws DAOExcepcion, DominioExcepcion{
        Controlador controlador=Controlador.dameControlador();
       ArrayList<Resultado> combinacionFinal=new ArrayList<>();
       Peso peso= controlador.getPeso();
       String nombre1=controlador.getColor(bases.get(0).get(0).getId()).getNombre();
       String nombre2=controlador.getColor(bases.get(1).get(0).getId()).getNombre();
      String producto=_pigmento.getNombre()+"  "+_cantidad+" Kg  para  "+ _producto.getNombre()+"  acabado  al  "+_acabado.getNombre()+"";
      for(int i = 0;i<bases.get(0).size();i++){
           for(int j=0;j<bases.get(1).size();j++){
               double cantidad1=calculo(bases.get(0).get(i), _pigmento, _acabado, _producto, _cantidad);
               double cantidad2=calculo(bases.get(1).get(j), _pigmento, _acabado, _producto, _cantidad);
               double total=Math.round((cantidad1+cantidad2)*100)/100d;
               String cantidad1Tabla=cantidad1+"";
                   String cantidad2Tabla=cantidad2+"";
                   String totalTabla=total+"";
               if(total>peso.getPesoMin() && total<peso.getPesoMax()){
                   String nombre1fin=nombre1+" "+bases.get(0).get(i).getConcentracion().toString()+"%";
                   String nombre2fin=nombre2+" "+bases.get(1).get(j).getConcentracion().toString()+"%";
                   Resultado resultado=new Resultado(nombre1fin, cantidad1Tabla);
                   resultado.setBase2(nombre2fin);
                   resultado.setCant2(cantidad2Tabla);
                   resultado.setTotal(totalTabla);
                   resultado.setProductoSeleccionado(producto);
                   combinacionFinal.add(resultado);
               }
           }
       }
      return combinacionFinal;
    }
     public ArrayList<Resultado> combinacion1Bases(ArrayList<ArrayList<Base>> bases,Pigmento _pigmento,Acabado _acabado,Producto _producto,Double _cantidad) throws DAOExcepcion, DominioExcepcion{
       Controlador controlador=Controlador.dameControlador();
       Peso peso= controlador.getPeso();
       ArrayList<Resultado> combinacionFinal=new ArrayList<>();
       String nombre1=controlador.getColor(bases.get(0).get(0).getId()).getNombre();
       String producto=_pigmento.getNombre()+"  "+_cantidad+" Kg  para  "+ _producto.getNombre()+"  acabado  al  "+_acabado.getNombre()+"";
      for(int i = 0;i<bases.get(0).size();i++){
      double cantidad1=calculo(bases.get(0).get(i), _pigmento, _acabado, _producto, _cantidad);
      cantidad1=Math.round(cantidad1*100)/100d;
        String cantidad1Tabla=cantidad1+"";
        
      if(cantidad1>peso.getPesoMin() && cantidad1<peso.getPesoMax()){
                   String nombre1fin=nombre1+" "+bases.get(0).get(i).getConcentracion().toString()+"%";
                   Resultado resultado=new Resultado(nombre1fin, cantidad1Tabla);
                   resultado.setTotal(cantidad1Tabla);
                   resultado.setProductoSeleccionado(producto);
                   combinacionFinal.add(resultado);
               }
               
       }
      return combinacionFinal;
    }
    
   public ArrayList<Color> getBases(Pigmento _pigmento) throws DAOExcepcion, DominioExcepcion{
       Controlador controlador=Controlador.dameControlador();
       ArrayList<Color> colores=controlador.getColores(_pigmento.getId());
       return colores;
   }
   public ArrayList<Base> basesConConcentracion(Color color) throws DAOExcepcion, DominioExcepcion{
       Controlador controlador=Controlador.dameControlador();
       ArrayList<Base> bases=controlador.getBases(color.getId());
       return bases;
   }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
        stage = ObjetoCompartido.dameLo().getStage();
        
        txtCantidad.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                {
                    try {
                        calcular(null);
                    } catch (DAOExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DominioExcepcion ex) {
                        Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
          tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getClickCount() > 1){
                Resultado resultado= (Resultado)tableView.getSelectionModel().getSelectedItem();
              /*  if(resultado!=null){
                   
                    btnElegir.setDisable(false);
                }else{
                  btnElegir.setDisable(true);  
                }*/
                 ObjetoResultado objetoResultado=ObjetoResultado.dameObjetoResultado();
       // Resultado resultado=tableView.getSelectionModel().getSelectedItem();
         objetoResultado.setResultado(resultado);
          Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("Resultado.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Pigmentos");
        stage.setScene(new Scene(root));
        stage.show();
            }
            }  
        });
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
       calcular.setDisable(true);
       listadoPeso.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoPesos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pesos");
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
                   root = FXMLLoader.load(getClass().getResource("AnyadirAditivosABase.fxml"));
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
        inicio.hide();
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
            
         tableView.setEditable(true);
        tableView.setMaxWidth(800);
        TableColumn Base1=new TableColumn("Base1");
        TableColumn Cant1=new TableColumn("Cant1");
        TableColumn Base2=new TableColumn("Base2");
        TableColumn Cant2=new TableColumn("Cant2");
        TableColumn Base3=new TableColumn("Base3");
        TableColumn Cant3=new TableColumn("Cant3");
        TableColumn Base4=new TableColumn("Base4");
        TableColumn Cant4=new TableColumn("Cant4");
        TableColumn total=new TableColumn("Total");
        total.setStyle("columna-total");
        Cant1.setMinWidth(30);
        Base1.setMinWidth(100);
        Cant2.setMinWidth(30);
        Base2.setMinWidth(100);
        Cant3.setMinWidth(30);
        Base3.setMinWidth(100);
        Cant4.setMinWidth(30);
        Base4.setMinWidth(100);
        total.setMinWidth(30);
      Base1.setCellValueFactory(new PropertyValueFactory<Base,String>("Base1"));
      Cant1.setCellValueFactory(new PropertyValueFactory("Cant1"));
      Base2.setCellValueFactory(new PropertyValueFactory<Base,String>("Base2"));
      Cant2.setCellValueFactory(new PropertyValueFactory("Cant2"));
      Base3.setCellValueFactory(new PropertyValueFactory<Base,String>("Base3"));
      Cant3.setCellValueFactory(new PropertyValueFactory("Cant3"));
      Base4.setCellValueFactory(new PropertyValueFactory<Base,String>("Base4"));
      Cant4.setCellValueFactory(new PropertyValueFactory("Cant4"));
      total.setCellValueFactory(new PropertyValueFactory("Total"));
      tableView.getColumns().addAll(Base1,Cant1,Base2,Cant2,Base3,Cant3,Base4,Cant4,total);
       try {
           cargarPigmentos();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           cargarAcabados();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           cargarProductos();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
        }
    
    
    public void cargarPigmentos() throws DAOExcepcion{
        
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
          ObservableList<Pigmento> pigmento = FXCollections.observableList(pigmentos);
          comboPigmento.setItems(pigmento);
    }
     public void cargarAcabados() throws DAOExcepcion{
        
                    Controlador controlador = null;

  ArrayList<Acabado> acabados=new ArrayList<Acabado>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            acabados=controlador.getAcabados();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Acabado> acabado = FXCollections.observableList(acabados);
          comboAcabado.setItems(acabado);
    }
      public void cargarProductos() throws DAOExcepcion{
        
                    Controlador controlador = null;

  ArrayList<Producto> productos=new ArrayList<Producto>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            productos=controlador.getProductos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Producto> producto = FXCollections.observableList(productos);
          comboProducto.setItems(producto);
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
  
}