package productDAO;

import model.Customer;
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
    private static final String SELECT_PRODUCT_BY_ID = "select id,name,imgUrl,price,special,producer from products where id = ?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products where special = ?";
    private static final String SORT_PRODUCT_BY_PRODUCER = "select * from products where special = ? and producer = ?";
    private static final String SELECT_SPECSMARTPHONE = "select * from specsmartphone where productId = ?";
    private static final String SELECT_SPECSLAPTOP = "select * from speclaptop where productId = ?";
    private static final String SELECT_SPECSTABLET = "select * from spectablet where productId = ?";
    private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,imgUrl= ?, price = ?, special = ?, producer = ? where id = ?;";
    private static final String UPDATE_SPECS_SM = "update specsmartphone set screen = ?, operaSystem =?, cameraFont = ?, " +
            "cameraEnd = ?, cpu = ?, ram = ?, memory = ?, sim = ?, pin = ? where productId = ?;";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customer";
    private static final String INSERT_CUSTOMERS = "insert into customer (Name, Email, Address, phoneNumber) values (?, ?, ?, ?)";

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
                String producer = rs.getString("producer");
                product = new Product(id, name, imgUrl, specil,producer, price);
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

    public List<String[]> selectSpecLaptop(String productId) {
        List<String[]> specLaptop = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECSLAPTOP);) {
            preparedStatement.setString(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                specLaptop.add(new String[]{"CPU", rs.getString("cpu")});
                specLaptop.add(new String[]{"RAM", rs.getString("ram")});
                specLaptop.add(new String[]{"DRIVE", rs.getString("drive")});
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return specLaptop;
    }

    public List<String[]> selectSpecTablet(String productId) {
        List<String[]> specTablet = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPECSTABLET);) {
            preparedStatement.setString(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                specTablet.add(new String[]{"SCREEN", rs.getString("screen")});
                specTablet.add(new String[]{"OPERA SYSTEM", rs.getString("operaSystem")});
                specTablet.add(new String[]{"CPU", rs.getString("cpu")});
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return specTablet;
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
                int price = rs.getInt("price");
                products.add(new Product(id, name, imgUrl, special, price));
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
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
            statement.setString(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getImgUrl());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getSpecial());
            statement.setString(5, product.getProducer());
            statement.setString(6, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean updateSpecSM(SpecSmartphone specSmartphone) throws SQLException {
        boolean rowUpdated;
        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SPECS_SM);) {
            statement.setString(1,specSmartphone.getScreen());
            statement.setString(2,specSmartphone.getOperaSystem());
            statement.setString(3,specSmartphone.getCameraFont());
            statement.setString(4,specSmartphone.getCameraEnd());
            statement.setString(5,specSmartphone.getCpu());
            statement.setString(6,specSmartphone.getRam());
            statement.setString(7,specSmartphone.getMemory());
            statement.setString(8,specSmartphone.getSim());
            statement.setString(9,specSmartphone.getPin());
            statement.setString(10,specSmartphone.getProductId());
            System.out.println(statement.toString());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public void insertCustomer(Customer customer){
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4,Integer.parseInt(customer.getPhoneNumber()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<Customer> selectAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = "" + rs.getInt("Id");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String phoneNumber ="" + rs.getInt("phoneNumber");
                customers.add(new Customer(id,name,email,address,phoneNumber));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
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
