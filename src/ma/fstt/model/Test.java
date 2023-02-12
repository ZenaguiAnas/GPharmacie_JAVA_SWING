package ma.fstt.model;

import java.sql.SQLException;
import java.time.Period;
import java.util.List;

public class Test {
	
	
	public static void main(String[] args) {
		
		
	Client cli = new Client(0, "Mohamed", "mohamed@fstt.ma", "M", "X5jHDLK6");
	
	ClientTransaction clientTransaction;
	try {
		clientTransaction = new ClientTransaction();
		
		//clientTransaction.save(cli);
		
		
		//List<Client> list = clientTransaction.getAll();
		
		
		//for (Client client : list) {
			
			//System.out.println(client.toString());
			
		//}
		
		//clientTransaction.delete(clientTransaction.getOne(2));
		
		System.out.println(clientTransaction.getOne(4).toString());
		
		for (Commande cmd : clientTransaction.getOne(4).getLsitcmds()) {
			
			
			System.out.println(cmd.toString());
			
		}
		
		
		
		
		//CommandeTransaction commandeTransaction = new CommandeTransaction();
		
		
		//Commande cmd = new Commande(0, "xxx34343434", "23/01/2022" , clientTransaction.getOne(4));
		
		
		//commandeTransaction.save(cmd);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		
	}

}
