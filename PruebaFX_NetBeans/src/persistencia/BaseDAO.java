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
    public Base getBase(int idb) throws DAOExcepcion {
   Base base;
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ADITIVOS_CANTIDAD");
			connManager.close();
			try {
				
				while (rs.next()){
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
					base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"));
					base.setAditivos(array);
                                        return base;
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return null;  }

    @Override
    public void insertarBase(Base base) throws DAOExcepcion {
     try {
			this.connManager.connect();
			this.connManager.updateDB("insert into BASES (ID, NOMBRE, " +
					"PORCENTAJE) values ("
					+ null
					+ ",'"
					+ base.getNombre()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}}

    @Override
    public void modificarBase(Base base) throws DAOExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarBase(Base base) throws DAOExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}