<<<<<<< HEAD

=======
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
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
<<<<<<< HEAD
	public BaseDAO() throws DAOExcepcion{
		super();
=======
        private DAL dal;
	public BaseDAO(DAL _dal) throws DAOExcepcion{
		super();
                dal = _dal;
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
<<<<<<< HEAD
}
   




	

    @Override
    public ArrayList<Base> getBases() throws DAOExcepcion {
    ArrayList<Base> bases = new ArrayList<Base>();
    
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BASE");
=======
	}


    @Override
    public ArrayList<Base> getBases() throws DAOExcepcion {
   ArrayList<Base> bases = new ArrayList<Base>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ADITIVOS_CANTIDAD");
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
			connManager.close();
			try {
				
				while (rs.next()){
<<<<<<< HEAD
					String nombre = rs.getString("NOMBRE");
                                        String porcentaje = rs.getString("PORCENTAJE");
					Base base=new Base(nombre,porcentaje);
                                        connManager.connect();
                                        ResultSet rs2=connManager.queryDB("select * from BASE_ADITIVO where nombre="+nombre+);
                                        connManager.close();
=======
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
					Base base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"),rs.getDouble("PORCENTAJE"));
					base.setAditivos(array);
                                        bases.add(base);
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
<<<<<<< HEAD
			return aditivos;
    }

    @Override
    public ArrayList<Aditivo> getAditivos(int idb) throws DAOExcepcion {
         ArrayList<Aditivo> aditivos = new ArrayList<Aditivo>();
    
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ADITIVOS_CANTIDAD WHERE IDB='"+idb+"'and PORCENTAJE="+porcentaje);
=======
			return bases; 
    }

    @Override
    public Base getBase(int idb) throws DAOExcepcion {
   Base base;
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ADITIVOS_CANTIDAD");
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
			connManager.close();
			try {
				
				while (rs.next()){
<<<<<<< HEAD

					Aditivo aditivo = new Aditivo(rs.getString("NOMBRE"),rs.getDouble("CANTIDAD"));
					aditivos.add(aditivo);
=======
                                   ArrayList<Aditivo> array = dal.getAditivos(rs.getInt("IDB"));
					base = new Base(rs.getInt("IDB"),rs.getString("NOMBRE"),rs.getDouble("PORCENTAJE"));
					base.setAditivos(array);
                                        return base;
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
<<<<<<< HEAD
			return aditivos;

    }

    @Override
    public void insertarAditivoNombre(String nombre) throws DAOExcepcion {
    try {
			this.connManager.connect();
			this.connManager.updateDB("insert into ADITIVOS (NOMBRE) values ('"+
					nombre
					+ "')");
=======
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
					+ "' , "
					+ base.getPorcentaje()
					+ ")");
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}}

    @Override
<<<<<<< HEAD
    public void modificarAditivoNombre(String nombreAntes,String nombreAhora) throws DAOExcepcion {
        try{
			connManager.connect();
			connManager.updateDB("UPDATE ADITIVOS SET NOMBRE = '" + nombreAhora+ 
                        "'WHERE NOMBRE = " + nombreAntes);
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
     }

    @Override
    public void eliminarAditivoNombre(String nombre) throws DAOExcepcion {
    try{
			connManager.connect();
			connManager.updateDB("DELETE FROM ADITIVOS_CANTIDAD WHERE NOMBRE = " + nombre);
			connManager.close();
                        connManager.connect();
			connManager.updateDB("DELETE FROM ADITIVOS WHERE NOMBRE = " + nombre);
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}}

    @Override
    public void asociarAditivo(Base base, Aditivo aditivo) throws DAOExcepcion {
      try {
			this.connManager.connect();
			this.connManager.updateDB("insert into ADITIVOS_CANTIDAD (IDB,PORCENTAJE,NOMBRE,CANTIDAD) values ("+
					base.getId()+","+
                                        base.getPorcentaje()+","+
                                        aditivo.getNombre()+","+
                                        aditivo.getCantidad()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}}

    @Override
    public void eliminaAsociacionAditivo(Base base, Aditivo aditivo) throws DAOExcepcion {
        connManager.connect();
			connManager.updateDB("DELETE FROM ADITIVOS_CANTIDAD WHERE NOMBRE = " + aditivo.getNombre()+" AND"+
                                                "IDB="+base.getId());
			connManager.close();
=======
    public void modificarBase(Base base) throws DAOExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarBase(Base base) throws DAOExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 13caf2f669d5c0f30d57f0acddaa75bc28be0833
    }

}
