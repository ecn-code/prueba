package persistencia;



import logica.Producto;
import excepciones.*;
import java.util.ArrayList;
import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Color;
import logica.Peso;
import logica.Pigmento;
import logica.Producto;

public class DAL {
	private static DAL dal;
	IProductoDAO iproductoDAO;
        IPigmentoDAO ipigmentoDAO;
	IAcabadoDAO iacabadoDAO;
        IAditivoDAO iaditivoDAO;
        IBaseDAO ibaseDAO;
        IColorDAO icolorDAO;
        IPesoDAO ipesoDAO;
private DAL() throws DAOExcepcion{
	// Objectos para comunicarse con la capa de acceso a datos
	this.iproductoDAO = new ProductoDAO();
        this.ipigmentoDAO = new PigmentoDAO();
        this.iacabadoDAO=new AcabadoDAO();
        this.iaditivoDAO=new AditivoDAO();
        this.ibaseDAO= new BaseDAO();
        this.icolorDAO=new ColorDAO();
        this.ipesoDAO=new PesoDAO();
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
public ArrayList<Aditivo> getAditivos(int idb,double concentracion) throws DAOExcepcion{
return iaditivoDAO.getAditivos(idb,concentracion);
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

public ArrayList<Base> getBasesNoAsignadas(int idp) throws DAOExcepcion{
    return ibaseDAO.getBasesNoAsignadas(idp);
}
public ArrayList<Base> getBases(int idb) throws DAOExcepcion{
    return ibaseDAO.getBases(idb);
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
public void eliminarConcentracionBase(Base base) throws DAOExcepcion{
    ibaseDAO.eliminarConcentracionBase(base);
}
    public void eliminaBasePigmento(Base base, Pigmento pigmento) {
        ibaseDAO.eliminarBasePigmento(base,pigmento);
    }
    public Double getPorcentaje(Base base, Pigmento pigmento) throws DAOExcepcion {
       return ibaseDAO.getPorcentaje(base, pigmento);
    }

    public void insertarColor(Color color)throws DAOExcepcion {
      icolorDAO.insertarColor(color);
    }

    public void eliminarColor(Color color) throws DAOExcepcion {
      icolorDAO.eliminarColor(color);
    }

    public Color getColor(String nombre)throws DAOExcepcion {
      return icolorDAO.getColor(nombre);
    }
    public Color getColor(int idb)throws DAOExcepcion {
      return icolorDAO.getColor(idb);
    }
    public void modificarColor(Color color)throws DAOExcepcion {
     icolorDAO.modificarColor(color);
    }

    public ArrayList<Color> getColores()throws DAOExcepcion {
        return icolorDAO.getColores();
    }

    public ArrayList<Color> getColores(int idP) throws DAOExcepcion {
      return icolorDAO.getColores(idP);
    }

    public ArrayList<Color> getColoresNoAsignados(int idp) throws DAOExcepcion {
     return icolorDAO.getColoresNoAsignados(idp);
    }

    public void asociarColorPigmento(Pigmento pigmento, Color color, double porcentaje) throws DAOExcepcion {
        icolorDAO.asociarColorPigmento(pigmento, color, porcentaje);
    }

    public void eliminarColorPigmento(Color color, Pigmento pigmento) throws DAOExcepcion {
      icolorDAO.eliminarColorPigmento(color, pigmento);
    }
    public void asociarConcentracion(Color color, Double cantidad)throws DAOExcepcion{
    icolorDAO.asociarConcentracion(color, cantidad);
    }
public boolean existeConcentracionBase(Color color,Double cantidad)throws DAOExcepcion{
   return icolorDAO.existeConcentracionBase(color, cantidad);
}
 public ArrayList<Aditivo> getAditivosNoAsignados(int idb,double concentracion) throws DAOExcepcion {
            return iaditivoDAO.getAditivosNoAsignados(idb,concentracion);
 }
 public Double getPorcentaje(Color color, Pigmento pigmento) throws DAOExcepcion {
     return icolorDAO.getPorcentaje(color, pigmento);
 }
 public Peso getPeso() throws DAOExcepcion{
     return ipesoDAO.getPeso();
 }
public void insertarPeso(Peso peso) throws DAOExcepcion {
     ipesoDAO.insertarPeso(peso);
}
public void eliminarPeso(Peso peso) throws DAOExcepcion {
    ipesoDAO.eliminarPeso(peso);
}
public void modificarPeso(Peso peso) throws DAOExcepcion {
       ipesoDAO.modificarPeso(peso);
    }
}