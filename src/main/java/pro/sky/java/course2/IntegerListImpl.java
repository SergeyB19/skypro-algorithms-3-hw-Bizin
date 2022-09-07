package pro.sky.java.course2;

import java.lang.constant.Constable;
import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private static final int INITIAL_SIZE = 15;
    private final Integer[] data;

    private int capacity;

    public IntegerListImpl() {
        data = new Integer[INITIAL_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительным");
        }
        data = new Integer[n];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]!");
        }
        if (capacity == data.length) { // todo: в 2.16 вместо выброса исключения сделать расширение массива
            throw new IllegalArgumentException("Массив полон");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        System.arraycopy(data, index, data, index + 1, capacity - index);
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Constable set(int index, Integer item) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]!");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        return data[index] = Integer.valueOf(item);
    }

    @Override
    public Integer remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Нет такого элемента");
        }
        capacity++;
        return remove(indexForRemoving);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity)!");
        }
        Integer removedItem = data[index];
        if (index + 1 < capacity) {
            System.arraycopy(data, index + 1, data, index, capacity - index - 1);
        }
        capacity--;
        data[capacity] = null;
        return removedItem;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в  список");
        }

        Integer[] arrayForSearch = toArray();
        sortInsertion(arrayForSearch);
        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }


    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = capacity - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]!");
        }
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(data,null);
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[capacity];
        System.arraycopy(data,0,array,0,capacity);
        return array;
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}

