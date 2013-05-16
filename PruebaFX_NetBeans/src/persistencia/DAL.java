package persistencia;



import logica.Producto;
import excepciones.*;
import java.util.ArrayList;
import logica.Pigmento;
import logica.Producto;

public class DAL {
	private static DAL dal;
	IProductoDAO iproductoDAO;
        IPigmentoDAO ipigmentoDAO;
	
private DAL() throws DAOExcepcion{
	// Objectos para comunicarse con la capa de acceso a datos
	this.iproductoDAO = new ProductoDAO();
        this.ipigmentoDAO = new PigmentoDAO();
}

public static DAL dameDAL() throws DAOExcepcion {
	if(dal == null)
		dal = new DAL();
	return dal;
	}
//metodos de incidencia
public void insertarProducto(Producto producto) throws DAOExcepcion {
	iproductoDAO.insertarProducto(producto);
}
public Producto getProducto(String _nombre) throws DAOExcepcion {
	return iproductoDAO.getProducto(_nombre);
}
public ArrayList<Producto> getProductos() throws DAOExcepcion {
	return iproductoDAO.getProductos();
}
public void eliminarProducto(Producto producto) throws DAOExcepcion {
	iproductoDAO.eliminarProducto(producto);
}

    public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion {
        return ipigmentoDAO.getPigmentos();
    }
}