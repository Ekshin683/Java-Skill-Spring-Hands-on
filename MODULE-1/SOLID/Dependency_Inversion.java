// Dependency Inversion Principle (DIP): It states that high-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.

public interface Database {
    void save(String item);
}

// Low-level module A
public class MySQLDatabase implements Database {
    public void save(String item) {
        System.out.println("Saving to MySQL: " + item);
    }
}

// Low-level module B — can be swapped in without touching OrderService
public class MongoDatabase implements Database {
    public void save(String item) {
        System.out.println("Saving to MongoDB: " + item);
    }
}

// High-level module depends on the abstraction, not a concrete class
public class OrderService {
    private Database db; // injected — no new keyword here

    public OrderService(Database db) { // dependency injection
        this.db = db;
    }

    public void placeOrder(String item) {
        db.save(item);
    }
}

// Usage
Database db = new MySQLDatabase(); // swap to MongoDatabase anytime
OrderService service = new OrderService(db);
service.placeOrder("Laptop");