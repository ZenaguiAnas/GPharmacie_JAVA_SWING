package ma.fstt.model;

import java.util.List;

public interface IMetier {
    public void addCat(Categorie c);
    public void addPrd(Product p, int idCat);
    public List<Product> getProduitParMC(String MC);
    public List<Product> getProduitParCat(int idCat);
    public List<Categorie> getAllCategories();
    public Categorie getCategorie(int idCat);
}
