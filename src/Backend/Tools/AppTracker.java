package Backend.Tools;

import Backend.Audio.Sound;
import Backend.TimeTracker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppTracker {

    /*
        AppSave.txt:
        currentClock
        intervalSetting
        volumeSetting
        date
        waterAmount
     */

    private int currentClock = 0; // 0: stopwatch, 1: countdown, 2: realtime
    private int currentPalette = 0; // 0: water, 1: setting

    private final WaterReminder waterReminder = new WaterReminder();
    private final ModeSetting modeSetting = new ModeSetting();
    private final TimeTracker timeTracker = new TimeTracker(modeSetting, new Sound());

    public AppTracker() {
        readAppSave();
    }

    private void readAppSave() {
        String[] tuples = readTuples();
        int clock = Integer.parseInt(tuples[0]);
        if (clock == 2) {
            clock = 0;
        }
        currentClock = clock;
        rewriteCurrenClock(clock+"");
        modeSetting.setInterval(Integer.parseInt(tuples[1]));
        modeSetting.setVolume(Integer.parseInt(tuples[2]));
        String currentDate = new SimpleDateFormat("MMMMM dd, yyyy").format(Calendar.getInstance().getTime());
        String savedDate = tuples[3];
        if (currentDate.equals(savedDate)) {
            waterReminder.setWaterTracker(Integer.parseInt(tuples[4]));
        } else {
            waterReminder.setWaterTracker(0);
            rewriteDate(currentDate);
        }
    }

    private String[] readTuples() {
        String[] tuples = new String[5];
        //InputStream inputStream = getClass().getResourceAsStream("AppSave.txt");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("lib/AppSave.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tuples[0] = line;
        int i = 1;
        while (line != null) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tuples[i] = line;
            i++;
            if (i == 5) {
                break;
            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tuples;
    }

    public void rewriteCurrenClock(String data) {
        rewriteAppSave(0, data);
    }

    public void rewriteIntervalSetting(String data) {
        rewriteAppSave(1, data);
    }

    public void rewriteVolumeSetting(String data) {
        rewriteAppSave(2, data);
    }

    public void rewriteDate(String data) {
        rewriteAppSave(3, data);
    }

    public void rewriteWaterAmount(String data) {
        rewriteAppSave(4, data);
    }

    private void rewriteAppSave(int index, String data) {
        /*
        tuples[index] = data;
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < tuples.length-1; i++) {
            content.append(tuples[i]).append("\n");
        }
        content.append(tuples[tuples.length - 1]);

        try {
            assert bw != null;
            bw.write(content.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        replaceLines(index, data);
    }

    public static void replaceLines(int index, String data) {
        try {
            int count = 0;
            // input the (modified) file content to the StringBuffer "input"
            StringBuilder inputBuffer = new StringBuilder();
            String line;

            BufferedReader file = new BufferedReader(new FileReader("lib/AppSave.txt"));

            while ((line = file.readLine()) != null) {
                if (count == index) {
                    line = data; // replace the line here
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                count++;
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("lib/AppSave.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public int getCurrentClock() {
        return currentClock;
    }

    public void setCurrentClock(int currentClock) {
        this.currentClock = currentClock;
    }

    public int getCurrentPalette() {
        return currentPalette;
    }

    public void setCurrentPalette(int currentPalette) {
        this.currentPalette = currentPalette;
    }

    public TimeTracker getTimeTracker() {
        return timeTracker;
    }

    public WaterReminder getWaterReminder() {
        return waterReminder;
    }

    public ModeSetting getModeSetting() {
        return modeSetting;
    }
}
