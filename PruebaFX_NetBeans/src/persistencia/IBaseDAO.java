/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.util.ArrayList;
import logica.Base;

/**
 *
 * @author roberto
 */
public interface IBaseDAO {
    public ArrayList<Base> getBases() throws DAOExcepcion;
    public void insertarBase(Base base) throws DAOExcepcion ;
    public void modificarBase(Base base) throws DAOExcepcion ;
    public void eliminarBase(String nombre) throws DAOExcepcion ;
}
