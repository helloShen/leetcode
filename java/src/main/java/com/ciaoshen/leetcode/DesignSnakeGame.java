/**
 * Leetcode - Algorithm - DesignSnakeGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DesignSnakeGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DesignSnakeGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void init(int width, int height, int[][] food);    //主方法接口
        abstract public int move(String direction);                 //主方法接口
        protected void sometest() { return; }                       // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public void init(int width, int height, int[][] food) {
            //测试很中二，行列定义是反的
            this.height = height;   //width代表行数
            this.width = width;     //height代表列数
            snake = new LinkedList<Position>();
            snake.add(new Position(0,0));
            this.food = food;
            this.nextFood = 0;
            dead = false;
            score = 0;
        }
        public int move(String direction) {
            if (dead) { return -1; }
            int[] next = nextPas(direction);
            // if (nextFood >= 0 && nextFood < food.length) {
            //     System.out.println("Next Food = [" + Arrays.toString(food[nextFood]) + "]");
            // }
            // System.out.println("Next Step = " + Arrays.toString(next));
            // System.out.println("Before move, snake is: " + snake);
            if (die(next)) {
                dead = true;
                return -1;
            }
            if (findFood(next)) {
                eat();
                // System.out.println("Snake eat food: " + snake);
                newFood();
                score++;
            } else {
                moveOn(next);
            }
            return score;
        }

        private int width;               //地图宽度
        private int height;              //地图高度
        private Deque<Position> snake;   //模拟蛇
        private int score;               //成功吃掉多少个食物
        private int nextFood;            //指向food中的下一个食物。如果食物更新位置被蛇身占住，则暂时挂起（负数）。
        private int[][] food;            //食物列表
        private boolean dead;            //记录蛇是否已死

        //通过方向词，算出下一步要走的位置
        //测试行列定义是反的：
        //  width代表行数
        //  height代表列数
        private int[] nextPas(String direction) {
            Position head = snake.peekFirst();
            int[] headArray = new int[]{head.getWidth(), head.getHeight()};
            if (direction == null) {
                return headArray;
            }
            //测试很中二，行列定义是反的
            switch (direction) {
                case "U":
                    headArray[0]--; break;  //往“上”: width-1
                case "D":
                    headArray[0]++; break;  //往“下”: width+1
                case "L":
                    headArray[1]--; break;  //往“左”: height-1
                case "R":
                    headArray[1]++; break;  //往“右”: height+1
                default:
                    break;
            }
            return headArray;
        }
        //判断这一步蛇会不会死。
        //两种情况会死：
        //  1. 超出屏幕
        //  2. 咬到自己身体
        private boolean die(int[] next) {
            if (next[0] < 0 || next[0] >= height || next[1] < 0 || next[1] >= width) {
                return true;
            } //出界
            Position nextPos = new Position(next[0],next[1]);
            if (snake.contains(nextPos) && !nextPos.equals(snake.peekLast())) {
                return true;
            } //咬到自己（下一步正好是自己的尾巴是允许的，因为位置正好会空出来）
            return false;
        }
        //判定本轮蛇是否吃到食物
        private boolean findFood(int[] next) {
            if (foodSuspended()) { return false; }
            return (nextFood < food.length &&       //先得有食物
                    next[0] == food[nextFood][0]) && (next[1] == food[nextFood][1]);
        }
        //蛇吃掉食物，本轮长大一格
        private void eat() {
            snake.offerFirst(new Position(food[nextFood][0],food[nextFood][1]));
            // System.out.println("After eat, snake: " + snake);
        }
        //蛇直接前进一格
        //如果下个新食物的位置被蛇占住了，每次moveOn()都重新调用newFood()试图更新食物
        private void moveOn(int[] next) {
            // System.out.println("Now head of snake becomes: " + Arrays.toString(next));
            snake.offerFirst(new Position(next[0],next[1]));
            snake.pollLast();
            // System.out.println("After move on, snake: " + snake);
            if (foodSuspended()) {
                newFood();
            }
        }
        private boolean foodSuspended() {
            return nextFood < 0;
        }
        //放新食物。nextFood指针超出food数组末尾，说明食物放完。
        //如果下一个新食物的位置被蛇占据了，就暂时不更新下一个食物，并将nextFood取负数。
        private void newFood() {
            //如果食物因为被蛇身占了位置无法跟新，重新检查现在蛇身是否还挡住食物更新位置。
            if (foodSuspended()) {
                Position nextFoodPos = new Position(food[nextFood][0], food[nextFood][1]);
                if (!snake.contains(nextFoodPos) || nextFoodPos.equals(snake.peekLast())) {
                    activateFood();
                }
            //正常更新食物
            } else {
                if (nextFood >= food.length) { return; } //食物吃完了
                nextFood++;
                if (nextFood < food.length && snake.contains(new Position(food[nextFood][0], food[nextFood][1]))) {
                    suspendFood();
                }
            }
        }
        //挂起食物。将下个食物指针取反，并-1（保证是负数）
        private void suspendFood() {
            nextFood = -Math.abs(nextFood) - 1;
        }
        //取消食物挂起。先将指针+1，再取绝对值
        private void activateFood() {
            nextFood = Math.abs(nextFood+1);
        }
        //模拟地图上点位置
        private class Position {
            private int width;  //行数
            private int height; //列数
            private int hash;   //散列值
            private Position(int width, int height) {
                this.width = width;
                this.height = height;
                hash = 0;
            }
            private int getWidth() {
                return width;
            }
            private int getHeight() {
                return height;
            }
            public String toString() {
                return "[" + width + "," + height + "]";
            }
            //为了比较LinkedList里Position元素，必须重写equals()和hashCode()函数
            @Override
            public boolean equals(Object another) {
                return (this.width == ((Position)another).width) && (this.height == ((Position)another).height);
            }
            @Override
            public int hashCode() {
                if (hash != 0) { return hash; }
                return 31 * (width+1) + (height+1);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        //2-D: [row,col]
        //1-D: row * width + col + 1
        public void init(int width, int height, int[][] food) {
            this.width = width;     //width代表列数
            this.height = height;   //height代表行数
            snake = new LinkedList<Integer>();
            snake.add(1);
            this.food = food;
            this.nextFood = 0;
            dead = false;
            score = 0;
        }
        public int move(String direction) {
            if (dead) { return -1; }
            //定位下一步
            int head = snake.peekFirst();
            int row = (head - 1) / width;
            int col = (head - 1) % width;
            switch (direction) {
                case "U":
                    row--; break;
                case "D":
                    row++; break;
                case "L":
                    col--; break;
                case "R":
                    col++; break;
                default:
                    break;
            }
            int next = row * width + col + 1;
            //先排除死局
            int tail = snake.pollLast(); //刚好咬尾巴不会死
            if (row < 0 || row >= height || col < 0 || col >= width || snake.contains(next)) {
                dead = true;
                return -1;
            }
            //吃到食物
            if (nextFood < food.length && row == food[nextFood][0] && col == food[nextFood][1]) {
                nextFood++;
                score++;
                snake.offerLast(tail);  //吃到食物不用剪尾巴
            }
            snake.offerFirst(next);
            return score;
        }

        private int width;               //列数
        private int height;              //行数
        private Deque<Integer> snake;    //模拟蛇
        private int nextFood;            //指向food中的下一个食物坐标
        private int[][] food;            //食物坐标列表
        private int score;               //成功吃掉多少个食物
        private boolean dead;            //记录蛇是否已死

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        //2-D: [row,col]
        //1-D: row * width + col + 1
        public void init(int width, int height, int[][] food) {
            this.width = width;     //width代表列数
            this.height = height;   //height代表行数
            snake = new LinkedList<Integer>();
            snake.add(1);
            this.food = food;
            this.nextFood = 0;
            dead = false;
            score = 0;
        }
        public int move(String direction) {
            if (dead) { return -1; }
            //定位下一步
            int head = snake.peekFirst();
            int[] next2D = navigate(head,direction);
            int next = next2D[0] * width + next2D[1] + 1;
            //先排除死局
            if (outOfBoundary(next2D) || biteItself(next)) {
                dead = true;
                return -1;
            }
            //吃到食物
            if (findFood(next2D)) {
                eat(next);
            } else {
                moveOn(next);
            }
            return score;
        }

        private int width;               //列数
        private int height;              //行数
        private Deque<Integer> snake;    //模拟蛇
        private int nextFood;            //指向food中的下一个食物坐标
        private int[][] food;            //食物坐标列表
        private int score;               //成功吃掉多少个食物
        private boolean dead;            //记录蛇是否已死


        private int[] navigate(int head, String direction) {
            int[] head2D = to2D(head);
            switch (direction) {
                case "U":
                    head2D[0]--; break;
                case "D":
                    head2D[0]++; break;
                case "L":
                    head2D[1]--; break;
                case "R":
                    head2D[1]++; break;
                default:
                    break;
            }
            return head2D;
        }

        //蛇撞边界挂了
        private boolean outOfBoundary(int[] next2D) {
            return next2D[0] < 0 || next2D[0] >= height || next2D[1] < 0 || next2D[1] >= width;
        }
        //蛇咬死了自己
        private boolean biteItself(int next) {
            int tail = snake.pollLast(); //刚好咬尾巴不会死
            boolean res = snake.contains(next);
            snake.offerLast(tail);
            return res;
        }
        //查看蛇当前头部位置有没有食物
        private boolean findFood(int[] next2D) {
            return nextFood < food.length && next2D[0] == food[nextFood][0] && next2D[1] == food[nextFood][1];
        }
        //蛇吃食物长长一格
        private void eat(int next) {
            snake.offerFirst(next);
            nextFood++;
            score++;
        }
        //没吃的继续走
        private void moveOn(int next) {
            snake.offerFirst(next);
            snake.pollLast();
        }
        //1维数组中的id转换成2维的坐标点
        private int[] to2D(int id) {
            int row = (id - 1) / width;
            int col = (id - 1) % width;
            return new int[]{row,col};
        }

    }
    // you can expand more solutions HERE if you want...


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
        private DesignSnakeGame problem = new DesignSnakeGame();
        private Solution solution = null;

        // call method in solution
        private void call(int width, int height, int[][] food, String directions, int[] answer) {
            solution.init(width,height,food);
            for (int i = 0; i < directions.length(); i++) {
                String direction = directions.substring(i,i+1);
                System.out.println("Move [" + direction + "]... ... [" + solution.move(direction) + "]");
            }
            System.out.println("Answer should be: " + Arrays.toString(answer));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            //测试用例1
            int width1 = 3;
            int height1 = 2;
            int[][] food1 = new int[][]{{1,2},{0,1}};
            String directions1 = "RDRULU";
            int[] answer1 = new int[]{0,0,1,1,2,-1};
            //测试用例2
            int width2 = 3;
            int height2 = 3;
            int[][] food2 = new int[][]{{2,0},{0,0},{0,2},{0,1},{2,2},{0,1}};
            String directions2 = "DDRUULDRRULLDRU";
            int[] answer2 = new int[]{0,1,1,1,1,2,2,2,2,3,4,4,4,4,-1};
            //测试用例3
            int width3 = 3;
            int height3 = 3;
            int[][] food3 = new int[][]{{0,1},{0,2},{1,2},{2,2},{2,1},{2,0},{1,0}};
            String directions3 = "RRDDLLUURRDDLLURULD";
            int[] answer3 = new int[]{1,2,3,4,5,6,7,7,7,7,7,7,7,7,7,7,7,7,-1};
            //测试用例4（好像并不存在，可能是我穿越了）
            int width4 = 1;
            int height4 = 2;
            int[][] food4 = new int[][]{{1,0}};
            String directions4 = "DD";
            int[] answer4 = new int[]{1,-1};
            //测试用例5
            int width5 = 2;
            int height5 = 2;
            int[][] food5 = new int[][]{{1,1},{0,0}};
            String directions5 = "RDLL";
            int[] answer5 = new int[]{0,1,1,-1};

            /** involk call() method HERE */
            // call(width1,height1,food1,directions1,answer1);
            // call(width2,height2,food2,directions2,answer2);
            call(width3,height3,food3,directions3,answer3);
            call(width4,height4,food4,directions4,answer4);
            call(width5,height5,food5,directions5,answer5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
