package leetcode;

import java.util.Map;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * hard
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {

        int [] nums1 = {1,4,7,11,16};
        int[] nums2 = {2,3,5,5,6,8,12};
//        int [] nums1 = {1,4,12,31,43};
//        int[] nums2 = {-1,0,2,2,3};
        System.out.println(FindMedianSortedArrays.findMedianSortedArrays(nums1,nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1 4 16 // 取中
        // 2 3 5 6 8 12


        // 1 4 7 11 16 // 取中
        // 1 2 3 4 5 6 7 8 11 12 16

        if (nums1.length == 0) return ((double)nums2[ (nums2.length - 1) / 2] + nums2[(nums2.length) / 2]) / 2;
        if (nums2.length == 0) return ((double) nums1[ (nums1.length - 1) / 2] + nums1[(nums1.length) / 2]) / 2;
        return binary(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1);
    }

    //   int [] nums1 = {1,4,7,11,16};
    //   int[] nums2 = {2,3,5,5,6,8,12};
    private static double binary(int[] nums1, int[] nums2, int s1, int e1, int s2, int e2) {
        int p1 = (e1 + s1) / 2;
        int p2 = 0;
        int lw = p1 + 1 + s2;
        int rw = nums1.length - p1 - 1;
        for (int i = s2; i <= e2; i++){
            if (nums1[p1] < nums2[i]){
                p2 = i;rw += nums2.length - p2;
                break;
            }
            p2 = i;lw++;
        }
        if (lw == rw){
            // 两个点都在nums1
            // 两个点都在nums2
            // p1 p2
            if (p1+1 < nums1.length){
                return ((double) nums1[p1] + Math.min(nums1[p1+1], nums2[p2])) / 2;
            }else return ((double)nums1[p1] + nums2[p2]) / 2;

        }else if (lw - rw == 1){
            if (p2 - 1 >= 0){
                return Math.max(nums1[p1], nums2[p2-1]);
            }else return nums1[p1];
        }else if (rw - lw == 1){
            if (p1+1 < nums1.length){
                return Math.min(nums2[p2], nums1[p1+1]);
            }else return nums2[p2];

        }
        if (s1 == e1){
            // 求出左右相差多少
            int a;
            if (rw - lw > 0){
                a = (rw - lw - (e2 - p2 + 1));
            }else {
                a = (rw - lw + (e2 - p2 + 1));
            }
            p2 += a;
            s2 = p2;
            return ((double)nums2[s2] + nums2[e2]) / 2;
        }
        if (s2 == e2){
            int a;
            if (rw - lw > 0){
                a = (rw - lw - (e1 - s1 + 1));
            }else {
                a = (rw - lw + (e1 - s1 + 1));
            }
            s1 +=  a;
            return ((double)nums1[s1] + nums1[e1]) / 2;
        }
        if (lw > rw){
            e1 = p1-1;e2 = p2;
        }else {
            s1 = p1+1;s2 = p2;
        }
        return binary(nums1, nums2, s1, e1, s2, e2);

    }


}
