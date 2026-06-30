// Interface Segregation Principle (ISP): It states that no client should be forced to depend on methods it does not use. Instead of one fat interface, many small interfaces are preferred based on groups of methods with each interface serving one submodule.

// Focused interfaces — each does one thing
public interface Workable {
    void work();
}

public interface Feedable {
    void eat();
}

public interface Restable {
    void sleep();
}

// Human needs all three
public class Human implements Workable, Feedable, Restable {
    public void work()  { System.out.println("Human working..."); }
    public void eat()   { System.out.println("Human eating..."); }
    public void sleep() { System.out.println("Human sleeping..."); }
}

// Robot only implements what it actually does
public class Robot implements Workable {
    public void work() { System.out.println("Robot working..."); }
}
