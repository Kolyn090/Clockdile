package New.Components;

public interface View<T>  {
    void update(T data);
    void reset();
    String getName();
}
