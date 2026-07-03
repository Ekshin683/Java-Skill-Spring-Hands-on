// InventoryManagementTest.java
public class InventoryManagementTest {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Add products
        manager.addProduct(new Product("P001", "Wireless Mouse", 50, 19.99));
        manager.addProduct(new Product("P002", "Mechanical Keyboard", 30, 79.99));
        manager.addProduct(new Product("P003", "USB-C Hub", 100, 24.99));

        System.out.println();

        // Update a product (e.g. stock sold, price changed)
        manager.updateProduct("P002", 25, 74.99);

        System.out.println();

        // Look up a product
        Product found = manager.getProduct("P001");
        System.out.println("Lookup P001: " + found);

        System.out.println();

        // Delete a discontinued product
        manager.deleteProduct("P003");

        System.out.println();

        // List remaining inventory
        System.out.println("Current inventory:");
        for (Product p : manager.getAllProducts()) {
            System.out.println("  " + p);
        }
    }
}