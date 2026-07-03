public interface PaymentProcessor {
    void processPayment(double amount);
}
public class PayPalGateway {
    public void makePayPalPayment(double amountInUsd) {
        System.out.println("Processing $" + amountInUsd + " via PayPal API");
    }
}
public class StripeGateway {
    public void charge(int amountInCents) {
        System.out.println("Charging " + amountInCents + " cents via Stripe API");
    }
}
public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    public void processPayment(double amount) {
        payPalGateway.makePayPalPayment(amount);
    }
}
public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    public void processPayment(double amount) {
        int amountInCents = (int) Math.round(amount * 100);
        stripeGateway.charge(amountInCents);
    }
}

public class AdapterPatternExample {
    public static void main(String[] args){
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        paypal.processPayment(1500.00);
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(1000.00);
    }
    
}
