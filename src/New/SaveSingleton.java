package New;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaveSingleton {

    /*
        AppSave.txt:
        currentClock
        interval
        volume
        date
        waterAmount
     */

    private static SaveSingleton instance;
    private int currentClock;
    private int interval;
    private int volume;
    private int waterAmount;

    private SaveSingleton() {
        readAppSave();
    }
    public static synchronized SaveSingleton instance() {
        if (instance == null) {
            instance = new SaveSingleton();
        }
        return instance;
    }

    private void readAppSave() {
        String[] tuples = readTuples();
        currentClock = Integer.parseInt(tuples[0]);
        interval = Integer.parseInt(tuples[1]);
        volume = Integer.parseInt(tuples[2]);
        String currentDate = new SimpleDateFormat("MMMMM dd, yyyy").format(Calendar.getInstance().getTime());
        String savedDate = tuples[3];
        if (currentDate.equals(savedDate)) {
            waterAmount = Integer.parseInt(tuples[4]);
        } else {
            waterAmount = 0;
            writeDate(currentDate);
        }
    }

    private String[] readTuples() {
        String[] tuples = new String[5];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(DirectoryManager.saveFile);
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

    public void writeClock(ClockMode mode) {
        int data = 0;
        if (mode == ClockMode.CountDown) {
            data = 1;
        } else if (mode == ClockMode.RealTime) {
            data = 2;
        }
        writeAppSave(0, ""+data);
        currentClock = data;
    }

    public void writeInterval(int data) {
        writeAppSave(1, ""+data);
        interval = data;
    }

    public void writeVolume(int data) {
        writeAppSave(2, ""+data);
        volume = data;
    }

    private void writeDate(String data) {
        writeAppSave(3, data);
    }

    public void writeWaterAmount(int data) {
        writeAppSave(4, ""+data);
        waterAmount = data;
    }

    private void writeAppSave(int index, String data) {
        replaceLine(index, data);
    }

    private static void replaceLine(int index, String data) {
        try {
            int count = 0;
            // input the (modified) file content to the StringBuffer "input"
            StringBuilder inputBuffer = new StringBuilder();
            String line;

            BufferedReader file = new BufferedReader(new FileReader(DirectoryManager.saveFile));

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
            FileOutputStream fileOut = new FileOutputStream(DirectoryManager.saveFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public ClockMode getCurrentClockMode() {
        if (currentClock == 0) {
            return ClockMode.StopWatch;
        } else if (currentClock == 1) {
            return ClockMode.CountDown;
        } else {
            return ClockMode.RealTime;
        }
    }

    public int getInterval() {
        return interval;
    }

    public int getVolume() {
        return volume;
    }

    public int getWaterAmount() {
        return waterAmount;
    }
}
