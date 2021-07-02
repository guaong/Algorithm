package leetcode;


/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * normal
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        String s = "pkew";
        System.out.println(LengthOfLongestSubstring.lengthOfLongestSubstring(s));
    }

    private static int lengthOfLongestSubstring(String s){
        char[] arr = s.toCharArray(); // 转成字符串
        int count = 0;
        // start到end之间记录的是当前无重复字符串(并不一定是最大长度的)
        // 称这一段为窗口
        int start = 0; // 用于标记当前不重复的字符串开始下标
        int end = 0; // 用于标记当前不重复的字符串结束下标（end指向的其实是窗口最后一个节点的下一个）
        for (int i = 0; i < arr.length; i++){ // 整个arr

            for (int p = start; p < end; p++){ // 部分arr(从start到end)

                if (arr[p] == arr[i]){
                    // 如果当前标记的范围大于历史最大长度count
                    // 就更新最大长度count
                    if (end - start > count){
                        count = end - start;
                    }
                    // 此时start到end之间的窗口已经不可能再扩大了
                    // 所以要找一个新的窗口
                    // 而只有start指向p+1才有机会找到更长的窗口
                    start = p + 1;
                    break;
                }

            }
            ++end;

        }
        if (end - start > count) count = end -start;

        return count;
    }

}
