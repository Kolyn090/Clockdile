package Clockdile;

import java.util.HashMap;
import java.util.Map;
import Clockdile.PathUtil;

public class DirectoryManager {
    public static final String basePath = PathUtil.getLibPath();

    public static final String[] waterImageDirectories = new String[] {
            basePath + "/Images/Water/0.png",
            basePath + "/Images/Water/1.png",
            basePath + "/Images/Water/2.png",
            basePath + "/Images/Water/3.png",
            basePath + "/Images/Water/4.png",
            basePath + "/Images/Water/5.png",
            basePath + "/Images/Water/6.png",
            basePath + "/Images/Water/7.png",
            basePath + "/Images/Water/8.png",
    };

    public static final String crocImageDirectory = basePath + "/Images/TheClockdile.png";

    public static final Map<String, String> leftEye = new HashMap<String, String>() {{
        put("Closed", basePath + "/Images/ResumeButton-Closed.png");
        put("Open", basePath + "/Images/ResumeButton-Open.png");
    }};

    public static final Map<String, String> rightEye = new HashMap<String, String>() {{
        put("Closed", basePath + "/Images/ResetButton-Closed.png");
        put("Open", basePath + "/Images/ResetButton-Open.png");
    }};

    public static final String leftArrow = basePath + "/Images/LeftButton.png";
    public static final String rightArrow = basePath + "/Images/RightButton.png";

    public static final String digitalClock = "Clockdile/Fonts/digital-7 (mono).ttf";
    public static final String avocado = "Clockdile/Fonts/avocado.ttf";

    public static final String saveFile = basePath + "/AppSave.txt";

    public static final String soundFile = basePath + "/crocodile-sound.wav";
}
