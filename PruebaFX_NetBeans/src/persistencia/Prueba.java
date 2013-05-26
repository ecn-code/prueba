/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.util.ArrayList;
import logica.Aditivo;


/**
 *
 * @author Elias
 */
public class Prueba {
     public static void main(String[] args) throws DAOExcepcion {
        DAL dal = DAL.dameDAL();
        dal.insertarAditivoNombre("Aditivo2");
        ArrayList<Aditivo> array = dal.getAditivos();
        
        for(Aditivo aditivo : array)
            System.out.println(aditivo.getNombre());
    }
}
