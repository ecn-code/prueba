package persistencia;



import logica.Producto;
import excepciones.*;
import java.util.ArrayList;
import logica.Acabado;
import logica.Pigmento;
import logica.Producto;

public class DAL {
	private static DAL dal;
	IProductoDAO iproductoDAO;
        IPigmentoDAO ipigmentoDAO;
	IAcabadoDAO iacabadoDAO;
        IAditivoDAO iaditivoDAO;
private DAL() throws DAOExcepcion{
	// Objectos para comunicarse con la capa de acceso a datos
	this.iproductoDAO = new ProductoDAO();
        this.ipigmentoDAO = new PigmentoDAO();
        this.iacabadoDAO=new AcabadoDAO();
        this.iaditivoDAO=new AditivoDAO();
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
public void insertarAcabado(Acabado acabado) throws DAOExcepcion {
	iacabadoDAO.insertarAcabado(acabado);
}
public Acabado getAcabado(String _nombre) throws DAOExcepcion {
	return iacabadoDAO.getAcabado(_nombre);
}
public ArrayList<Acabado> getAcabados() throws DAOExcepcion {
            return iacabadoDAO.getAcabados();
}
public void eliminarAcabado(Acabado acabado) throws DAOExcepcion {
	iacabadoDAO.eliminarAcabado(acabado);
}

    public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion {
        return ipigmentoDAO.getPigmentos();
    }
    public void insertarAditivo(String nombre) throws DAOExcepcion {
	iaditivoDAO.añadirAditivo(nombre);
    }
    public void eliminarAditivo(String nombre) throws DAOExcepcion {
	iaditivoDAO.eliminarAditivo(nombre);
    }
    public ArrayList<String> getAditivos() throws DAOExcepcion {
            return iaditivoDAO.getAditivos();
}
}