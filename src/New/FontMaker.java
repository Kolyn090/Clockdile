package New;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public final class FontMaker {
    public static Font makeFont(int fontSize, String fontDirectory) {
        Font temp = null;
        try {
            temp = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(FontMaker.class
                            .getClassLoader()
                            .getResourceAsStream(fontDirectory)));
            temp = temp.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
