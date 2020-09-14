package sort;

/**
 * 直接插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {1,4,56,3,12,54,76,98,100,98,12,13,87,5,3,23};
        InsertionSort.sort(arr);
    }

    public static void sort(int[] arr){
        // 从下标为1的开始
        for (int i = 1; i < arr.length; i++){
            // 只考虑已插入的,即之和下标i之前的已经排序好的比较
            for (int j = i; j > 0; j--){
                if (arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        for (int i:arr) System.out.print(i + " ");
    }

}
