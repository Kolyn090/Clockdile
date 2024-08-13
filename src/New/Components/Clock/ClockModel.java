package New.Components.Clock;

import java.util.function.IntUnaryOperator;

public class ClockModel {
    private final IntUnaryOperator stepFunc;
    private int elapsedTime = 0;
    private int getSeconds() {
        return (elapsedTime / 1000) % 60;
    }
    private int getMinutes() {
        return (elapsedTime / 60000) % 60;
    }
    private int getHours() {
        return elapsedTime / 3600000;
    }

    public ClockModel(IntUnaryOperator stepFunc) {
        this.stepFunc = stepFunc;
    }

    public void step() {
        elapsedTime = stepFunc.applyAsInt(elapsedTime);
    }

    public String getClockText() {
        return toTwoDigits(getHours()) + ":" +
                toTwoDigits(getMinutes()) + ":" +
                toTwoDigits(getSeconds());
    }

    private String toTwoDigits(int number) {
        return String.format("%02d", number);
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}
