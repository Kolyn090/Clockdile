package New.Components;

import javax.swing.*;

public interface Controller<T> {
    void addModel(Model<T> model);
    void updateModels(T data);
}
