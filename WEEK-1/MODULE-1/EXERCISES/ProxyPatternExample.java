public interface Image {
    void display();
}
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer(); 
    }

    private void loadFromServer() {
        System.out.println("Loading " + fileName + " from remote server...");
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
public class ProxyImage implements Image {
    private RealImage realImage; // lazily initialized
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // load only when needed
        } else {
            System.out.println("Loading " + fileName + " from cache.");
        }
        realImage.display();
    }
}

public class ProxyPatternExample {
    public static void main(String[] args){
        Image image1 = new RealImage("image1.jpg");
        System.out.println("Image created");

        image1.display();
        System.out.println();
        image1.display();
    }
}
