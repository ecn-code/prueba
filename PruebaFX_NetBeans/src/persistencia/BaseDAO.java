package persistencia;

import logica.Acabado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Color;
import logica.Pigmento;


public class BaseDAO implements IBaseDAO{
	private ConnectionManager connManager;
        private DAL dal;
	public BaseDAO() throws DAOExcepcion{
		super();

		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}


    @Override
    public ArrayList<Base> getBases() throws DAOExcepcion {
   ArrayList<Base> bases = new ArrayList<Base>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES");
			connManager.close();
			try {
				DAL dal = DAL.dameDAL();
				while (rs.next()){
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"),rs.getDouble("CONCENTRACION"));
					Base base = new Base(rs.getInt("IDB"),rs.getDouble("CONCENTRACION"));
					base.setAditivos(array);
                                        bases.add(base);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return bases; 
    }
    
     

    @Override
    public void insertarBase(Base base) throws DAOExcepcion {
                try {
			this.connManager.connect();
			this.connManager.updateDB("insert into BASES (IDB, NOMBRE) values ("
					+ null
					+ ",'"
					+ base.getNombre()
					+ "')");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
    }

    @Override
    public void modificarBase(Base base) throws DAOExcepcion {
    try{
			connManager.connect();
			connManager.updateDB("UPDATE BASES SET NOMBRE = '" + base.getNombre()+ 
                        "'WHERE IDB = " + base.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}}

    @Override
    public void eliminarConcentracionBase(Base base) throws DAOExcepcion {
     try{
			connManager.connect();
                        connManager.updateDB("DELETE FROM ADITIVOS_CANTIDAD WHERE IDB = " + base.getId()+" and CONCENTRACION="+base.getConcentracion());
			connManager.updateDB("DELETE FROM BASES WHERE IDB = " + base.getId()+" and CONCENTRACION="+base.getConcentracion());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

   

    @Override
    public ArrayList<Base> getBases(int idb) throws DAOExcepcion {
        Base base=null;    
        ArrayList<Base> bases =new ArrayList<Base>();
        try{
                   
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES WHERE IDB="+idb);
			connManager.close();
			try {
			DAL dal = DAL.dameDAL();
				while (rs.next()){
                                base = new Base(rs.getInt("IDB"),rs.getDouble("CONCENTRACION"));
				bases.add(base);
                                        
		
				}
                                return bases;
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
                        }
    }

   /* @Override
    public ArrayList<Base> getBases(int idp) throws DAOExcepcion {
        ArrayList<Base> bases = new ArrayList<Base>();
                try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES WHERE IDP="+idp);
			connManager.close();
			try {
				DAL dal = DAL.dameDAL();
				while (rs.next()){
                                    
                                   


				Base base = getBase(rs.getInt("IDB"));
                                base.setConcentracion(rs.getDouble("PORCENTAJE"));
                                ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
                                base.setAditivos(array);
                                bases.add(base);
                                        
		
				}
                                return bases;
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
    }*/

   /* @Override
    public Base getBase(String nombre) throws DAOExcepcion {
            
       try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES where NOMBRE= '"+nombre+"'");						
			connManager.close();
                        
			try{
                            DAL dal = DAL.dameDAL();
				if (rs.next()){
                                        Base base=new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
                                        ArrayList<Aditivo> aditivos = dal.getAditivos(rs.getInt("IDB"));
					base.setAditivos(aditivos);
                                        return base;
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
*/
    @Override
    public void asociarBasePigmento(Pigmento pigmento, Base base, Double cantidad) throws DAOExcepcion {
       try {
			this.connManager.connect();
			this.connManager.updateDB("insert into BASES_PIGMENTO (PORCENTAJE,IDP,IDB) values ("+
					cantidad+","+
                                        pigmento.getId()+","+
                                        base.getId()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
    }

    @Override
    public ArrayList<Base> getBasesNoAsignadas(int idp) throws DAOExcepcion {
     ArrayList<Base> bases = new ArrayList<Base>();
                
		try{
                    
			connManager.connect();
			ResultSet rs=connManager.queryDB("Select * from BASES ba where NOT EXISTS(Select * from "
                                + "BASES_PIGMENTO basp WHERE IDP="+idp+" and ba.IDB=basp.IDB)");
			connManager.close();
                        
                        
			try {
				
				while (rs.next()){

				Base base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
					//aditivo.setCantidad(rs.getDouble("CANTIDAD"));
                                        bases.add(base);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
                
			return bases;

    }

    @Override
    public void eliminarBasePigmento(Base base, Pigmento pigmento) {
        try{
			connManager.connect();
			connManager.updateDB("DELETE FROM BASES_PIGMENTO WHERE IDB = " + base.getId()+" and IDP="+pigmento.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

    @Override
    public Double getPorcentaje(Base base, Pigmento pigmento) throws DAOExcepcion {
       try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES_PIGMENTO WHERE IDB="+base.getId()+""
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

    
    }