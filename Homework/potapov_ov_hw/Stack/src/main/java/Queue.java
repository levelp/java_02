import java.util.ArrayList;


public class Queue <T> {

    ArrayList <T> queueBase = new ArrayList<>();

    public void put(T e) {
        queueBase.add(e);
    }

    public T get() {
        T e = queueBase.get(0);
        queueBase.remove(e);
        return e;
    }

    public boolean isEmpty() {
        return queueBase.size()==0;
    }
}
