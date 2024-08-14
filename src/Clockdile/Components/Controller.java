package Clockdile.Components;

public interface Controller<T> {
    void addModel(Model<T> model);
    void updateModels(T data);
}
