package Stack;

//归并排序有两个操作，第一个是对原数组对半分成的两个子数组递归排序，第二个是将排好序的两个子数组合并成一个有序的数组
class M {
    public int op;
    public int start;
    public int end;

    public M(int op, int start, int end) {
        this.op = op;
        this.start = start;
        this.end = end;
    }
}

public class Application_mergesort {
    static final int MSORT = 0;
    static final int MERGE = 1;

    static void Merge(int[] nums, int[] temp, int l, int r) {
        int mid = l + (r - l) / 2;
        //排序好的子数组先copy到temp数组中，保证nums在每层递归中都是原数组，temp都是辅助数组，从而避免每次归并都使用一个新数组。(这里i必须能取到r,因为r是下标而不是长度,i==r即将子序列最右端元素copy到辅助数列中)
        for (int i = l; i <= r; i++) {
            temp[i] = nums[i];
        }
        //执行归并排序
        int i1, i2;  //i1为左边子数组顶端数的下标，i2为右边子数组顶端数的下标
        i1 = l;
        i2 = mid + 1;
        for (int cur = l; cur <= r; cur++) {  //这里的cur也必须能取到r
            //如果一个子数组中已经没有数，则比较结束，另一个数组中的剩余数全部移动到nums中即可
            if (i1 == mid + 1) {
                nums[cur] = temp[i2++];
            } else if (i2 > r) {
                nums[cur] = temp[i1++];
                //比较左边子数组和右边子数组的顶端，小的放入nums以实现排序
            } else if (nums[i1] <= nums[i2]) {
                nums[cur] = temp[i1++];      //i1++是先把顶端数移入nums，再在子数组中抹除这个数
            } else if (nums[i1] > nums[i2]) {
                nums[cur] = temp[i2++];
            }
        }
    }

    static void Msort(int[] nums, int[] temp, int start, int end) {
        Stack S = new LStack();
        S.push(new M(MSORT, start, end));
        while (!S.isEmpty()) {
            M Mtemp = (M) S.pop();
            if (Mtemp.start != Mtemp.end) {
                if (Mtemp.op == MERGE) {
                    Merge(nums, temp, Mtemp.start, Mtemp.end);
                } else {
                    int mid = Mtemp.start + (Mtemp.end - Mtemp.start) / 2;
                    S.push(new M(MERGE, Mtemp.start, Mtemp.end));
                    S.push(new M(MSORT, mid + 1, Mtemp.end));
                    S.push(new M(MSORT, Mtemp.start, mid));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 54, 4, 56, -2,5};
        int[] temp = new int[nums.length];
        Msort(nums, temp, 0, nums.length - 1);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
