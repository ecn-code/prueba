package persistencia;

import logica.Acabado;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import logica.Aditivo;
import logica.Base;


public interface IAditivoDAO {
public ArrayList<Aditivo> getAditivos() throws DAOExcepcion;
public ArrayList<Aditivo> getAditivos(int idb) throws DAOExcepcion ;
public void insertarAditivoNombre(String nombre) throws DAOExcepcion ;
public void modificarAditivoNombre(String nombre) throws DAOExcepcion ;
public void eliminarAditivoNombre(String nombre) throws DAOExcepcion ;
public void asociarAditivo(Base base,Aditivo aditivo) throws DAOExcepcion;
public void eliminaAsociacionAditivo(Base base,Aditivo aditivo) throws DAOExcepcion;
}
