package New.Components.Water;

public class WaterController {
    private final WaterView view;
    private final WaterModel model;

    public WaterController() {
        view = new WaterView();
        model = new WaterModel();
    }

    public void increaseAmount() {
        model.increaseAmount();
        view.changeAmount(model.getAmount());
    }

    public void decreaseAmount() {
        model.decreaseAmount();
        view.changeAmount(model.getAmount());
    }

    public WaterView getView() {
        return view;
    }
}
