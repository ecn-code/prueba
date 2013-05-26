package logica;
import excepciones.DominioExcepcion;
import excepciones.DAOExcepcion;
import java.util.ArrayList;

import persistencia.DAL;


public class Controlador {
	private static Controlador controlador;
	private DAL dal;
	//**********************************************************************
	// Creaci�n del controlador
	// Un �nico controlador para todos los C.U.
	//**********************************************************************
	private Controlador() throws DominioExcepcion{
		try {
		// Objeto para comunicarse con la capa de acceso a datos
		this.dal = DAL.dameDAL();
		
		}catch (DAOExcepcion e){
			throw new DominioExcepcion(e.getMessage());
		}
	}
        
	public static Controlador dameControlador() throws DAOExcepcion, DominioExcepcion {
	if(controlador == null)
		controlador=new Controlador();
	return controlador;
	}
	
	public void insertarProducto(Producto producto) throws DAOExcepcion {
	dal.insertarProducto(producto);
}
public Producto getProducto(String _nombre) throws DAOExcepcion {
	return dal.getProducto(_nombre);
}
public ArrayList<Producto> getProductos() throws DAOExcepcion {
	return dal.getProductos();
}
public void eliminarProducto(Producto producto) throws DAOExcepcion {
	dal.eliminarProducto(producto);
}

    public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion {
        return dal.getPigmentos();
    }
public void insertarAcabado(Acabado acabado) throws DAOExcepcion {
	dal.insertarAcabado(acabado);
}
public Acabado getAcabado(String _nombre) throws DAOExcepcion {
	return dal.getAcabado(_nombre);
}
public ArrayList<Acabado> getAcabados() throws DAOExcepcion {
	return dal.getAcabados();
}
public void eliminarAcabado(Acabado acabado) throws DAOExcepcion {
	dal.eliminarAcabado(acabado);
}
    public ArrayList<String> getAditivos() throws DAOExcepcion{
    return dal.getAditivos();
    }
public ArrayList<Aditivo> getAditivos(int idb) throws DAOExcepcion{
return dal.getAditivos(idb);
}
public void insertarAditivoNombre(String nombre) throws DAOExcepcion{
dal.insertarAditivoNombre(nombre);
}
public void modificarAditivoNombre(String nombreAntes,String nombreAhora) throws DAOExcepcion{
dal.modificarAditivoNombre(nombreAntes, nombreAhora);
}
public void eliminarAditivoNombre(String nombre) throws DAOExcepcion{
dal.eliminarAditivoNombre(nombre);
}
public void asociarAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
dal.asociarAditivo(base, aditivo);
}
public void eliminaAsociacionAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
dal.eliminaAsociacionAditivo(base, aditivo);
}
}
