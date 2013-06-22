
package persistencia;

import logica.Acabado;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import logica.Aditivo;
import logica.Base;


public interface IAditivoDAO {
public Aditivo getAditivo(String nombre)throws DAOExcepcion;
public ArrayList<Aditivo> getAditivos() throws DAOExcepcion;
public ArrayList<Aditivo> getAditivos(int idb,double concentracion) throws DAOExcepcion ;
public ArrayList<Aditivo> getAditivosNoAsignados(int idb,double concentracion) throws DAOExcepcion ;
public void insertarAditivo(Aditivo aditivo) throws DAOExcepcion ;
public void modificarAditivoNombre(String nombreAntes,String nombreAhora) throws DAOExcepcion ;
public void eliminarAditivo(Aditivo aditivo) throws DAOExcepcion ;
public void asociarAditivo(Base base,Aditivo aditivo) throws DAOExcepcion;
public void eliminaAsociacionAditivo(Base base,Aditivo aditivo) throws DAOExcepcion;
}
