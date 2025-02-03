package work.collection;


public class MyFilterCollection {
    public Object[] filter(Object[] arr, Filter filter){
        Object[] result = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = filter.apply(arr[i]);
        }
        return result;
    }
}
