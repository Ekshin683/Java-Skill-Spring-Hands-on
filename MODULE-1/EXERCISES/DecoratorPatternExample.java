public interface Notifier {
    void send(String message);
}

// EmailNotifier.java
public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

// NotifierDecorator.java
public abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    public void send(String message) {
        wrappedNotifier.send(message); // delegate first
    }
}

// SMSNotifierDecorator.java
public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS: " + message);
    }
}

// SlackNotifierDecorator.java
public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Slack message: " + message);
    }
}

public class DecoratorPatternExample {
    public static void main(String[] args){
        Notifier notify = new EmailNotifier();
        notify = new SMSNotifierDecorator(notify);
        notify = new SlackNotifierDecorator(notify);

        notify.send("These is test Notification");
    }
}
