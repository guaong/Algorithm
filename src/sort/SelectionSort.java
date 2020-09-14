package sort;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5,6,11,77,32,98,666,78,54,32,45,2};
        SelectionSort.sort(arr);
    }

    public static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++){
            // 用于记录每次最小值的下标
            int index = i;
            for (int j = i+1; j < arr.length; j++){
                if (arr[index] > arr[j]) index = j;
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        for (int i:arr) System.out.print(i + " ");
    }

}
