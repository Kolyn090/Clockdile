package New;

import java.util.HashMap;
import java.util.Map;

public class DirectoryManager {
    public static final String[] waterImageDirectories = new String[] {
            "lib/Images/Water/0.png",
            "lib/Images/Water/1.png",
            "lib/Images/Water/2.png",
            "lib/Images/Water/3.png",
            "lib/Images/Water/4.png",
            "lib/Images/Water/5.png",
            "lib/Images/Water/6.png",
            "lib/Images/Water/7.png",
            "lib/Images/Water/8.png",
    };

    public static final String crocImageDirectory = "lib/Images/TheClockdile.png";

    public static final Map<String, String> leftEye = new HashMap<>() {{
        put("Closed", "lib/Images/ResumeButton-Closed.png");
        put("Open", "lib/Images/ResumeButton-Open.png");
    }};

    public static final Map<String, String> rightEye = new HashMap<>() {{
        put("Closed", "lib/Images/ResetButton-Closed.png");
        put("Open", "lib/Images/ResetButton-Open.png");
    }};

    public static final String leftArrow = "lib/Images/LeftButton.png";
    public static final String rightArrow = "lib/Images/RightButton.png";

    public static final String digitalClock = "New/Fonts/digital-7 (mono).ttf";
    public static final String avocado = "New/Fonts/avocado.ttf";
}
