/**
 * Leetcode - Algorithm - SuperUglyNumber
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SuperUglyNumber implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SuperUglyNumber() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int nthSuperUglyNumber(int n, int[] primes); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }

    // 暴力除法
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int nthSuperUglyNumber(int n, int[] primes) {
            if (n == 1) { return 1; }
            n--;
            int next = 2;
            for( ; n > 0; next++) {
                if (isUglyNumber(next,primes)) {
                    n--;
                }
            }
            return next-1;
        }

        private boolean isUglyNumber(int n, int[] primes) {
            for (int prime : primes) {
                while (n % prime == 0) { n /= prime;}
            }
            return n == 1;    // 最后除干净得到1，就肯定是。
        }
    }

    // 素数乘法解释帖发帖人的代码
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] ugly = new int[n+1];
            ugly[0]=1;
            int[] pointer = new int[primes.length];
            for(int i=1;i<n;i++) {
                int min=Integer.MAX_VALUE;
                int minIndex = 0;
                for(int j=0;j<primes.length;j++) {
                    if(ugly[pointer[j]]*primes[j]<min) {
                        min=ugly[pointer[j]]*primes[j];
                        minIndex = j;
                    }else if(ugly[pointer[j]]*primes[j]==min) {
                        pointer[j]++;
                    }
                }
                ugly[i]=min;
                pointer[minIndex]++;
            }
            return ugly[n-1];
        }
    }
    // 我希望将得到的丑陋数加入原来的素数数组，来加快数字被除的速度。其实没必要，也没效果。
    // 不是个好方法
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int nthSuperUglyNumber(int n, int[] primes) {
            if (n == 1) { return 1; }
            n--;
            List<Integer> primeList = new ArrayList<>();
            for (int prime : primes) { primeList.add(prime); }
            int next = 2;
            for( ; n > 0; next++) {
                if (isUglyNumber(next,primeList)) {
                    primeList.add(0,next);  // 确认的丑陋数都可以加进原始的数组
                    n--;
                }
            }
            return next-1;
        }
        private boolean isUglyNumber(int n, List<Integer> primeList) {
            for (int prime : primeList) {
                while (n % prime == 0) { n /= prime;}
            }
            return n == 1;    // 最后除干净得到1，就肯定是。
        }
    }
    // 利用已知的丑陋数推导出后续的丑陋数，而不是暴力排除法。
    private class Solution4 extends Solution {
        { super.id = 4; }

        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] uglyNumbers = new int[n];
            int uglyNumbersP = 0;
            uglyNumbers[uglyNumbersP++] = 1;
            int[] pointers = new int[primes.length];
            int[] candidates = new int[primes.length];
            for (int i = 0; i < primes.length; i++) {
                candidates[i] = primes[i];
            }
            while (uglyNumbersP < n) {
                // 取本轮最小值
                int minP = 0;       // 指示本轮是第几个候选数当选
                for (int j = 1; j < candidates.length; j++) {
                    if (candidates[j] < candidates[minP]) {
                        minP = j;
                    }
                }
                if (candidates[minP] > uglyNumbers[uglyNumbersP-1]) { // 最小的候选数成功当选
                    uglyNumbers[uglyNumbersP++] = candidates[minP];
                    // System.out.println("表格新加入[" + candidates[minP] + "]");
                    // System.out.println("结果列表更新为：" + Arrays.toString(uglyNumbers));
                // } else {
                    // System.out.println("本轮[" + candidates[minP] + "]重复了");
                }
                // 更新候选数列表
                candidates[minP] = primes[minP] * (uglyNumbers[++pointers[minP]]);
                // System.out.println("更新后的candidates列表=" + Arrays.toString(candidates));
            }
            // System.out.println(Arrays.toString(uglyNumbers));
            return uglyNumbers[n-1];
        }
    }

    // 希望用一个Min Heap维护最小候选丑陋数
    private class Solution5 extends Solution {
        { super.id = 5; }

        public int nthSuperUglyNumber(int n, int[] primes) {
            // 初始化丑陋数数组
            int[] uglyNumbers = new int[n];
            uglyNumbers[0] = 1;
            int uglyNumbersP = 1;
            // 初始化候选数列表（是一个Min Heap）
            // 每个元素是一个int[2]，其中[0]是实际候选数，[1]是指向现有丑陋数组的指针
            MinHeapWithPointer candidates = new MinHeapWithPointer(primes.length);
            for (int prime : primes) {
                candidates.insert(prime,0);
            }
            // 每次从候选数组中取出最小的数添加到丑陋数组中，然后再根据丑陋数组更新候选数
            while (uglyNumbersP < n) {
                int[] candidate = candidates.getMin();
                if (candidate[0] != uglyNumbers[uglyNumbersP-1]) {
                    uglyNumbers[uglyNumbersP++] = candidate[0];
                    System.out.println("当前丑陋数组：" + Arrays.toString(uglyNumbers));
                }
                int newCandidate = candidate[0] / uglyNumbers[candidate[1]] * uglyNumbers[candidate[1]+1];
                candidates.insert(newCandidate,candidate[1]+1);
                System.out.println("更新后的candidates列表=\n" + candidates);
            }
            return uglyNumbers[n-1];
        }
    }

    // 这道题专用的，每个内部节点都有2个值的Min Heap
    // 用来封装一系列的候选数以及他们当前对应到丑陋数列表上的指针
    // 比较大小只依靠heap[]中的值，不用info[]。
    private class MinHeapWithPointer {

        public MinHeapWithPointer(int size) {
            heap = new int[size+1];
            info = new int[size+1];
            p = 1;
        }
        /**
         * return min value in heap and update the heap
         * @return min value in heap (current root node)
         */
        public int[] getMin() {
            int[] min = new int[2];
            min[0] = heap[1];
            min[1] = info[1];
            heap[1] = heap[--p];
            info[1] = info[p];
            minHelper(1);
            return min;
        }
        // bubble-down the pseudo-root
        // 冒泡只依靠heap[]中的值，不考虑info[]
        private void minHelper(int root) {
            int left = root * 2, right = left + 1;
            // 当左右子节点中至少有一个大于父节点时
            if ((left < p && heap[left] < heap[root]) || (right < p && heap[right] < heap[root])) {
                // 优先考虑换左节点，只有当右节点确实比左节点小才考虑换右节点
                if (right < p && heap[left] > heap[right]) {
                    swap(root,right);
                    minHelper(right);
                } else {
                    swap(root,left);
                    minHelper(left);
                }
            }
        }
        // insert a new number at the end of array and bubble-up it
        public void insert(int val, int addition) {
            int curr = p, parent = p / 2;
            heap[p] = val;
            info[p] = addition;
            p++;
            // 如果新节点值小于其父节点，冒泡
            while (parent > 0 && heap[curr] < heap[parent]) {
                swap(curr,parent);
                curr = parent;
                parent = curr / 2;
            }
        }
        public boolean isEmpty() {
            return p <= 1;
        }
        public String toString() {
            return Arrays.toString(heap) + "\n" + Arrays.toString(info);
        }

        private int[] heap;
        private int[] info;
        private int p;

        private void swap(int a, int b) {
            int temp = heap[a];
            int tempInfo = info[a];
            heap[a] = heap[b];
            info[a] = info[b];
            heap[b] = temp;
            info[b] = tempInfo;
        }
    }

    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private SuperUglyNumber problem = new SuperUglyNumber();
        private Solution solution = null;

        // call method in solution
        private void call(int n, int[] primes) {
            System.out.println("[" + n + "]th super ugly number is: " + solution.nthSuperUglyNumber(n,primes));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 12;
            int[] primes1 = new int[]{2,7,13,19};
            int n2 = 89;
            int[] primes2 = new int[]{5,7,11,13,17,19,29,43,47,53};
            int n3 = 15;
            int[] primes3 = new int[]{3,5,7,11,19,23,29,41,43,47};

            /** involk call() method HERE */
            // call(n1,primes1);
            // call(n2,primes2);
            call(n3,primes3);
        }
        // 只为了测试MinHeap用
        // 给我任意一串数字，应该从小到大地输出数字
        private void testMinHeap() {
            int size = 10, max = 100;
            int[] nums = new int[size];
            Random r = new Random();
            for (int i = 0; i < size; i++) {
                nums[i] = r.nextInt(max);
            }
            System.out.println(Arrays.toString(nums));
            MinHeap heap = new MinHeap(size);
            for (int i = 0; i < size; i++) {
                heap.insert(nums[i]);
            }
            System.out.println("Now Heap become: " + heap);
            while (!heap.isEmpty()) {
                System.out.println("-> " + heap.getMin() + "\t");
                System.out.println("Now Heap become: " + heap);
            }
            System.out.println("\n");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        test.test(5);
        // test.testMinHeap();
    }
}
