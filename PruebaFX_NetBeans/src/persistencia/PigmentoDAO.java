package persistencia;

import logica.Pigmento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Pigmento;


public class PigmentoDAO implements IPigmentoDAO{
	private ConnectionManager connManager;
	public PigmentoDAO() throws DAOExcepcion{
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}

	public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion{
		// TODO Auto-generated method stub
		
		ArrayList<Pigmento> pigmentos = new ArrayList<Pigmento>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PIGMENTO");
			connManager.close();
			try {
				
				while (rs.next()){
					Pigmento pigmento = new Pigmento(rs.getInt("IDP"),rs.getString("NOMBRE"));
					pigmentos.add(pigmento);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return pigmentos;
	}

	@Override
	public Pigmento getPigmento(String nombre) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PRODUCTO where NOMBRE= '"+nombre+"'");						
			connManager.close();
			try{
				if (rs.next()){
					return new Pigmento(rs.getInt("ID"),rs.getString("NOMBRE"));
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
	public void insertarPigmento(Pigmento pigmento) {
		try {
			this.connManager.connect();
			this.connManager.updateDB("insert into PRODUCTO (ID, NOMBRE, " +
					"FACTOR) values ("
					+ null
					+ ",'"
					+ pigmento.getNombre()
					+ "')");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
	}

	public void modificarPigmento(Pigmento pigmento) {
		try{
			connManager.connect();
			connManager.updateDB("UPDATE PRODUCTO SET NOMBRE = '" + pigmento.getNombre()+ 
                        "'WHERE ID = " + pigmento.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
	}


	public void eliminarPigmento(Pigmento pigmento) {
		try{
			connManager.connect();
			connManager.updateDB("DELETE FROM PRODUCTO WHERE ID = " + pigmento.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
	}

}
