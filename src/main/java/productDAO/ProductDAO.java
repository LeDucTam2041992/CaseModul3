package productDAO;

import model.Product;
import model.SpecSmartphone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAO implements IProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/casemd3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Sakurasaoyran204";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + "  (id, name, imgUrl, price) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_ALL_PRODUCER = "select distinct producer from products where special = ?";
    private static final String SELECT_PRODUCT_BY_ID = "select id,name,imgUrl,price,special from products where id = ?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products where special = ?";
    private static final String SORT_PRODUCT_BY_PRODUCER = "select * from products where special = ? and producer = ?";
    private static final String SELECT_SPECSMARTPHONE = "select * from specsmartphone where productId = ?";
    private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,imgUrl= ?, price =? where id = ?;";

    public ProductDAO() {
    }


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {

    }

    @Override
    public Product selectProduct(String id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String imgUrl = rs.getString("imgUrl");
                String specil = rs.getString("special");
                int price = rs.getInt("price");
                product = new Product(id, name, imgUrl, specil, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<String[]> selectSpecSm(String productId) {
        List<String[]> specSmartphone = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECSMARTPHONE);) {
            preparedStatement.setString(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                specSmartphone.add(new String[]{"SCREEN", rs.getString("screen")});
                specSmartphone.add(new String[]{"OPERA SYSTEM", rs.getString("operaSystem")});
                specSmartphone.add(new String[]{"CAMERA FONT", rs.getString("cameraFont")});
                specSmartphone.add(new String[]{"CAMERA BACK", rs.getString("cameraEnd")});
                specSmartphone.add(new String[]{"CPU", rs.getString("cpu")});
                specSmartphone.add(new String[]{"RAM", rs.getString("ram")});
                specSmartphone.add(new String[]{"MEMORY", rs.getString("memory")});
                specSmartphone.add(new String[]{"SIM", rs.getString("sim")});
                specSmartphone.add(new String[]{"PIN", rs.getString("pin")});
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return specSmartphone;
    }
    @Override
    public List<Product> selectAlProduct(String special) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            preparedStatement.setString(1, special);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String imgUrl = rs.getString("imgUrl");
                String specil = rs.getString("special");
                int price = rs.getInt("price");
                products.add(new Product(id, name, imgUrl, specil, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public List<String> selectAllProducer(String special) {
        List<String> producers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCER);) {
            preparedStatement.setString(1,special);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String producer = rs.getString("producer");
                producers.add(producer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return producers;
    }

    @Override
    public List<Product> sortProductByProducer(String special, String producer) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SORT_PRODUCT_BY_PRODUCER);) {
            preparedStatement.setString(1, special);
            preparedStatement.setString(2, producer);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String imgUrl = rs.getString("imgUrl");
                int price = rs.getInt("price");
                products.add(new Product(id, name, imgUrl, special, producer, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }


    @Override
    public boolean deleteProduct(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateProduct(Product user) throws SQLException {
        return false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
