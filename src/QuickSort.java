public class QuickSort {
    static int partition(long array[], int low, int high) {

        // choose the rightmost element as pivot
        long pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                //swap
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        long temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return (i + 1);
    }

     long [] quickSort(long array[], int low, int high) {
        if (low < high) {
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
        return array;
    }
}
