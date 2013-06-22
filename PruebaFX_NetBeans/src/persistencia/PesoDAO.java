package persistencia;

import logica.Acabado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DAOExcepcion;

import logica.Acabado;
import logica.Peso;


public class PesoDAO implements IPesoDAO{
	private ConnectionManager connManager;
	public PesoDAO() throws DAOExcepcion{
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("CALCPIGMENTOS");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
	}
	

	@Override
	public Peso getPeso() throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PESO where ID=0");						
			connManager.close();
			try{
				if (rs.next()){
					return new Peso(rs.getInt("ID"),rs.getDouble("MINIMO"),rs.getDouble("MAXIMO"));
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
	public void insertarPeso(Peso peso) {
		try {
			this.connManager.connect();
			this.connManager.updateDB("insert into Peso (ID, MINIMO, " +
					"MAXIMO) values ("
					+ null
					+ ","
					+ peso.getPesoMin()
					+ " , "
					+ peso.getPesoMax()
					+ ")");
			this.connManager.close();

		} catch (DAOExcepcion e) {
			System.out.println("ERROR EN DAO");
		}
	}

	@Override
	public void eliminarPeso(Peso peso) {
		try{
			connManager.connect();
			connManager.updateDB("DELETE FROM PESO WHERE ID = " + peso.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
	}

    @Override
    public void modificarPeso(Peso peso) throws DAOExcepcion {
    try{
			connManager.connect();
			connManager.updateDB("UPDATE PESO SET MINIMO ="+peso.getPesoMin()+ 
                        ",MAXIMO="+peso.getPesoMax()+" WHERE ID ="+peso.getId());
			connManager.close();

		}catch (DAOExcepcion e){
			e.printStackTrace();
		}
    }

	

}
