
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


public interface IBaseDAO {
public ArrayList<Base> getBases() throws DAOExcepcion;
//public ArrayList<Base> getBases(int idp) throws DAOExcepcion
public ArrayList<Base> getBasesNoAsignadas(int idp)throws DAOExcepcion;
public ArrayList<Base> getBases(int idb) throws DAOExcepcion ;
//public Base getBase(String nombre) throws DAOExcepcion ;
public void insertarBase(Base base) throws DAOExcepcion ;
public void modificarBase(Base base) throws DAOExcepcion ;
public void eliminarConcentracionBase(Base base) throws DAOExcepcion ;
public void asociarBasePigmento(Pigmento pigmento, Base base, Double cantidad)throws DAOExcepcion ;
public void eliminarBasePigmento(Base base, Pigmento pigmento);
public Double getPorcentaje(Base base,Pigmento pigmento)throws DAOExcepcion;
}
