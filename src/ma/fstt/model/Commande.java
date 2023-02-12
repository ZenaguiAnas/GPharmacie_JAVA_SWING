package ma.fstt.model;

public class Commande {
	
	
	
	private Integer id_commande;
	private String date;
	private int qtePrd;

	// Many  to one 
	private Client client ;
	private Product product;



	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}


	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commande(Integer id_commande, String date, int qtePrd , int cl, int pr) {
		super();
		this.id_commande = id_commande;
		this.date = date;
		this.client.setId_client(cl);
		this.product.setId(pr);
		this.qtePrd = qtePrd;
	}

	public Integer getId_commande() {
		return id_commande;
	}
	public void setId_commande(Integer id_commande) {
		this.id_commande = id_commande;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public int getQtePrd() {
		return qtePrd;
	}

	public void setQtePrd(int qtePrd) {
		this.qtePrd = qtePrd;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", date=" + date + ", client=" + client
				+ ", Produit=" + product + "]";
	}
	
	
	
	
	
	

}
