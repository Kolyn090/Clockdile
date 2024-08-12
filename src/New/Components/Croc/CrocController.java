package New.Components.Croc;

public class CrocController {
    private final CrocView view;
    private CrocModel model;

    public CrocController(int width, int height) {
        view = new CrocView(width, height);
        model = new CrocModel();
    }

    public CrocView getView() {
        return view;
    }
}
