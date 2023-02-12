package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeTransaction extends BaseTranscation<Commande> {

	public CommandeTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Commande object) throws SQLException {
		
		String request = "INSERT INTO commande( numero, date , id_client ) VALUES (?,? , ?) ";
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		// mapping objet relation
		
		this.preparedStatement.setString(2, object.getDate());
		this.preparedStatement.setInt(3, object.getClient().getId_client());
		

		
		this.preparedStatement.execute();
		
	}

	@Override
	public void update(Commande object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Commande object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Commande> getAll() throws SQLException {
		// TODO Auto-generated method stub
List<Commande> liste = new ArrayList<Commande>();
		
		
		String request = "select * from  commande  ";
		
		this.statement = this.cnx.createStatement();

		 this.resultSet = this.statement.executeQuery(request);
		 
		 
		 while ( this.resultSet.next()) {
			
			 
			 liste.add(new Commande(resultSet.getInt("id_commande"), resultSet.getString("date"), resultSet.getInt("qte"), resultSet.getInt("id_client"), resultSet.getInt("id_prod")));
		}
		
		
		return liste;
	}

	@Override
	public Commande getOne(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String request = "select * from  commande  where id_commande= ?";
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);

		 this.resultSet = this.preparedStatement.executeQuery();
		 
		 
		 while ( this.resultSet.next()) {
			
			 
			new Commande(resultSet.getInt("id_commande"), resultSet.getString("date"), resultSet.getInt("qte"), resultSet.getInt("id_client"), resultSet.getInt("id_prod"));
			
		}
		

		return null;
	}
	
	
	
	public List<Commande> getByClient(Client client) throws SQLException {
		// TODO Auto-generated method stub
			List<Commande> liste = new ArrayList<Commande>();
		
		
		String request = "select * from  commande where id_client	= ?  ";
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		
		this.preparedStatement.setInt(1, client.getId_client());
		
		
		this.resultSet = this.preparedStatement.executeQuery();
		 
		 while ( this.resultSet.next()) {
			
			 
			 liste.add(new Commande(resultSet.getInt("id_commande"), resultSet.getString("date"), resultSet.getInt("qte"), resultSet.getInt("id_client"), resultSet.getInt("id_prod")));
			
		}

		
		return liste;
	}

}
