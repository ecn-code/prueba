<<<<<<< HEAD
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
=======
package persistencia;

import logica.Acabado;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import logica.Aditivo;
import logica.Base;


public interface IBaseDAO {
public ArrayList<Base> getBases() throws DAOExcepcion;
public Base getBase(int idb) throws DAOExcepcion ;
public void insertarBase(Base base) throws DAOExcepcion ;
public void modificarBase(Base base) throws DAOExcepcion ;
public void eliminarBase(Base base) throws DAOExcepcion ;
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
}
