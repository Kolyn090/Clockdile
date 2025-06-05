package Clockdile;

import java.io.File;

public class PathUtil {
    public static String getLibPath() {
        // Gets the path where the app (JAR or EXE) is located
        String basePath = new File("").getAbsolutePath();
        return basePath + File.separator + "lib";
    }

    public static void main(String[] args) {
        String filePath = getLibPath() + File.separator + "AppSave.txt";
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("File found at: " + file.getAbsolutePath());
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }
}
