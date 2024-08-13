package New.Components.Clock;

import javax.swing.*;
import java.util.function.IntUnaryOperator;

public abstract class ClockController {
    protected ClockView view;
    protected ClockModel model;
    protected Timer timer;

    public ClockController(IntUnaryOperator stepFunc) {
        view = new ClockView();
        model = new ClockModel(stepFunc);
    }

    public ClockView getView() {
        return view;
    }
}
