
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import persistencia.DAL;

public class FXMLTableView extends Application {


    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjetoCompartido.dameLo().setStage(primaryStage);
        primaryStage.setTitle("FXML TableView Example");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
        
    }
 
    public static void main(String[] args) {
        
          ThreadEjemplo ej = new ThreadEjemplo();
         ej.start();
      
      launch(args);     
     }
     public static class ThreadEjemplo extends Thread {
    public ThreadEjemplo() {
        super();
    }
    Runtime rt;
     Process pr = null;
    public void finaler(){
    rt.exit(0);
    pr.destroy();

    }
    
    public void run() {
       
         try {
                rt = Runtime.getRuntime();
                Process pr  =  rt.exec("C:\\Users\\Elias\\Documents\\GitHub\\prueba\\PruebaFX_NetBeans\\hsqldb\\bin\\runServer.bat CALCPIGMENTOS CALCPIGMENTOS");
                // pr= rt.exec("hsqldb\\bin\\runServer.bat CALCPIGMENTOS CALCPIGMENTOS");
 
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                String line=null;
                
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
  
                int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
 
            } catch(Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
    }
     }}
        
        
        

