package persistencia;



import logica.Producto;
import excepciones.*;
import java.util.ArrayList;
import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Pigmento;
import logica.Producto;

public class DAL {
	private static DAL dal;
	IProductoDAO iproductoDAO;
        IPigmentoDAO ipigmentoDAO;
	IAcabadoDAO iacabadoDAO;
        IAditivoDAO iaditivoDAO;
        IBaseDAO ibaseDAO;
private DAL() throws DAOExcepcion{
	// Objectos para comunicarse con la capa de acceso a datos
	this.iproductoDAO = new ProductoDAO();
        this.ipigmentoDAO = new PigmentoDAO();
        this.iacabadoDAO=new AcabadoDAO();
        this.iaditivoDAO=new AditivoDAO();
        this.ibaseDAO= new BaseDAO();
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
public void modificarProducto(Producto producto) throws DAOExcepcion {
	iproductoDAO.modificarProducto(producto);
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
public void modificarAcabado(Acabado acabado) throws DAOExcepcion {
	iacabadoDAO.modificarAcabado(acabado);
}

    public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion {
        return ipigmentoDAO.getPigmentos();
    }
     public void eliminarPigmento(Pigmento pigmento) throws DAOExcepcion {
        ipigmentoDAO.eliminarPigmento(pigmento);
    }
     public void insertarPigmento(Pigmento pigmento) throws DAOExcepcion {
        ipigmentoDAO.insertarPigmento(pigmento);
    }
      public Pigmento getPigmento(String nombre) throws DAOExcepcion {
        return ipigmentoDAO.getPigmento(nombre);
    }
    public ArrayList<Aditivo> getAditivos() throws DAOExcepcion{
    return iaditivoDAO.getAditivos();
    }
    public Aditivo getAditivo(String nombre) throws DAOExcepcion{
    return iaditivoDAO.getAditivo(nombre);
    }
public ArrayList<Aditivo> getAditivos(int idb) throws DAOExcepcion{
return iaditivoDAO.getAditivos(idb);
}
public void insertarAditivo(Aditivo aditivo) throws DAOExcepcion{
iaditivoDAO.insertarAditivo(aditivo);
}
public void modificarAditivoNombre(String nombreAntes,String nombreAhora) throws DAOExcepcion{
iaditivoDAO.modificarAditivoNombre(nombreAntes, nombreAhora);
}
public void eliminarAditivo(Aditivo aditivo) throws DAOExcepcion{
iaditivoDAO.eliminarAditivo(aditivo);
}
public void asociarAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
iaditivoDAO.asociarAditivo(base, aditivo);
}
public void eliminaAsociacionAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
iaditivoDAO.eliminaAsociacionAditivo(base, aditivo);
}
public ArrayList<Base> getBases() throws DAOExcepcion{
   return ibaseDAO.getBases();
}
public ArrayList<Base> getBases(int idp) throws DAOExcepcion{
    return ibaseDAO.getBases(idp);
}
public ArrayList<Base> getBasesNoAsignadas(int idp) throws DAOExcepcion{
    return ibaseDAO.getBasesNoAsignadas(idp);
}
public Base getBase(int idb) throws DAOExcepcion{
    return ibaseDAO.getBase(idb);
}
public Base getBase(String nombre) throws DAOExcepcion{
    return ibaseDAO.getBase(nombre);
}
public void insertarBase(Base base) throws DAOExcepcion{
    ibaseDAO.insertarBase(base);
}
public void asociarBasePigmento(Pigmento pigmento,Base base,Double cantidad) throws DAOExcepcion{
    ibaseDAO.asociarBasePigmento(pigmento, base, cantidad);
}
public void modificarBase(Base base) throws DAOExcepcion{
    ibaseDAO.modificarBase(base);
}
public void modificarPigmento(Pigmento pigmento) throws DAOExcepcion{
    ipigmentoDAO.modificarPigmento(pigmento);
}
public void eliminarBase(Base base) throws DAOExcepcion{
    ibaseDAO.eliminarBase(base);
}

    public ArrayList<Aditivo> getAditivosNoAsociados(int idb) throws DAOExcepcion {
       return iaditivoDAO.getAditivosNoAsignados(idb);
    }

    public void eliminaBasePigmento(Base base, Pigmento pigmento) {
        ibaseDAO.eliminarBasePigmento(base,pigmento);
    }
    public Double getPorcentaje(Base base, Pigmento pigmento) throws DAOExcepcion {
       return ibaseDAO.getPorcentaje(base, pigmento);
    }
}