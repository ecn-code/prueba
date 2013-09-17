package persistencia;

import logica.Acabado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Color;
import logica.Pigmento;


public class ColorDAO implements IColorDAO{
	private ConnectionManager connManager;
        private DAL dal;
	public ColorDAO() throws DAOExcepcion{
		super();

		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}


    @Override
    public ArrayList<Color> getColores() throws DAOExcepcion {
   ArrayList<Color> colors = new ArrayList<Color>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COLORES ORDER BY NOMBRE");
			connManager.close();
			try {
				DAL dal = DAL.dameDAL();
				while (rs.next()){
                                 //  ArrayList<Base> array = dal.getBases(rs.getInt("IDB"));
					Color color = new Color(rs.getInt("IDB"),rs.getString("NOMBRE"),0.0);
				//	color.setBases(array);
                                        colors.add(color);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return colors; 
    }
    
     

    @Override
    public void insertarColor(Color color) throws DAOExcepcion {
                try {
			this.connManager.connect();
			this.connManager.updateDB("insert into COLORES (IDB, NOMBRE) values ("
					+ null
					+ ",'"
					+ color.getNombre()
					+ "')");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
    }

    @Override
    public void modificarColor(Color color) throws DAOExcepcion {
    try{
			connManager.connect();
			connManager.updateDB("UPDATE COLORES SET NOMBRE = '" + color.getNombre()+ 
                        "'WHERE IDB = " + color.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}}

    @Override
    public void eliminarColor(Color color) throws DAOExcepcion {
     try{
			connManager.connect();
			connManager.updateDB("DELETE FROM COLORES_PIGMENTO WHERE IDB = " + color.getId());
                        connManager.updateDB("DELETE FROM ADITIVOS_CANTIDAD WHERE IDB = " + color.getId());
			connManager.updateDB("DELETE FROM COLORES WHERE IDB = " + color.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

   

    @Override
    public Color getColor(int idb) throws DAOExcepcion {
         Color color=null;        
        try{
                   
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COLORES WHERE IDB="+idb);
			connManager.close();
			try {
			DAL dal = DAL.dameDAL();
				while (rs.next()){
 				color = new Color(rs.getInt("IDB"),rs.getString("NOMBRE"),0.0);

				}
                                return color;
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
                        }
    }

    @Override
    public ArrayList<Color> getColores(int idp) throws DAOExcepcion {
        ArrayList<Color> colors = new ArrayList<Color>();
                try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COLORES_PIGMENTO WHERE IDP="+idp);
			connManager.close();
			try {
				DAL dal = DAL.dameDAL();
				while (rs.next()){
                                    
                                   


				Color color = getColor(rs.getInt("IDB"));
                                color.setPorcentaje(rs.getDouble("PORCENTAJE"));
                                colors.add(color);
                                        
		
				}
                                return colors;
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
    }

    @Override
    public Color getColor(String nombre) throws DAOExcepcion {
            
       try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COLORES where NOMBRE= '"+nombre+"'");						
			connManager.close();
                        
			try{
                            DAL dal = DAL.dameDAL();
				if (rs.next()){
                                        Color color=new Color(rs.getInt("IDB"),rs.getString("NOMBRE"),0.0);
                                       // ArrayList<Base> bases = dal.getBases(rs.getInt("IDB"));
					//color.setBases(bases);
                                        return color;
				}
				else
					return null;
			}catch (SQLException e){
				throw new DAOExcepcion("DB_READ_ERROR");
			}
			
			}catch (DAOExcepcion e){
				throw e;
			}
    }

    @Override
    public void asociarColorPigmento(Pigmento pigmento, Color color, Double cantidad) throws DAOExcepcion {
       try {
			this.connManager.connect();
			this.connManager.updateDB("insert into COLORES_PIGMENTO (PORCENTAJE,IDP,IDB) values ("+
					cantidad+","+
                                        pigmento.getId()+","+
                                        color.getId()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
    }

    @Override
    public ArrayList<Color> getColoresNoAsignados(int idp) throws DAOExcepcion {
     ArrayList<Color> colors = new ArrayList<Color>();
                
		try{
                    
			connManager.connect();
			ResultSet rs=connManager.queryDB("Select * from COLORES ba where NOT EXISTS(Select * from "
                                + "COLORES_PIGMENTO basp WHERE IDP="+idp+" and ba.IDB=basp.IDB)");
			connManager.close();
                        
                        
			try {
				
				while (rs.next()){

				Color color = new Color(rs.getInt("IDB"),rs.getString("NOMBRE"),0.0);
					//aditivo.setCantidad(rs.getDouble("CANTIDAD"));
                                        colors.add(color);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
                
			return colors;

    }

    @Override
    public void eliminarColorPigmento(Color color, Pigmento pigmento) {
        try{
			connManager.connect();
			connManager.updateDB("DELETE FROM COLORES_PIGMENTO WHERE IDB = " + color.getId()+" and IDP="+pigmento.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

    @Override
    public Double getPorcentaje(Color color, Pigmento pigmento) throws DAOExcepcion {
       try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COLORES_PIGMENTO WHERE IDB="+color.getId()+""
                                + " and IDP="+pigmento.getId());
			connManager.close();
			try {
				
				if (rs.next()){
                               return rs.getDouble("PORCENTAJE");
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return null; 
    }

    @Override
    public void asociarConcentracion(Color color, Double cantidad) throws DAOExcepcion {
            try {
			this.connManager.connect();
			this.connManager.updateDB("insert into BASES (CONCENTRACION, IDB) values ("
					+ cantidad
					+ ",'"
					+ color.getId()
					+ "')");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
    }

    @Override
    public boolean existeConcentracionBase(Color color, Double cantidad) throws DAOExcepcion {
             
        try{
                   
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES WHERE IDB="+color.getId()+" and CONCENTRACION="+cantidad);
			connManager.close();
                        if(rs.next())
                         return true;
                    }catch (DAOExcepcion e){
				throw e;
                        } catch (SQLException ex) {
                Logger.getLogger(ColorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }
    }