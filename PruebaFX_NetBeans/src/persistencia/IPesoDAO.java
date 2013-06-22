package persistencia;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import logica.Peso;


public interface IPesoDAO {
public Peso getPeso() throws DAOExcepcion;
public void insertarPeso(Peso peso) throws DAOExcepcion ;
public void eliminarPeso(Peso peso) throws DAOExcepcion ;
public void modificarPeso(Peso peso) throws DAOExcepcion ;
}
