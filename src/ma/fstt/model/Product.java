package ma.fstt.model;

public class Product {

        private int id;
        private String name;
        private float price;
        private String addDate;
        private final byte[] image;
        private Categorie categorie;

        public Product(int id, String name, float price, String addDate, byte[] image)
        {
            this.id = id;
            this.name = name;
            this.price = price;
            this.addDate = addDate;
            this.image = image;
        }


        public int getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public float getPrice()
        {
            return price;
        }

        public String getAddDate()
        {
            return addDate;
        }

        public byte[] getImage()
        {
            return image;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setAddDate(String addDate) {
            this.addDate = addDate;
        }

        public void setCategorie(Categorie categorie) {
            this.categorie = categorie;
        }
}