package persistencia;

import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Producto;

public interface IProductoDAO {
public ArrayList<Producto> getProductos() throws DAOExcepcion ;
public Producto getProducto(String nombre) throws DAOExcepcion ;
public void insertarProducto(Producto producto) throws DAOExcepcion ;
public void modificarProducto(Producto producto) throws DAOExcepcion ;
public void eliminarProducto(Producto producto) throws DAOExcepcion ;
}
