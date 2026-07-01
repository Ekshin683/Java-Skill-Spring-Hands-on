// InventoryManager.java
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class InventoryManager {
    // productId -> Product, gives O(1) average lookup/insert/delete
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product " + product.getProductId() + " already exists. Use update instead.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product);
    }

    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product " + productId + " not found.");
            return;
        }
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
        System.out.println("Updated: " + product);
    }

    public void deleteProduct(String productId) {
        Product removed = inventory.remove(productId);
        if (removed == null) {
            System.out.println("Product " + productId + " not found, nothing deleted.");
        } else {
            System.out.println("Deleted: " + removed);
        }
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public Collection<Product> getAllProducts() {
        return inventory.values();
    }
}