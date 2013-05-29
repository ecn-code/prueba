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
}
