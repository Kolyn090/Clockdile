package New.Components;

import java.util.ArrayList;

public class IntModel implements Model<Integer> {
    private Integer data;
    private final int min;
    private final int max;
    private final ArrayList<View<Integer>> observers;

    public IntModel(int min, int max) {
        observers = new ArrayList<>();
        this.min = min;
        this.max = max;
        data = min;
    }

    @Override
    public void addObserver(View<Integer> view) {
        observers.add(view);
    }

    @Override
    public void updateViews() {
        for (View<Integer> view: observers) {
            view.update(data);
        }
    }

    @Override
    public void update(Integer data) {
        this.data = data;
        if (data < min) this.data = min;
        if (data > max) this.data = max;
        updateViews();
    }

    @Override
    public Integer getData() {
        return data;
    }
}
