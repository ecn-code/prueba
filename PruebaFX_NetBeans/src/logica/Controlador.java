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
        public void modificarProducto(Producto producto) throws DAOExcepcion {
	dal.modificarProducto(producto);
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
        public Pigmento getPigmento(String nombre) throws DAOExcepcion {
        return dal.getPigmento(nombre);
        }
         public void insertarPigmento(Pigmento pigmento) throws DAOExcepcion {
         dal.insertarPigmento(pigmento);
        }
          public void eliminarPigmento(Pigmento pigmento) throws DAOExcepcion {
         dal.eliminarPigmento(pigmento);
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
        public void modificarAcabado(Acabado acabado) throws DAOExcepcion {
	dal.modificarAcabado(acabado);
        }
        public ArrayList<Aditivo> getAditivos() throws DAOExcepcion{
        return dal.getAditivos();
        }
        public ArrayList<Aditivo> getAditivos(int idb) throws DAOExcepcion{
        return dal.getAditivos(idb);
        }
        public void insertarAditivo(Aditivo aditivo) throws DAOExcepcion{
        dal.insertarAditivo(aditivo);
        }
        public void modificarAditivoNombre(String nombreAntes,String nombreAhora) throws DAOExcepcion{
        dal.modificarAditivoNombre(nombreAntes, nombreAhora);
        }
        public void eliminarAditivo(Aditivo aditivo) throws DAOExcepcion{
        dal.eliminarAditivo(aditivo);
        }
        public Aditivo getAditivo(String nombre) throws DAOExcepcion{
        return dal.getAditivo(nombre);
        }
        public void asociarAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
        dal.asociarAditivo(base, aditivo);
        }
        public void eliminaAsociacionAditivo(Base base,Aditivo aditivo) throws DAOExcepcion{
        dal.eliminaAsociacionAditivo(base, aditivo);
        }
        public void insertarBase(Base base) throws DAOExcepcion{
        dal.insertarBase(base);
        }
        public void modificarBase(Base base) throws DAOExcepcion{
        dal.modificarBase(base);
        }
        public void eliminarBase(Base base) throws DAOExcepcion{
        dal.eliminarBase(base);
        }
        public ArrayList<Base> getBases() throws DAOExcepcion{
            return dal.getBases();
        }
        public ArrayList<Base> getBases(int idp) throws DAOExcepcion{
            return dal.getBases(idp);
        }
        public Base getBase(int idb) throws DAOExcepcion{
            return dal.getBase(idb);
        }
        public Base getBase(String nombre) throws DAOExcepcion{
            return dal.getBase(nombre);
        }
}
