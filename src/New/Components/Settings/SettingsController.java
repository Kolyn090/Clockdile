package New.Components.Settings;

public class SettingsController {
    private final SettingsView view;
    private final SettingsModel model;

    public SettingsController() {
        model = new SettingsModel(60);
        view = new SettingsView(this::decInterval, this::incInterval);
    }

    private void decInterval() {
        model.decInterval();
        view.setIntervalNumberText(model.getInterval());
    }

    private void incInterval() {
        model.incInterval();
        view.setIntervalNumberText(model.getInterval());
    }

    public SettingsView getView() {
        return view;
    }
}
