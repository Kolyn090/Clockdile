package New;

public interface IClock {
    boolean hasStarted();
    void start();
    void stop();
    void reset();
}