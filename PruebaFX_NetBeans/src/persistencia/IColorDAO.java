
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.util.ArrayList;
import logica.Base;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import logica.Aditivo;
import logica.Base;
import logica.Color;
import logica.Pigmento;


public interface IColorDAO {
public ArrayList<Color> getColores() throws DAOExcepcion;
public ArrayList<Color> getColores(int idp) throws DAOExcepcion;
public ArrayList<Color> getColoresNoAsignados(int idp)throws DAOExcepcion;
public Color getColor(int idb) throws DAOExcepcion ;
public Color getColor(String nombre) throws DAOExcepcion ;
public void insertarColor(Color color) throws DAOExcepcion ;
public void modificarColor(Color color) throws DAOExcepcion ;
public void eliminarColor(Color color) throws DAOExcepcion ;
public void asociarColorPigmento(Pigmento pigmento, Color base, Double cantidad)throws DAOExcepcion ;;
public void eliminarColorPigmento(Color color, Pigmento pigmento);
public Double getPorcentaje(Color color,Pigmento pigmento)throws DAOExcepcion;
public void asociarConcentracion(Color color, Double cantidad)throws DAOExcepcion;
public boolean existeConcentracionBase(Color color,Double cantidad)throws DAOExcepcion;
}
