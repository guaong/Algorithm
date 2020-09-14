package sort;

/**
 * 快速排序 没有达到要求
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 24, 123, 333, 445, 521, 663, 723, 856, 1765, 3312};
//        for (int i = 0; i < 100; i++) arr[i] = (int)(Math.random()*100);
        long start = System.currentTimeMillis();
        QuickSort.merger(arr, 0, arr.length - 1);
        for (int i : arr
        ) {
            System.out.print(i + " ");
        }
        System.out.println("\n时长:" + (System.currentTimeMillis() - start));
    }

    private static void merger(int[] arr, int left, int right) {
        // 递归到剩余一个时停止递归
        // 由于会出现left = flag(flag左侧没有任何元素),因此merger(arr, left, flag - 1)会导致left > right的情况出现
        if (left >= right) return;
        // 每次以第一个值为flag
        int flag = left;
        // 用于交换位置的临时变量
        int temp;
        // 会变化的left,right
        // 即currentLeft的左边的值已经和flag比较过,并且一定小于flag的值
        // 所以下一轮比较就可以不再重复比较了,直接从currentLeft开始比较
        int currentLeft = left;
        int currentRight = right;
        while (currentLeft + 1 >= flag && currentRight - 1 >= flag) {
            // 从后向前找第一个比temp小的
            int i = currentRight;
            while (i >= flag) {
                if (arr[flag] > arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[flag];
                    arr[flag] = temp;
                    flag = i;
                    // -- 和 =i 一样
                    currentRight--;
                    break;
                }
                // 不符合就左移
                currentRight = i;
                i--;
            }
            // 从前向后找第一个比temp大的
            int j = currentLeft;
            while (j < flag) {
                if (arr[flag] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[flag];
                    arr[flag] = temp;
                    flag = j;
                    // ++ 和 =j 一样
                    currentLeft++;
                    break;
                }
                // 不符合就右移
                currentLeft = j;
                j++;
            }
        }
        merger(arr, left, flag - 1);
        merger(arr, flag + 1, right);
    }
}