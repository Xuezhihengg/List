package Stack;
//快速排序有两种操作，一种是partition，该操作不需要递归，另一种是分别对两个子序列递归快速排序快速排序
class Q{  //Q是用户自定义的活动记录类，储存操作类型，数组信息，排序起点，排序终点

    public int start;
    public int end;
    public int op;   //操作类型，0代表分割序列，1代表递归快排
    public Q(int op, int start, int end){
        this.op = op;
        this.start = start;
        this.end = end;
    }
}

public class Application_qsort {
    static final int PART = 0;
    static final int QSORT = 1;

    public static int findPivot(int[] nums, int left, int right) {
        int center = left + (right - left) / 2;
        if (nums[left] > nums[center]) {
            int temp = left;
            left = center;
            center = temp;
        }
        if (nums[left] > nums[right]) {
            int temp = left;
            left = right;
            right = temp;
        }
        if (nums[center] > nums[right]) {
            int temp = center;
            center = right;
            right = temp;
        }
        return center;
    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[findPivot(nums, left, right)];
        //先将轴值换到最左边
        int temp = pivot;
        pivot = nums[left];
        nums[left] = temp;
        int i = left + 1, j = right;
        while (true) {
            while (nums[i] <= pivot && i <= j && i < right) { //这里的判断条件是关键，不能随意更改，nums[i] <= pivot这里有等号可以在重复元素较多时避免交换前后无变化而陷入死循环;i <= j这里有等号可以保证最后i与j会发生交叉进而确定j对应元素需要与轴值交换;i < right这里有等号是为了避免重复元素过多时出现越界报错
                i++;
            }
            while (nums[j] >= pivot && i <= j && j > left) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int temp1 = nums[i];
            nums[i] = nums[j];
            nums[j] = temp1;

        }
        //最终i与j会交叉,j为左边子数组最后一个元素下标，i为右边子数组第一个元素下标.此时需要交换轴值与nums[j]
        nums[left] = nums[j];
        nums[j] = pivot;
        return j;
    }

    public static void Qsort(int[] nums, int start, int end) {
        //创建用户栈来模拟系统栈
        Stack S = new LStack();
        //第一次递归入栈要手动进行
        S.push(new Q(QSORT, start, end));
        while (!S.isEmpty()) {
            Q temp = (Q) S.pop();
            if (temp.start < temp.end) {
                if (temp.op == PART) {
                    int pviot = partition(nums, temp.start, temp.end);
                    //由于左右子数组快排入栈需要partition返回的轴值信息，所以子数组入栈并不放在QSORT操作下。这里还要注意由于栈是先入后出，所以要注意入栈顺序
                    S.push(new Q(QSORT, pviot + 1, temp.end));
                    S.push(new Q(QSORT, temp.start, pviot - 1));
                } else {
                    S.push(new Q(PART, temp.start, temp.end));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 441, 54, 3,56,-2};
        Qsort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.print(i+ " ");
        }
    }
}
