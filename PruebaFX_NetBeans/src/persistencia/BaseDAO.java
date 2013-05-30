package persistencia;

import logica.Acabado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Acabado;
import logica.Aditivo;
import logica.Base;


public class BaseDAO implements IBaseDAO{
	private ConnectionManager connManager;
        private DAL dal;
	public BaseDAO(DAL _dal) throws DAOExcepcion{
		super();
                dal = _dal;
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
			ResultSet rs=connManager.queryDB("select * from ADITIVOS_CANTIDAD");
			connManager.close();
			try {
				
				while (rs.next()){
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
					Base base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
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
			connManager.updateDB("UPDATE ADITIVOS SET NOMBRE = '" + base.getNombre()+ 
                        "'WHERE IDB = " + base.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}}

    @Override
    public void eliminarBase(Base base) throws DAOExcepcion {
     try{
			connManager.connect();
			connManager.updateDB("DELETE FROM BASES_PIGMENTO WHERE IDB = " + base.getId());
                        connManager.updateDB("DELETE FROM ADITIVOS_CANTIDAD WHERE IDB = " + base.getId());
			connManager.updateDB("DELETE FROM BASES WHERE IDB = " + base.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

   

    @Override
    public Base getBase(int idb) throws DAOExcepcion {
         Base base=null;        
        try{
                   
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES WHERE IDB="+idb);
			connManager.close();
			try {
				
				while (rs.next()){
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
                                   
				base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
					base.setAditivos(array);
                                        
		
				}
                                return base;
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
                        }
    }

    @Override
    public ArrayList<Base> getBases(int idp) throws DAOExcepcion {
        ArrayList<Base> bases = new ArrayList<Base>();
                try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES_PIGMENTO");
			connManager.close();
			try {
				
				while (rs.next()){
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
                                   
				Base base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
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
    }

    @Override
    public Base getBase(String nombre) throws DAOExcepcion {
            
       try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASES where NOMBRE= '"+nombre+"'");						
			connManager.close();
			try{
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

}