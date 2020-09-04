package productDAO;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void insertProduct(Product product) throws SQLException;

    public Product selectProduct(String id);

    public List<String[]> selectSpecSm(String productId);

    public List<Product> selectAlProduct(String special);

    public List<String> selectAllProducer(String special);

    public boolean deleteProduct(String id) throws SQLException;

    public boolean updateProduct(Product user) throws SQLException;
}