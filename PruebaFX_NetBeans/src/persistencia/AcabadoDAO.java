package persistencia;

import logica.Acabado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Acabado;


public class AcabadoDAO implements IAcabadoDAO{
	private ConnectionManager connManager;
	public AcabadoDAO() throws DAOExcepcion{
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}
	@Override
	public ArrayList<Acabado> getAcabados() throws DAOExcepcion{
		// TODO Auto-generated method stub
		
		ArrayList<Acabado> acabados = new ArrayList<Acabado>();
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ACABADO ORDER BY NOMBRE");
			connManager.close();
			try {
				
				while (rs.next()){
					Acabado acabado = new Acabado(rs.getInt("ID"),rs.getString("NOMBRE"),Double.toString(rs.getDouble("FACTOR")));
					acabados.add(acabado);
		
				}
				}catch (SQLException e){
					throw new DAOExcepcion("DB_READ_ERROR");
				}
			
			}catch (DAOExcepcion e){
				throw e;
			}
			return acabados;
	}

	@Override
	public Acabado getAcabado(String nombre) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ACABADO where NOMBRE= '"+nombre+"'");						
			connManager.close();
			try{
				if (rs.next()){
					return new Acabado(rs.getInt("ID"),rs.getString("NOMBRE"),Double.toString(rs.getDouble("FACTOR")));
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
	public void insertarAcabado(Acabado acabado) {
		try {
			this.connManager.connect();
			this.connManager.updateDB("insert into ACABADO (ID, NOMBRE, " +
					"FACTOR) values ("
					+ null
					+ ",'"
					+ acabado.getNombre()
					+ "' , "
					+ acabado.getFactor()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
	}

	@Override
	public void modificarAcabado(Acabado acabado) {
		try{
			connManager.connect();
			connManager.updateDB("UPDATE ACABADO SET NOMBRE = '" + acabado.getNombre()+ 
                        "',FACTOR ='"+acabado.getFactor()+"'WHERE ID = " + acabado.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarAcabado(Acabado acabado) {
		try{
			connManager.connect();
			connManager.updateDB("DELETE FROM ACABADO WHERE ID = " + acabado.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
	}

}
