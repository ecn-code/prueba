/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import logica.Aditivo;


/**
 *
 * @author Elias
 */
public class Prueba {

     public static void main(String[] args) throws DAOExcepcion, IOException {
         
        ThreadEjemplo ej = new ThreadEjemplo();
         ej.start();
      
         
        DAL dal = DAL.dameDAL();
        dal.insertarAditivoNombre("Aditivo2445");
        ArrayList<String> array = dal.getAditivos();
        
        for(String aditivo : array)
            System.out.println(aditivo);
        
        ej.finaler();
        ej.interrupt();
           
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
                //Process pr = rt.exec("cmd /c dir");
                 pr= rt.exec("c:\\hsqldb\\bin\\runServer.bat CALCPIGMENTOS CALCPIGMENTOS");
 
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
}
}
