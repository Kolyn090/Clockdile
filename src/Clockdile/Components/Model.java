package Clockdile.Components;

public interface Model<T> {
    void addObserver(View<T> view);
    void updateViews();
    void update(T data);
    T getData();
}
