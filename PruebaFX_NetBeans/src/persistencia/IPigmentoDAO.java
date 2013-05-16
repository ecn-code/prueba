package persistencia;

import logica.Pigmento;
import java.util.ArrayList;

import excepciones.DAOExcepcion;



public interface IPigmentoDAO {
public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion ;
public Pigmento getPigmento(String nombre) throws DAOExcepcion ;
public void insertarPigmento(Pigmento pigmento) throws DAOExcepcion ;
public void modificarPigmento(Pigmento pigmento) throws DAOExcepcion ;
public void eliminarPigmento(Pigmento pigmento) throws DAOExcepcion ;
}
