/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javafx.stage.Stage;

/**
 *
 * @author Elias
 */


public class ObjetoCompartido {
    private Stage stage;
    private static ObjetoCompartido yoMismo;
    
   public ObjetoCompartido(){
   }
   public static ObjetoCompartido dameLo(){
       if(yoMismo==null) yoMismo=new ObjetoCompartido();
       return yoMismo;
   }
   public void setStage(Stage _stage){stage=_stage;}
   public Stage getStage(){return stage;}
}
