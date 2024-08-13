package New.Components;

import javax.swing.*;
import java.util.ArrayList;

public interface Model<T> {
    void addObserver(View<T> view);
    void updateViews();
    void update(T data);
    T getData();
}
