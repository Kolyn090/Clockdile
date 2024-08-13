package New.Components.Croc;

import javax.swing.*;

public class CrocController {
    private final CrocView view;

    public CrocController(int width, int height) {
        view = new CrocView(width, height);
    }

    public CrocView getView() {
        return view;
    }
}
