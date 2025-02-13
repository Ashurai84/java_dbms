import java.util.List;

public interface ProductRepo {
    public Product addProduct(Product p);
    public Product updateProduct(int id, Product p);
    public Product deleteProduct(int id);
    public Product deleteProduct(String description);
    public Product getProductById(int id);
    public List<Product> listAllProducts();
    public List<Product> listProductsByCategory(Category category);
}
