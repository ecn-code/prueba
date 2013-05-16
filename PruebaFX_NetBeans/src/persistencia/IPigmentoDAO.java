package persistencia;

import logica.Pigmento;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Producto;

public interface IPigmentoDAO {
public ArrayList<Pigmento> getProductos() throws DAOExcepcion ;
public Producto getProducto(String nombre) throws DAOExcepcion ;
public void insertarPigmento(Pigmento pigmento) throws DAOExcepcion ;
public void modificarProducto(Pigmento producto) throws DAOExcepcion ;
public void eliminarProducto(Pigmento producto) throws DAOExcepcion ;
}
