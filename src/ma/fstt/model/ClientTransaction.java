package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientTransaction extends BaseTranscation<Client>{
	
	private CommandeTransaction commandeTransaction ;

	public ClientTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
		
		commandeTransaction = new CommandeTransaction();
	}

	@Override
	public void save(Client object) throws SQLException {



		String request= "insert into client (nom , email, genre) values (? ,? , ?)" ;

		this.preparedStatement = this.cnx.prepareStatement(request);

		// maooing relatipon et objet table client et objet client
		this.preparedStatement.setString(1, object.getNom());
		this.preparedStatement.setString(2, object.getEmail());
		this.preparedStatement.setString(3, object.getGenre());
		
		this.preparedStatement.execute();
		
	}

	@Override
	public void update(Client object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Client object) throws SQLException {
		
		String request= "delete  from  client where id_client =?" ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, object.getId_client());
		
	  this.preparedStatement.execute();
	   
	   
		

		
	}

	@Override
	public List<Client> getAll() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Client> malist = new ArrayList<Client>();

		String request= "select * from  client " ;
		
		this.statement = this.cnx.createStatement();
		
	   this.resultSet = 	this.statement.executeQuery(request);
	   
	   
	   while (this.resultSet.next()) {
		
		   malist.add(new Client( this.resultSet.getInt(1),  this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), this.resultSet.getString(5)));



	}
		

		return malist;
	}

	@Override
	public Client getOne(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		

				String request= "select * from  client where id_client =?" ;
				
				this.preparedStatement = this.cnx.prepareStatement(request);
				
				this.preparedStatement.setInt(1, id);
				
			   this.resultSet = 	this.preparedStatement.executeQuery();
			   
			   
			   while (this.resultSet.next()) {
				   
				   
				   
				   Client cli = new Client( this.resultSet.getInt(1),  this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), this.resultSet.getString(5));
				   
				   cli.setLsitcmds(commandeTransaction.getByClient(cli));
				  
				   return cli ;
				   
				 
				
			}
				

				return null;
	}

}
