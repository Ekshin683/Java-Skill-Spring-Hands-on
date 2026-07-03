// Liskov Substitution Principle (LSP): It states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() { return width * height; }
}

public class Square implements Shape {
    private int side;

    public Square(int side) { this.side = side; }

    public int getArea() { return side * side; }
}

// Works correctly for any Shape — true substitutability
public void printArea(Shape shape) {
    System.out.println("Area: " + shape.getArea());
}
