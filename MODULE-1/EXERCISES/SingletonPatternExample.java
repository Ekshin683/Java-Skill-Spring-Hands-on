public class Logger {
    private static Logger instance;
    private int logCount = 0;

    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }
}
public class SingletonPatternExample{
    public static void main(String[] args){
        Logger log1 = Logger.getInstance();
        log1.log("Applications Started");
        Logger log2 = Logger.getInstance();
        log2.log("User Logged In");
    }
}