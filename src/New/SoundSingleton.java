package New;

public class SoundSingleton {
    private static SoundSingleton instance;
    private SoundSingleton() {

    }
    public static synchronized SoundSingleton instance() {
        if (instance == null) {
            instance = new SoundSingleton();
        }
        return instance;
    }
}
