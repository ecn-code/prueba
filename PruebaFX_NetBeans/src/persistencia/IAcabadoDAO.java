package persistencia;

import logica.Acabado;
import java.util.ArrayList;

import excepciones.DAOExcepcion;


public interface IAcabadoDAO {
public ArrayList<Acabado> getAcabados() throws DAOExcepcion ;
public Acabado getAcabado(String nombre) throws DAOExcepcion ;
public void insertarAcabado(Acabado acabado) throws DAOExcepcion ;
public void modificarAcabado(Acabado acabado) throws DAOExcepcion ;
public void eliminarAcabado(Acabado acabado) throws DAOExcepcion ;
}
