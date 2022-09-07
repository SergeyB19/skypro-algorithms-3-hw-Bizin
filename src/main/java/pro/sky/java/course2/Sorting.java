package pro.sky.java.course2;

import java.util.Random;


public class Sorting {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        double timeForBubbleSort = timeForBubbleSort(5);
        System.out.println("Среднее время сортировки пузырьком: " + timeForBubbleSort + " мс. ");
        double timeForSelectionSort = timeForSelectionSort(5);
        System.out.println("Среднее время сортировки выбором: " + timeForSelectionSort + " мс. ");
        double timeForInsertionSort = timeForInsertionSort(5);
        System.out.println("Среднее время сортировки вставками: " + timeForInsertionSort + " мс. ");
    }

    private static double timeForBubbleSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sortBubble(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

    private static double timeForSelectionSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sortBubble(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }
    private static double timeForInsertionSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sortBubble(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(100, -100);
        }
        return array;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }


    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minElementIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr,i,minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
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
