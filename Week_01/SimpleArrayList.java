import java.util.Arrays;

/**
 * This is a demo class to illustrate how to implement add/remove method in array.
 * Reference to Java ArrayList.
 */
public class SimpleArrayList<E> {
    private static final int DEFAULT_INIT_CAPACITY = 10;
    private static final int DEFAULT_GROW_LENGTH = 5;

    private Object[] elementObjects;
    private int size = 0;

    public SimpleArrayList() {
        elementObjects = new Object[DEFAULT_INIT_CAPACITY];;
    }

    public SimpleArrayList(int initCapacity) {
        elementObjects = new Object[initCapacity];
    }

    public boolean add(E e) {
        ensureCapacity(size + 1);
        elementObjects[size++] = e;
        return true;
    }

    private void ensureCapacity(int minCapacity) {
        if (elementObjects.length < minCapacity) {
            grow(minCapacity + DEFAULT_GROW_LENGTH);
        }
    }

    private void grow(int targetSize) {
        Arrays.copyOf(elementObjects, targetSize);
    }

    public boolean add(E e, int index) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementObjects, index, elementObjects, index + 1, size - index);
        elementObjects[index] = e;
        size++;
        return true;
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
    }

    public E remove(int index) {
        rangeCheck(index);
        E e = (E) elementObjects[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementObjects, index + 1, elementObjects, index, numMoved);
        }
        elementObjects[--size] = null;
        return e;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) elementObjects[index];
    }

    public void printOut() {
        System.out.println(Arrays.toString(elementObjects));
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> simpleList = new SimpleArrayList<>();
        simpleList.add(1);
        simpleList.add(2);
        simpleList.add(3);
        simpleList.add(4);
        simpleList.add(5);
        simpleList.printOut();

        simpleList.add(3, 3);
        simpleList.printOut();

        simpleList.remove(4);
        simpleList.printOut();

        simpleList.add(3, -1);
    }
}