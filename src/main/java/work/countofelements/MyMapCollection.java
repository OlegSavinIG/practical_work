package work.countofelements;

import java.util.HashMap;
import java.util.Map;

public class MyMapCollection {
    public <T> Map<T, Integer> mapCreator(T[] arr) {
        Map<T, Integer> result = new HashMap<>();
        for (T object : arr) {
            result.put(object, result.getOrDefault(object, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 1, 1, 2, 3, 3, 5, 6};
        MyMapCollection mapCollection = new MyMapCollection();
        Map<Integer, Integer> result = mapCollection.mapCreator(numbers);
        System.out.println(result);
    }
}
