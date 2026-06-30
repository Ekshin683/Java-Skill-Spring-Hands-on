import java.util.*;
public interface Observer {
    void update(String stockSymbol, double price);
}
public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
public class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }

    public void setStockPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        notifyObservers();
    }
}

// MobileApp.java
public class MobileApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("[MobileApp] " + stockSymbol + " is now $" + price);
    }
}

// WebApp.java
public class WebApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("[WebApp] " + stockSymbol + " is now $" + price);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args){
        StockMarket stock = new StockMarket();
        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        stock.registerObserver(mobile);
        stock.registerObserver(web);

        stock.setStockPrice("ASPL", 300.65);

        stock.deregisterObserver(web);
        System.out.print("Web deregister");
        stock.setStockPrice("ASPL", 305.75);
    }
} 
