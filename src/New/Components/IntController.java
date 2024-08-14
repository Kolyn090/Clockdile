package New.Components;

import java.util.ArrayList;

public class IntController implements Controller<Integer> {
    private final ArrayList<Model<Integer>> models;

    public IntController() {
        models = new ArrayList<>();
    }

    public void increment() {
        for (Model<Integer> model: models) {
            model.update(model.getData() + 1);
        }
    }

    public void decrement() {
        for (Model<Integer> model: models) {
            model.update(model.getData() - 1);
        }
    }

    @Override
    public void addModel(Model<Integer> model) {
        models.add(model);
    }

    @Override
    public void updateModels(Integer data) {
        for (Model<Integer> model: models) {
            model.update(data);
        }
    }
}
