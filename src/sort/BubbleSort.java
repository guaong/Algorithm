package sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2, 12, 43, 56, 32, 12, 14};
        BubbleSort.sort(arr);
    }

    public static void sort(int[] arr) {
        // 记录已经排过序的位置,即每次可以少比较一次
        int end = arr.length;
        // 每次取一个和其他比较
        for (int k = 0; k < arr.length; k++) {
            for (int i = 1; i < end; i++) {
                // 小则交换位置
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }
            end--;
        }
        for (int i : arr) System.out.print(i + " ");
    }

}
