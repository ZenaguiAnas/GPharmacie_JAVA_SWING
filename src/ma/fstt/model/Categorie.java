package ma.fstt.model;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int idCat;
    private String nomCat;
    private List<Product> products = new ArrayList<Product>();

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public List <Product> getProducts() {
        return products;
    }

    public void setProducts(List <Product> products) {
        this.products = products;
    }

    public Categorie(String nomCat) {
        this.nomCat = nomCat;
    }

    public Categorie() {
        super();
    }
}
