package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
 * 没有考虑性能,使用了list
 */
public class MergeSort {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            arr.add((int)(Math.random()*1000));
        }
        MergeSort.merge(arr, 0, arr.size() - 1);
        System.out.println(arr);
    }

    // 分治
    public static void merge(List<Integer> arr, int left, int right){
        // 如果left == right说明已经不可再分割
        if (left == right) return;
        // 获取中间的下标
        int mid = (left + right) / 2;
        // 数组左半部分再分治
        merge(arr, left, mid);
        // 数组右半部分再分治
        merge(arr, mid + 1, right);
        // 进行排序
        sort(arr, left, mid, right);
    }

    public static void sort(List<Integer> arr, int left, int mid, int right){
        // 标记位.当前一个right的元素已经插入到left的某一位flag,
        // 则表示该位flag之前的left中的元素对于其他right中的其他元素都不需要再进行无意义的比较
        // 因为right的剩余元素恒大于flag之前的元素
        int flag = left;
        // 标记位,用于记录动态变化中的left中最后一个元素下标
        // 因为right中的元素插入到left中会使原mid对应的元素后移
        int fakeMid = mid;
        for (int i = mid + 1; i <= right; i++){ // 从right中依次取出一个
            for (int j = flag; j <= fakeMid; j++){ // 将right中的元素和left中的每个元素比较
                if (arr.get(i) < arr.get(j)){
                    Integer remove = arr.remove(i);
                    arr.add(j,remove);
                    // 原mid对应元素后移一位
                    fakeMid++;
                    // 记录right中元素插入位置,下一个right中元素从该位置和left中元素开始比较
                    flag = j;
                    break;
                }
                flag = j;
            }
            // 如果right中某个元素比left中所有元素都大,则表示其后的right的元素恒大于left中元素
            // 则不需要再进行排序
            if (flag > fakeMid) break;
        }
    }

}
