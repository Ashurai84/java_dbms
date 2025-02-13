import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepo {

    private Connection connection = ConnectionManager.getConnection();
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Product addProduct(Product p) {
        String query = "insert into product1(id, price, brand) values(?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getBrand());
            int retVal = ps.executeUpdate();
            if (retVal == 1) {
                System.out.println("Record added to product1 table");
                return p;  // Return the product after it is added
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product updateProduct(int id, Product p) {
        String query = "update product1 set price = ?, brand = ? where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, p.getPrice());
            ps.setString(2, p.getBrand());
            ps.setInt(3, id);
            int retVal = ps.executeUpdate();
            if (retVal == 1) {
                System.out.println("Product updated");
                return p; // Return the updated product
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        String query = "delete from product1 where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int retVal = ps.executeUpdate();
            if (retVal == 1) {
                System.out.println("Product deleted");
                return null; // Return null or indicate successful deletion
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product deleteProduct(String description) {
        String query = "delete from product1 where description = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, description);
            int retVal = ps.executeUpdate();
            if (retVal == 1) {
                System.out.println("Product with description deleted");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        String query = "select * from product1 where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("id");
                double price = rs.getDouble("price");
                String brand = rs.getString("brand");
                return new Product(productId, price, brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> listAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "select * from product1";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String brand = rs.getString("brand");
                Product p1 = new Product(id, price, brand);
                products.add(p1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> listProductsByCategory(Category category) {
        List<Product> products = new ArrayList<>();
        String query = "select * from product1 where category = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, category.name());  
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String brand = rs.getString("brand");
                Product p1 = new Product(id, price, brand);
                products.add(p1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
