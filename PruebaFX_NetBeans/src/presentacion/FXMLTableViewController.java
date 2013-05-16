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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logica.Controlador;


public class FXMLTableViewController implements Initializable{
    @FXML private TableView<Person> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    
    @FXML
    protected void addPerson(ActionEvent event) {
        ObservableList<Person> data = tableView.getItems();
        data.add(new Person(Integer.parseInt(firstNameField.getText()),
            lastNameField.getText(),
            Double.parseDouble(emailField.getText())
        ));
        
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");   
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
                  Controlador controlador = null;
  ArrayList<logica.Producto> productos=new ArrayList<logica.Producto>();
        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(FXMLTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(FXMLTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            productos=controlador.getProductos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(FXMLTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  ArrayList<Person> productosTabla = new ArrayList<Person>();
                  for(int i=0;i<productos.size();i++){
                    System.out.println(productos.get(i).getId()+"  "+productos.get(i).getNombre()+"  "+productos.get(i).getFactor());
                     productosTabla.add(new Person(productos.get(i).getId(),productos.get(i).getNombre(),productos.get(i).getFactor())); 
                  
                  }
                   ObservableList<Person> producto = FXCollections.observableList(productosTabla);  
       tableView.setItems(producto);
    }
    
}