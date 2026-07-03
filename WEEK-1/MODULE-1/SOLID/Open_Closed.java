//Open-Closed Principle: Here the class is open for extension but closed for modification. Which means we can extend code without modifying exixting code.
// here you can see the interface is open for extension
public interface Open_Closed {
    double apply(double price);
}
//here the code says we extend the interface and implement the apply method in different classes without modifying the existing code of interface
public class RegularDiscount implements Open_Closed {
    public double apply(double price) { return price * 0.95; }
}

public class PremiumDiscount implements Open_Closed {
    public double apply(double price) { return price * 0.80; }
}
