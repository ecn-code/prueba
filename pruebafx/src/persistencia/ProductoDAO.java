<<<<<<< HEAD
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Producto;


public class ProductoDAO implements IProductoDAO{
	private ConnectionManager connManager;
	public ProductoDAO() throws DAOExcepcion{
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}
	@Override
	public ArrayList<Producto> getProductos() throws DAOExcepcion{
		// TODO Auto-generated method stub
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PRODUCTO");
			connManager.close();
			try {
				
				while (rs.next()){
					Producto producto = new Producto(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getDouble("FACTOR"));
					productos.add(producto);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return productos;
	}

	@Override
	public Producto getProducto(String nombre) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PRODUCTO where NOMBRE= '"+nombre+"'");						
			connManager.close();
			try{
				if (rs.next()){
					return new Producto(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getDouble("FACTOR"));
				}
				else
					return null;
			}catch (SQLException e){
				throw new DAOExcepcion("DB_READ_ERROR");
			}
			
			}catch (DAOExcepcion e){
				throw e;
			}
	}

	@Override
	public void insertarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

}
=======
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Producto;


public class ProductoDAO implements IProductoDAO{
	private ConnectionManager connManager;
	public ProductoDAO() throws DAOExcepcion{
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}
	@Override
	public ArrayList<Producto> getProductos() throws DAOExcepcion{
		// TODO Auto-generated method stub
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PRODUCTO");
			connManager.close();
			try {
				
				while (rs.next()){
					Producto producto = new Producto(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getDouble("FACTOR"));
					productos.add(producto);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return productos;
	}

	@Override
	public Producto getProducto(String nombre) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PRODUCTO where NOMBRE= '"+nombre+"'");						
			connManager.close();
			try{
				if (rs.next()){
					return new Producto(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getDouble("FACTOR"));
				}
				else
					return null;
			}catch (SQLException e){
				throw new DAOExcepcion("DB_READ_ERROR");
			}
			
			}catch (DAOExcepcion e){
				throw e;
			}
	}

	@Override
	public void insertarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

}
>>>>>>> DAO PRODUCTO
