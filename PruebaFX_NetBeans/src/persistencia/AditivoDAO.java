/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Pigmento;

/**
 *
 * @author roberto
 */
public class AditivoDAO implements IAditivoDAO{
    private ConnectionManager connManager;
public AditivoDAO() throws DAOExcepcion{
    	super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
}
    @Override
    public void añadirAditivo(String nombre) throws DAOExcepcion {
        double valor=0.0;
     try {
			this.connManager.connect();
			this.connManager.updateDB("ALTER TABLE ADITIVOS ADD ("+nombre.toUpperCase()+" DOUBLE)");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
       try{
			connManager.connect();
			connManager.updateDB("UPDATE ADITIVOS SET '"+nombre.toUpperCase()+"'='"+valor);
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

    @Override
    public void eliminarAditivo(String nombre) throws DAOExcepcion {
      try{
			connManager.connect();
			connManager.updateDB("ALTER TABLE ADITIVOS DROP ("+nombre.toUpperCase());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

    @Override
    public ArrayList<String> getAditivos() throws DAOExcepcion {
        ArrayList<String> aditivos = new ArrayList<String>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("SELECT COLUMN_NAME from INFORMATION_SCHEMA.SYSTEM_COLUMNS WHERE TABLE_SCHEM='PUBLIC' and TABLE_NAME='ADITIVOS' and COLUMN_NAME<>'IDB' and COLUMN_NAME<>'PORCENTAJE'");
			connManager.close();
			try {
				
				while (rs.next()){
					aditivos.add(rs.getString("COLUMN_NAME"));
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return aditivos;
      
    }
    
}
