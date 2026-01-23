import java.util.ArrayList;

public class MyStack<E> {

    private ArrayList<E> stack;
    private E top;
    private int height;

    public MyStack() {
        stack = new ArrayList<E>();
        top = null;
        height = 0;
    }

    public boolean empty() {
        return height == 0;
    }

    public boolean push(E obj) {
        stack.add(obj);
        top = obj;
        height++;
        return true;
    }

    public E pop() {
        if (!empty()) {
            E removed = stack.get(height - 1);
            stack.remove(height - 1);
            height--;
            if (height >= 1) {
                top = stack.get(height - 1);
            }
            return removed;
        }
        return null;
    }

    public E peek() {
        if (!empty()) {
            return top;
        }
        return null;
    }

    public int getHeight() {
        return height;
    }
}
