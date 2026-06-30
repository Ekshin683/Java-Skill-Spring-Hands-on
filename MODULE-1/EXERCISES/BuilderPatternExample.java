// package MODULE-1.EXERCISES;
public class Computer {
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final boolean hasWifi;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.hasWifi = builder.hasWifi;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage
                + ", GPU=" + (gpu == null ? "Integrated" : gpu)
                + ", WiFi=" + hasWifi + "]";
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private boolean hasWifi;

        public Builder cpu(String cpu) { this.cpu = cpu; return this; }
        public Builder ram(String ram) { this.ram = ram; return this; }
        public Builder storage(String storage) { this.storage = storage; return this; }
        public Builder gpu(String gpu) { this.gpu = gpu; return this; }
        public Builder wifi(boolean hasWifi) { this.hasWifi = hasWifi; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderPatternExample {
    public static void main(String[] args){
        Computer cp = new Computer.Builder().cpu("Lenove").ram("12GB").storage("512").wifi(true).build();
        System.out.println(cp);
    }
}
