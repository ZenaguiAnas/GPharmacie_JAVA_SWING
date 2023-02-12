package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class ProductTransaction extends BaseTranscation<Product> {

    public ProductTransaction() throws Exception{
        super();

    }

    @Override
    public void save(Product object) throws SQLException {

    }

    @Override
    public void update(Product object) throws SQLException {

    }

    @Override
    public void delete(Product object) throws SQLException {

    }

    @Override
    public List<Product> getAll() throws SQLException {
        return null;
    }

    @Override
    public Product getOne(Integer id) throws SQLException {
        return null;
    }
}
