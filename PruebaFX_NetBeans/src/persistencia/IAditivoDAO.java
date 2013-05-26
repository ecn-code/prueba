/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.util.ArrayList;

/**
 *
 * @author roberto
 */
public interface IAditivoDAO {
    
    public void añadirAditivo(String nombre)throws DAOExcepcion;
    public void eliminarAditivo(String nombre)throws DAOExcepcion;
    public ArrayList<String> getAditivos()throws DAOExcepcion;
    
}
