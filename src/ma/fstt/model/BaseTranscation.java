package ma.fstt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseTranscation<T> {
	
	
	protected Connection cnx ;
	
	protected Statement statement ;
	
	
	protected PreparedStatement preparedStatement ;
	
	
	protected ResultSet resultSet ;
	
//	private String url = "jdbc:mysql://localhost:3306/GPharmacie";
//	private String login = "root";
//	private String pass = "";
	
	
	public BaseTranscation() throws SQLException {
		
		this.cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/GPharmacie", "root", "");
		
	}
	
	
	public abstract void save(T object)  throws SQLException ;
	public abstract void update(T object)  throws SQLException ;
	public abstract void delete(T object)  throws SQLException ;
	public abstract List<T> getAll()  throws SQLException ;
	public abstract T getOne(Integer id)  throws SQLException ;
	
	
	

}
