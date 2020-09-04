package productDAO;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void insertProduct(Product product) throws SQLException;

    public Product selectProduct(String id);

    public List<Product> selectAlProduct();

    public List<String> selectAllProducer();

    public boolean deleteProduct(String id) throws SQLException;

    public boolean updateProduct(Product user) throws SQLException;
}
