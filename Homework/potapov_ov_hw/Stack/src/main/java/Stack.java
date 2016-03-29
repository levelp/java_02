
import java.util.ArrayList;


public class Stack <T> {

    int top=-1;
    ArrayList<T> stackBase = new ArrayList<T>();


    public void push(T e) {
        stackBase.add(++top, e);

    }

    public T pop() {
        T e = stackBase.get(top);
        remove(e);
        return e;

    }

    private void remove(T e){
        stackBase.remove(e);
        top--;
    }

    public boolean isEmpty(){
        return top==-1;
    }
}
