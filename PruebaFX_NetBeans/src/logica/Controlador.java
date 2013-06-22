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
          public void modificarPigmento(Pigmento pigmento) throws DAOExcepcion{
        dal.modificarPigmento(pigmento);
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
        public ArrayList<Aditivo> getAditivos(int idb,double concentracion) throws DAOExcepcion{
        return dal.getAditivos(idb,concentracion);
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
        public void eliminarConcentracionBase(Base base) throws DAOExcepcion{
        dal.eliminarConcentracionBase(base);
        }
         public void asociarBasePigmento(Pigmento pigmento,Base base,Double cantidad) throws DAOExcepcion{
        dal.asociarBasePigmento(pigmento,base,cantidad);
        }
        public ArrayList<Base> getBases() throws DAOExcepcion{
            return dal.getBases();
        }
       
        public ArrayList<Base> getBasesNoAsignadas(int idp) throws DAOExcepcion{
            return dal.getBasesNoAsignadas(idp);
        }
        public ArrayList<Base> getBases(int idb) throws DAOExcepcion{
            return dal.getBases(idb);
        }
        public ArrayList<Aditivo> getAditivosNoAsignados(int idb,double concentracion) throws DAOExcepcion {
        return dal.getAditivosNoAsignados(idb,concentracion);
        }

        public void eliminaBasePigmento(Base base, Pigmento pigmento) {
        dal.eliminaBasePigmento(base,pigmento);
        }
        public Double getPorcentaje(Base base, Pigmento pigmento) throws DAOExcepcion {
        return dal.getPorcentaje(base, pigmento);
        }

        public void insertarColor(Color color)throws DAOExcepcion  {
        dal.insertarColor(color);
        }

        public void eliminarColor(Color color)throws DAOExcepcion{
        dal.eliminarColor(color);
        }

        public Color getColor(String nombre) throws DAOExcepcion{
        return dal.getColor(nombre);
        }

        public void modificarColor(Color color) throws DAOExcepcion{
        dal.modificarColor(color);
        }

        public ArrayList<Color> getColores() throws DAOExcepcion{
        return dal.getColores();
        }

        public ArrayList<Color> getColores(int idp) throws DAOExcepcion {
        return dal.getColores(idp);
        }

        public ArrayList<Color> getColoresNoAsignados(int idp) throws DAOExcepcion {
        return dal.getColoresNoAsignados(idp);
        }

        public void asociarColorPigmento(Pigmento pigmento, Color color, double porcentaje)throws DAOExcepcion  {
        dal.asociarColorPigmento(pigmento,color,porcentaje);
        }

        public void eliminaColorPigmento(Color color, Pigmento pigmento)throws DAOExcepcion {
        dal.eliminarColorPigmento(color,pigmento);   
        }
        public void asociarConcentracion(Color color, Double cantidad)throws DAOExcepcion{
        dal.asociarConcentracion(color, cantidad);
        }
        public boolean existeConcentracionBase(Color color,Double cantidad)throws DAOExcepcion{
        return dal.existeConcentracionBase(color, cantidad);
        }
         public Color getColor(int idb)throws DAOExcepcion {
             return dal.getColor(idb);
         }
         public Double getPorcentaje(Color color, Pigmento pigmento) throws DAOExcepcion {
             return dal.getPorcentaje(color, pigmento);
         }
         public Peso getPeso() throws DAOExcepcion{
             return dal.getPeso();
         }
        public void insertarPeso(Peso peso) throws DAOExcepcion{
            dal.insertarPeso(peso);
        }
        public void eliminarPeso(Peso peso) throws DAOExcepcion{
            dal.eliminarPeso(peso);
        }
        public void modificarPeso(Peso peso) throws DAOExcepcion{
            dal.modificarPeso(peso);
        }
}

