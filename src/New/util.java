package New;

import java.util.function.Function;

public class util {
    public static <T, R> R[] map(T[] array, Function<T, R> mapper, R[] resultArray) {
        for (int i = 0; i < array.length; i++) {
            resultArray[i] = mapper.apply(array[i]);
        }
        return resultArray;
    }
}
