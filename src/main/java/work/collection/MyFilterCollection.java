package work.collection;


import java.util.Arrays;

public class MyFilterCollection {
    public Object[] filter(Object[] arr, Filter filter) {
        Object[] result = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = filter.apply(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strings = {"hello", "world"};
        MyFilterCollection collection = new MyFilterCollection();
        FilterImpl filter = new FilterImpl();
        Object[] filtered = collection.filter(strings, filter);
        System.out.println(Arrays.toString(filtered));
    }
}
