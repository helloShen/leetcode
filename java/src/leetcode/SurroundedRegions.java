/**
 * Leetcode - Algorithm - Surrounded regions
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class SurroundedRegions {
    /**
     * 最开始的杀鸡用牛刀的解法。
     * 1. 找出所有`O`，并记录位置。
     * 2. 把所有`O`按位置是否连在一起，分成两组`O`和`OOO`。
     * 3. 对于任意一组`O`，其中只要有一个`O`在最外一圈，整个一组就是活的，逃跑了。
     * 4. 反之，某一组`O`，如果没有`O`在最外一圈，整个一组被捕获。
     * 5. 记录完所有被捕获的小组，在`board`上把所有捕获的`O`替换成`X`。
     */
    public void solveV1(char[][] board) {
        if (board.length == 0) { return; }
        List<List<Integer>> captured = new ArrayList<>();
        List<List<List<Integer>>> groups = groups(board);
        for (List<List<Integer>> group : groups) {
            if (!groupIsFree(board.length,board[0].length,group)) {
                captured.addAll(group);
            }
        }
        for (List<Integer> point : captured) {
            board[point.get(0)][point.get(1)] = 'X';
        }
    }
    public List<List<List<Integer>>> groups(char[][] board) {
        List<List<List<Integer>>> groups = new ArrayList<>();
        List<List<Integer>> points = points(board);
        while (!points.isEmpty()) {
            List<List<Integer>> group = new ArrayList<>();
            List<Integer> head = points.remove(0);
            trackGroup(points,head,group);
            groups.add(group);
            for (List<Integer> point : group) {
                points.remove(point);
            }
        }
        return groups;
    }
    public List<List<Integer>> points(char[][] board) {
        List<List<Integer>> points = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    points.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{i,j})));
                }
            }
        }
        return points;
    }
    public void trackGroup(List<List<Integer>> points, List<Integer> head, List<List<Integer>> group) {
        if (group.contains(head)) { return; }
        group.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{head.get(0),head.get(1)})));
        for (List<Integer> point : points) {
            if (areNeighbours(point.get(0),point.get(1),head.get(0),head.get(1))) {
                trackGroup(points,point,group);
            }
        }
    }
    public boolean areNeighbours (int rowA, int colA, int rowB, int colB) {
        if (rowA == rowB && Math.abs(colA - colB) == 1) { return true; }
        if (colA == colB && Math.abs(rowA-rowB) == 1) { return true; }
        return false;
    }
    public boolean groupIsFree(int height, int width, List<List<Integer>> group) {
        for (List<Integer> point : group) {
            if (pointIsFree(height,width,point.get(0),point.get(1))) { return true; }
        }
        return false;
    }
    public boolean pointIsFree(int height, int width, int row, int col) {
        return row == 0 || row == height-1 || col == 0 || col == width-1;
    }
    private static class Point {
        private int row;
        private int col;
        private Point(int row, int col) { this.row = row; this.col = col; }
        public boolean equals(Object obj) {
            if (! (obj instanceof Point)) { return false; }
            Point p = (Point)obj;
            return row == p.row && col == p.col;
        }
        public int hashCode() {
            return (17 + row) * 31 + col;
        }
    }
    /**
     * 已经是正统做法。但思路还不成熟。
     */
    public void solveV2(char[][] board) {
        if (board.length == 0) { return; }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    Point p = new Point(i,j);
                    Set<Point> group = new HashSet<>();
                    if (escapted(board,p,group)) {
                        write(board,group,'F'); // 先标记成F，最后再改回来。
                    } else {
                        write(board,group,'X');
                    }
                }
            }
        }
        replaceAll(board,'F','O');
    }
    public void write(char[][] board, Set<Point> group, char c) {
        for (Point p : group) {
            board[p.row][p.col] = c;
        }
    }
    public void replaceAll(char[][] board,char oldC, char newC) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == oldC) {
                    board[i][j] = newC;
                }
            }
        }
    }
    public boolean escapted(char[][] board, Point p, Set<Point> group) {
        if (outOfRange(board.length, board[0].length, p)) { return false; } // out of boundary
        if (board[p.row][p.col] == 'O') {
            if (!group.add(p)) { return false; } // already mark this O
        } else { // is X
            return false;
        }
        boolean res = isFree(board.length,board[0].length,p);
        boolean up = escapted(board,new Point(p.row-1,p.col),group);
        boolean blow = escapted(board,new Point(p.row+1,p.col),group);
        boolean left = escapted(board,new Point(p.row,p.col-1),group);
        boolean right = escapted(board,new Point(p.row,p.col+1),group);
        return res || up || blow || left || right;
    }
    // 用Point而不是用两个int表示点
    public boolean outOfRange(int height, int width, Point p) {
        return p.row < 0 || p.row >= height || p.col < 0 || p.col >= width;
    }
    public boolean isFree(int height, int width, Point p) {
        return (p.row == 0) || (p.row == height-1) || (p.col == 0) || (p.col == width-1);
    }

    /**
     * 正统的解法，从最外一圈的O点向内探索。因为最外一圈的O都是抓不住的，和他相邻也都抓不住。
     */
    public void solveV3(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { harvestPointsRecur(board,new Point(0,i)); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { harvestPointsRecur(board,new Point(i,width-1)); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { harvestPointsRecur(board,new Point(height-1,i)); }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { harvestPointsRecur(board,new Point(i,0)); }
        }
        replaceAll(board,'O','X'); // kill all captured
        replaceAll(board,'F','O'); // remark all survivers
    }
    /**
     * 深一层。无脑进入下一层，靠终结条件结束迭代或递归。
     * @param  [description]
     * @param  [description]
     */
    public void harvestPointsV1(char[][] board, Point root) {
        int height = board.length;
        int width = board[0].length;
        List<Point> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            Point p = buffer.remove(0);
            if (outOfRange(height,width,p)) { continue; }
            if (board[p.row][p.col] == 'O') {
                board[p.row][p.col] = 'F';
                buffer.add(new Point(p.row-1,p.col)); // up
                buffer.add(new Point(p.row+1,p.col)); // down
                buffer.add(new Point(p.row,p.col-1)); // left
                buffer.add(new Point(p.row,p.col+1)); // right
            }
        }
    }
    // harvestPoints的递归版。虽然更好，但是在Leetcode通不过，递归太深导致栈溢出。
    public void harvestPointsRecur(char[][] board, Point root) {
        if (outOfRange(board.length,board[0].length,root)) { return; }
        if (board[root.row][root.col] != 'O') { return; }
        board[root.row][root.col] = 'F';
        harvestPointsRecur(board,new Point(root.row-1,root.col));
        harvestPointsRecur(board,new Point(root.row+1,root.col));
        harvestPointsRecur(board,new Point(root.row,root.col-1));
        harvestPointsRecur(board,new Point(root.row,root.col+1));
    }
    /**
     * 浅一层。不靠终结条件终结，不符合条件不进入下一层。
     */
    public void harvestPointsV2(char[][] board, Point root) {
        int height = board.length;
        int width = board[0].length;
        List<Point> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            Point p = buffer.remove(0);
            board[p.row][p.col] = 'F';
            if (p.row-1 >= 0 && board[p.row-1][p.col] == 'O') { buffer.add(new Point(p.row-1,p.col)); } // up
            if (p.row+1 < height && board[p.row+1][p.col] == 'O') { buffer.add(new Point(p.row+1,p.col)); } // down
            if (p.col-1 >= 0 && board[p.row][p.col-1] == 'O') { buffer.add(new Point(p.row,p.col-1)); } // left
            if (p.col+1 < width && board[p.row][p.col+1] == 'O') { buffer.add(new Point(p.row,p.col+1)); } // right
        }
    }
    /**
     * 也是正统的从最外圈开始探索的解法。
     * 和solveV3的区别是，这里尝试使用两个int代替Point。
     */
    public void solveV4(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { harvestPoints(board,0,i); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { harvestPoints(board,i,width-1); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            int col = height-1;
            if (board[height-1][i] == 'O') {
                harvestPoints(board,height-1,i);
            }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { harvestPoints(board,i,0); }
        }
        replaceAll(board,'O','X'); // kill all captured
        replaceAll(board,'F','O'); // remark all survivers
    }
    // 完全不用Point类型，直接用两个int表示点。
    public void harvestPoints(char[][] board, int row, int col) {
        List<Integer> stack = new LinkedList<>();
        stack.add(row);
        stack.add(col);
        while (!stack.isEmpty()) {
            int r = stack.remove(0);
            int c = stack.remove(0);
            if (outOfRange2(board.length,board[0].length,r,c)) { continue; }
            if (board[r][c] == 'O') {
                board[r][c] = 'F';
                stack.add(r-1); stack.add(c); // up
                stack.add(r+1); stack.add(c); // down
                stack.add(r); stack.add(c-1); // left
                stack.add(r); stack.add(c+1); // right
            }
        }
    }
    // 不用Point，直接用两个int表示点
    public boolean outOfRange2(int height, int width, int row, int col) {
        return row < 0 || row >= height || col < 0 || col >= width;
    }


    private static interface UnionFind{
        public Point find(Point p); // 找到p的根节点
        public void follow(Point p, Point q); // 把新节点p接到树q上
        public void union(Point p, Point q); // 合并树p和树q
        public boolean connected(Point p, Point q); // 检查p点和q点是否属于同一棵树F
        public void create(Point p); // 创建一棵新的以p点为根的树。因为Union Find中Point都初始化为null。
        public Point[][] getBoard(); // 方便打印board
    }
    /**
     * 朴素的 Union Find。没有深度平衡，没有路径压缩。所有Point都做防御性拷贝。
     * 不需要换算，直接用Point做数据结构。
     * 默认值为null。
     */
    private static class UnionFindNaive implements UnionFind {
        private Point[][] board;
        private UnionFindNaive(int height, int width) {
            board = new Point[height][width];
        }
        /**
         * 找到P点的根节点
         */
        public Point find(Point p) {
            do {
                p = board[p.row][p.col];
            } while (!board[p.row][p.col].equals(p)); // 根节点指向自己
            return p;
        }
        /**
         * P是新点，接在老树Q后面
         */
        public void follow(Point p, Point q) {
            board[p.row][p.col] = new Point(q.row,q.col);
        }
        /**
         * 把P变成Q的子树
         */
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) { return; }
            board[rootP.row][rootP.col] = new Point(rootQ.row,rootQ.col);
        }
        /**
         * 判断P点和Q点是否属于同一棵树
         */
        public boolean connected(Point p, Point q) {
            return find(p).equals(find(q));
        }
        /**
         * 创建一棵以p为根的新树
         */
        public void create(Point p) {
            board[p.row][p.col] = new Point(p.row,p.col);
        }
        /**
         * 方便打印board
         */
        public Point[][] getBoard() {
            return board;
        }
    }
    /**
     * 朴素的 Union Find 解法。
     * 没有做深度平衡，没有做路径压缩。
     */
    public void solveV5(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        if (!checkOuterRing(board)) {
            allCaptured(board);
            return;
        }
        int height = board.length;
        int width = board[0].length;
        UnionFindNaive uf = new UnionFindNaive(height,width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    Point p = new Point(i,j);
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf.follow(p,new Point(i-1,j)); // 先把当前点接到楼上节点后面
                        uf.union(p,new Point(i,j-1)); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf.follow(p,new Point(i-1,j));
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf.follow(p,new Point(i,j-1));
                    } else { // 建一棵新树
                        uf.create(p);
                    }
                }
            }
        }
        //printUnionFind(uf.board);
        Set<Point> outerRing = getOuterRing(uf);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O' && !outerRing.contains(uf.find(uf.board[i][j]))) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public Set<Point> getOuterRing(UnionFindNaive uf) {
        Set<Point> res = new HashSet<>();
        int height = uf.board.length;
        int width = uf.board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (uf.board[0][i] != null) { res.add(uf.find(uf.board[0][i])); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (uf.board[i][width-1] != null) { res.add(uf.find(uf.board[i][width-1])); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (uf.board[height-1][i] != null) { res.add(uf.find(uf.board[height-1][i])); }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (uf.board[i][0] != null) { res.add(uf.find(uf.board[i][0])); }
        }
        return res;
    }
    /**
     * 如果最外圈没有存活的O，就不用麻烦用Union Find了，直接全部填满X，然后返回。
     */
    public boolean checkOuterRing(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { return true; }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { return true; }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { return true; }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { return true; }
        }
        return false;
    }
    /**
     * 直接填满X
     */
    public void allCaptured(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 'X';
            }
        }
    }

    /**
     * 相对轻量级的 Union Find，不做防御性拷贝。没有深度平衡，没有路径压缩。
     * 不需要换算，直接用Point做数据结构。
     * 默认值为null。
     */
    private static class UnionFindLight implements UnionFind {
        private Point[][] board;
        private UnionFindLight(int height, int width) {
            board = new Point[height][width];
        }
        /**
         * 找到P点的根节点
         */
        public Point find(Point p) {
            do {
                p = board[p.row][p.col];
            } while (!board[p.row][p.col].equals(p)); // 根节点指向自己
            return p;
        }
        /**
         * P是新点，接在老树Q后面
         */
        public void follow(Point p, Point q) {
            board[p.row][p.col] = q;
        }
        /**
         * 把P变成Q的子树
         */
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) { return; }
            board[rootP.row][rootP.col] = rootQ;
        }
        /**
         * 判断P点和Q点是否属于同一棵树
         */
        public boolean connected(Point p, Point q) {
            return find(p).equals(find(q));
        }
        /**
         * 创建一棵以p为根的新树
         */
        public void create(Point p) {
            board[p.row][p.col] = new Point(p.row,p.col);
        }
        /**
         * 方便打印board
         */
        public Point[][] getBoard() {
            return board;
        }
    }
    /**
     * 深度平衡 Union Find
     */
    private static class UnionFindBalanced implements UnionFind {
        private Point[][] board;
        private int[][] sz;
        private UnionFindBalanced(int height, int width) {
            board = new Point[height][width];
            sz = new int[height][width];
        }
        /**
         * 找到P点的根节点
         */
        public Point find(Point p) {
            do {
                p = board[p.row][p.col];
            } while (!board[p.row][p.col].equals(p)); // 根节点指向自己
            return p;
        }
        /**
         * P是新点，接在老树Q后面
         */
        public void follow(Point p, Point q) {
            board[p.row][p.col] = q;
            Point rootQ = find(q);
            sz[rootQ.row][rootQ.col]++;
        }
        /**
         * 把P变成Q的子树
         */
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) { return; }
            int sizeP = sz[rootP.row][rootP.col];
            int sizeQ = sz[rootQ.row][rootQ.col];
            int sum = sizeP + sizeQ;
            if (sizeP >= sizeQ) { // q树较小，把q树接到p树的根
                board[rootQ.row][rootQ.col] = rootP;
                sz[rootP.row][rootP.col] = sum;
            } else { // p树较小，把p树接到q树的根。
                board[rootP.row][rootP.col] = rootQ;
                sz[rootQ.row][rootQ.col] = sum;
            }
        }
        /**
         * 判断P点和Q点是否属于同一棵树
         */
        public boolean connected(Point p, Point q) {
            return find(p).equals(find(q));
        }
        /**
         * 创建一棵以p为根的新树
         */
        public void create(Point p) {
            board[p.row][p.col] = new Point(p.row,p.col);
            sz[p.row][p.col] = 1;
        }
        /**
         * 方便打印board
         */
        public Point[][] getBoard() {
            return board;
        }
    }
    /**
     * 优化的 Union Find
     * 即是深度平衡，又做了路径压缩。
     */
    private static class UnionFindOptimal implements UnionFind {
        private Point[][] board;
        private int[][] sz;
        private UnionFindOptimal(int height, int width) {
            board = new Point[height][width];
            sz = new int[height][width];
        }
        /**
         * 找到P点的根节点
         */
        public Point find(Point p) {
            Point cur = p;
            do {
                cur = board[cur.row][cur.col];
            } while (!board[cur.row][cur.col].equals(cur)); // 根节点指向自己
            board[p.row][p.col] = cur;
            return cur;
        }
        /**
         * P是新点，接在老树Q后面
         */
        public void follow(Point p, Point q) {
            board[p.row][p.col] = q;
            Point rootQ = find(q);
            sz[rootQ.row][rootQ.col]++;
        }
        /**
         * 把P变成Q的子树
         */
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) { return; }
            int sizeP = sz[rootP.row][rootP.col];
            int sizeQ = sz[rootQ.row][rootQ.col];
            int sum = sizeP + sizeQ;
            if (sizeP >= sizeQ) { // q树较小，把q树接到p树的根
                board[rootQ.row][rootQ.col] = rootP;
                sz[rootP.row][rootP.col] = sum;
            } else { // p树较小，把p树接到q树的根。
                board[rootP.row][rootP.col] = rootQ;
                sz[rootQ.row][rootQ.col] = sum;
            }
        }
        /**
         * 判断P点和Q点是否属于同一棵树
         */
        public boolean connected(Point p, Point q) {
            return find(p).equals(find(q));
        }
        /**
         * 创建一棵以p为根的新树
         */
        public void create(Point p) {
            board[p.row][p.col] = new Point(p.row,p.col);
            sz[p.row][p.col] = 1;
        }
        /**
         * 方便打印board
         */
        public Point[][] getBoard() {
            return board;
        }
    }
    /**
     * 只能用于这一题的专属 Union Find
     * 做了路径压缩。
     */
    private static class UnionFindSpecialPurpose implements UnionFind {
        private Point[][] board;
        private UnionFindSpecialPurpose(int height, int width) {
            board = new Point[height][width];
        }
        /**
         * 找到P点的根节点
         */
        public Point find(Point p) {
            Point cur = p;
            do {
                cur = board[cur.row][cur.col];
            } while (!board[cur.row][cur.col].equals(cur)); // 根节点指向自己
            board[p.row][p.col] = cur; // 路径压缩
            return cur;
        }
        /**
         * 为这题做了特殊处理。直接记根节点坐标。为了更扁平化整棵树。
         * P是新点，接在老树Q后面
         */
        public void follow(Point p, Point q) {
            board[p.row][p.col] = board[q.row][q.col];
        }
        /**
         * 把P变成Q的子树
         */
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) { return; }
            board[rootP.row][rootP.col] = rootQ;
        }
        /**
         * 判断P点和Q点是否属于同一棵树
         */
        public boolean connected(Point p, Point q) {
            return find(p).equals(find(q));
        }
        /**
         * 创建一棵以p为根的新树
         */
        public void create(Point p) {
            board[p.row][p.col] = new Point(p.row,p.col);
        }
        /**
         * 方便打印board
         */
        public Point[][] getBoard() {
            return board;
        }
    }
    /**
     * 可以灵活更换不同的UnionFind组件。
     * 使用基于Point的UnionFind。
     */
    public void solveV6(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        if (!checkOuterO(board)) {
            allCaptured(board);
            return;
        }
        int height = board.length;
        int width = board[0].length;
        // 可以选用不同的UnionFind实现类。比较效率。
        UnionFind uf = new UnionFindSpecialPurpose(height,width);
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    Point p = new Point(i,j);
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf.follow(p,new Point(i-1,j)); // 先把当前点接到楼上节点后面
                        uf.union(p,new Point(i,j-1)); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf.follow(p,new Point(i-1,j));
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf.follow(p,new Point(i,j-1));
                    } else { // 建一棵新树
                        uf.create(p);
                    }
                }
            }
        }
        //printUnionFind(uf.getBoard());
        Set<Point> outerO = getOuterO(board,uf);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    if (!outerO.contains(uf.find(new Point(i,j)))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
    /**
     * 简化对外圈的收集。只遍历一次外圈。
     */
    public void solveV7(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        List<Point> outerO = collectOuterO(board);
        if (outerO.isEmpty()) {
            allCaptured(board); return;
        }
        int height = board.length;
        int width = board[0].length;
        // 可以选用不同的UnionFind实现类。比较效率。
        UnionFind uf = new UnionFindSpecialPurpose(height,width);
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    Point p = new Point(i,j);
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf.follow(p,new Point(i-1,j)); // 先把当前点接到楼上节点后面
                        uf.union(p,new Point(i,j-1)); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf.follow(p,new Point(i-1,j));
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf.follow(p,new Point(i,j-1));
                    } else { // 建一棵新树
                        uf.create(p);
                    }
                }
            }
        }
        //printUnionFind(uf.getBoard());
        Set<Point> outerRoot = collectOuterRoot(outerO,uf);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    if (!outerRoot.contains(uf.find(new Point(i,j)))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
    public List<Point> collectOuterO(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { res.add(new Point(0,i)); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { res.add(new Point(i,width-1)); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { res.add(new Point(height-1,i)); }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { res.add(new Point(i,0)); }
        }
        return res;
    }
    public Set<Point> collectOuterRoot(List<Point> outerO, UnionFind uf) {
        Set<Point> res = new HashSet<>();
        for (Point p : outerO) {
            res.add(uf.find(p));
        }
        return res;
    }
    public Set<Point> getOuterO(char[][] board, UnionFind uf) {
        Set<Point> res = new HashSet<>();
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { res.add(uf.find(new Point(0,i))); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { res.add(uf.find(new Point(i,width-1))); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { res.add(uf.find(new Point(height-1,i))); }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { res.add(uf.find(new Point(i,0))); }
        }
        return res;
    }
    /**
     * 如果最外圈没有存活的O，就不用麻烦用Union Find了，直接全部填满X，然后返回。
     */
    public boolean checkOuterO(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { return true; }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { return true; }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { return true; }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { return true; }
        }
        return false;
    }

    private static interface UnionFindInt {
        public int find(int p); // 找到p的根节点
        public void follow(int p, int q); // 把新节点p接到树q上
        public void union(int p, int q); // 合并树p和树q
        public boolean connected(int p, int q); // 检查p点和q点是否属于同一棵树F
        public void create(int p); // 创建一棵新的以p点为根的树。因为Union Find中Point都初始化为null。
        public int[] getBoard(); // 方便打印board
    }
    private static class UnionFindIntImp implements UnionFindInt {
        private int[] board;
        private UnionFindIntImp(int size) {
            board = new int[size];
        }
        public int find(int p) {
            int cur = p;
            do {
                cur = board[cur];
            } while (board[cur] != cur);
            board[p] = cur; // 路径压缩
            return cur;
        }
        public void follow(int p, int q) {
            board[p] = board[q];
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { return; } // 已经属于同一棵树
            board[rootP] = rootQ;
        }
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
        public void create(int p) {
            board[p] = p;
        }
        public int[] getBoard() {
            return board;
        }
    }

    /**
     * 使用基于int的 Union Find
     */
    public void solveV8(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        List<Integer> outerO = collectOuterOWithInt(board);
        if (outerO.isEmpty()) {
            allCaptured(board); return;
        }
        int height = board.length;
        int width = board[0].length;
        // 可以选用不同的UnionFind实现类。比较效率。
        UnionFindInt uf = new UnionFindIntImp(height*width);
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j;
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf.follow(index,index - width); // 先把当前点接到楼上节点后面
                        uf.union(index,index-1); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf.follow(index,index-width);
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf.follow(index,index-1);
                    } else { // 建一棵新树
                        uf.create(index);
                    }
                }
            }
        }
        //printUnionFind(uf.getBoard());
        Set<Integer> outerRoot = collectOuterRootWithInt(outerO,uf);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j;
                    if (!outerRoot.contains(uf.find(index))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
    public List<Integer> collectOuterOWithInt(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < width-1; i++) { // top line
            if (board[0][i] == 'O') { res.add(i); }
        }
        for (int i = 0; i < height-1; i++) { // right col
            if (board[i][width-1] == 'O') { res.add(i*width+width-1); }
        }
        for (int i = width-1; i > 0; i--) { // buttom line
            if (board[height-1][i] == 'O') { res.add((height-1)*width+i); }
        }
        for (int i = height-1; i > 0; i--) { // left col
            if (board[i][0] == 'O') { res.add(i*width); }
        }
        return res;
    }
    public Set<Integer> collectOuterRootWithInt(List<Integer> outerO, UnionFindInt uf) {
        Set<Integer> res = new HashSet<>();
        for (Integer p : outerO) {
            res.add(uf.find(p));
        }
        return res;
    }

    /**
     * 不用数据结构UnionFind。直接用局部变量数组。
     */
    public void solveV9(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        List<Integer> outerO = collectOuterOWithInt(board);
        if (outerO.isEmpty()) { allCaptured(board); return; }
        int height = board.length;
        int width = board[0].length;
        // 用数组代替UnionFind类型
        int[] uf = new int[width*height];
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j;
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf[index] = index - width;
                        union(uf,index,index-1); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf[index] = index - width;
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf[index] = index - 1;
                    } else { // 建一棵新树
                        uf[index] = index;
                    }
                }
            }
        }
        Set<Integer> outerRoot = new HashSet<>();
        for (Integer p : outerO) { outerRoot.add(find(uf,p)); }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j;
                    if (!outerRoot.contains(find(uf,index))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
    /**
     * 不用数据结构UnionFind。直接用局部变量数组。
     * 而且也不重新遍历外圈。在union的时候，统一处理外圈信息。
     */
    public void solveV10(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        int height = board.length;
        int width = board[0].length;
        int size = width * height;
        // 用数组代替UnionFind类型
        int[] uf = new int[size];
        boolean[] isEdge = new boolean[size];
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j;
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf[index] = index - width;
                        union2(uf,isEdge,index,index-1); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf[index] = index - width;
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf[index] = index - 1;
                    } else { // 建一棵新树
                        uf[index] = index;
                    }
                    if ((i==0) || (i==height-1) || (j==0) || (j==width-1)) { isEdge[find(uf,uf[index])] = true; }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O' && !isEdge[find(uf,i*width+j)]) {
                        board[i][j] = 'X';
                }
            }
        }
    }
    public int find(int[] board, int p) {
        int cur = p;
        do {
            cur = board[cur];
        } while (board[cur] != cur);
        board[p] = cur; // 路径压缩
        return cur;
    }
    public void union(int[] board, int p, int q) {
        int rootP = find(board,p);
        int rootQ = find(board,q);
        if (rootP == rootQ) { return; } // 已经属于同一棵树
        board[rootP] = rootQ;
    }
    // 配合V10的isEdge数组
    public void union2(int[] board, boolean[] isEdge, int p, int q) {
        int rootP = find(board,p);
        int rootQ = find(board,q);
        if (rootP == rootQ) { return; } // 已经属于同一棵树
        board[rootP] = rootQ;
        if (isEdge[rootP] || isEdge[rootQ]) { isEdge[rootQ] = true; }
    }


    /**
     * 用负数表示沾外圈。
     * 不用数据结构UnionFind。直接用局部变量数组。
     * 而且也不重新遍历外圈。在union的时候，统一处理外圈信息。
     */
    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) { return; }
        int height = board.length;
        int width = board[0].length;
        int size = width * height + 1;
        // 用数组代替UnionFind类型
        int[] uf = new int[size];
        for (int i = 0; i < height; i++) { // Union Find 生成树
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    int index = i * width + j + 1; // 0 不能用
                    if (i > 0 && board[i-1][j] == 'O' && j > 0 && board[i][j-1] == 'O') { // 需要union()两棵树
                        uf[index] = uf[index - width];
                        union3(uf,index,index-1); // 再union()两棵老树
                    } else if (i > 0 && board[i-1][j] == 'O') { // follow()楼上点
                        uf[index] = uf[index - width];
                    } else if (j > 0 && board[i][j-1] == 'O') { // follow()左边点
                        uf[index] = uf[index - 1];
                    } else { // 建一棵新树
                        uf[index] = index;
                    }
                    if ((i==0) || (i==height-1) || (j==0) || (j==width-1)) { // 外圈
                        int root = Math.abs(find2(uf,index));
                        uf[root] = 0-root;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(uf));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O' && uf[find2(uf,i*width+j+1)] > 0) { // 沾外圈的根节点为负数
                    //System.out.println("Index = " + find2(uf,i*width+j+1));
                    //System.out.println("Value = " + uf[find2(uf,i*width+j+1)]);
                    board[i][j] = 'X';
                }
            }
        }
    }
    public int find2(int[] board, int p) { // 有可能是负数
        int cur = p;
        do {
            cur = Math.abs(board[cur]);
        } while (Math.abs(board[cur]) != cur);
        board[p] = board[cur]; // 路径压缩，注意如果根节点是不可捕获的，值为负数，压缩路径的时候也要写成负数，否则根节点就不能保持负数。
        return cur;
    }
    public void union3(int[] board, int p, int q) { // 用负数表示沾外圈
        int rootP = find2(board,p);
        int rootQ = find2(board,q);
        if (rootP == rootQ) { return; } // 已经属于同一棵树
        if (board[rootP] < 0) {
            board[rootQ] = 0 - Math.abs(board[rootQ]);
        } else {
            board[rootP] = board[rootQ];
        }
    }

    public static class SolutionV1 {
        // 数组拿出来，防止递归过深
        private int[] uf;
        private boolean[] isEdge;
        /**
         * 不用数据结构UnionFind。直接用局部变量数组。
         * 而且也不重新遍历外圈。在union的时候，统一处理外圈信息。
         */
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            int size = width * height;
            // 用数组代替UnionFind类型
            uf = new int[size];
            isEdge = new boolean[size];
            for (int i = 0; i < uf.length; i++) { // Union Find 生成树
                uf[i] = i;
                int row = i / width, col = i % width;
                if ((row==0) || (row==height-1) || (col==0) || (col==width-1)) { isEdge[i] = true; } //标记沾到外圈
                if (board[row][col] == 'O') {
                    if (row > 0 && board[row-1][col] == 'O') { // follow()楼上点
                        union(i,i-width);
                    }
                    if (col > 0 && board[row][col-1] == 'O') { // follow()左边点
                        union(i,i-1);
                    }
                }
            }
            for (int i = 0; i < uf.length; i++) {
                int row = i / width, col = i % width;
                if (board[row][col] == 'O' && !isEdge[find(i)]) {
                        board[row][col] = 'X';
                }
            }
        }
        // 递归版。优点是能将路过的所有点都压缩路径。
        public int find(int p) {
            if (uf[p] == p) { return p; }
            uf[p] = find(uf[p]);
            return uf[p];
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { return; } // 已经属于同一棵树
            uf[rootP] = rootQ;
            boolean b = isEdge[rootP] || isEdge[rootQ];
            isEdge[rootP] = b;
            isEdge[rootQ] = b;
        }
    }
    public static class SolutionV2 {
        // 数组拿出来，防止递归过深
        private int[] uf;
        private boolean[] isEdge;
        /**
         * 不用数据结构UnionFind。直接用局部变量数组。
         * 而且也不重新遍历外圈。在union的时候，统一处理外圈信息。
         */
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            int size = width * height;
            // 用数组代替UnionFind类型
            uf = new int[size];
            isEdge = new boolean[size];
            for (int i = 0; i < uf.length; i++) { // Union Find 生成树
                int row = i / width, col = i % width;
                if (board[row][col] == 'O') {
                    uf[i] = i;
                    if ((row==0) || (row==height-1) || (col==0) || (col==width-1)) { isEdge[i] = true; } //标记是否外圈
                    if (row > 0 && board[row-1][col] == 'O') { follow(i,i-width); } // 简单follow()楼上
                    if (col > 0 && board[row][col-1] == 'O') { union(i,i-1); } // 左邻
                }
            }
            for (int i = 0; i < uf.length; i++) {
                int row = i / width, col = i % width;
                if (board[row][col] == 'O' && !isEdge[find(i)]) {
                        board[row][col] = 'X';
                }
            }
        }
        // 递归版。优点是能将路过的所有点都压缩路径。
        public int find(int p) {
            if (uf[p] == p) { return p; }
            uf[p] = find(uf[p]);
            return uf[p];
        }
        public void follow(int p, int q) { // p点简单follow() q树
            uf[p] = uf[q];
            int rootQ = find(q);
            isEdge[rootQ] = isEdge[p] || isEdge[rootQ];
        }
        public void union(int p, int q) { // p树嫁接到q树
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { return; } // 已经属于同一棵树
            uf[rootP] = rootQ;
            isEdge[rootQ] = isEdge[rootP] || isEdge[rootQ];
        }
    }

    public static class SolutionV3 {
        // 数组拿出来，防止递归过深
        private int[] uf;
        private boolean[] isEdge;
        /**
         * 不用数据结构UnionFind。直接用局部变量数组。
         * 而且也不重新遍历外圈。在union的时候，统一处理外圈信息。
         */
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            int size = width * height;
            // 用数组代替UnionFind类型
            uf = new int[size];
            isEdge = new boolean[size];
            for (int row = 0; row < height; row++) { // Union Find 生成树
                for (int col = 0; col < width; col++) {
                    int i = row * width + col;
                    if (board[row][col] == 'O') {
                        uf[i] = i;
                        if ((row==0) || (row==height-1) || (col==0) || (col==width-1)) { isEdge[i] = true; } //标记是否外圈
                        if (row > 0 && board[row-1][col] == 'O') { follow(i,i-width); } // 简单follow()楼上
                        if (col > 0 && board[row][col-1] == 'O') { union(i,i-1); } // 左邻
                    }
                }
            }
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int i = row * width + col;
                    if (board[row][col] == 'O' && !isEdge[find(i)]) {
                        board[row][col] = 'X';
                    }
                }
            }
        }
        // 递归版。优点是能将路过的所有点都压缩路径。
        public int find(int p) {
            if (uf[p] == p) { return p; }
            uf[p] = find(uf[p]);
            return uf[p];
        }
        public void follow(int p, int q) { // p点简单follow() q树
            uf[p] = uf[q];
            int rootQ = find(q);
            isEdge[rootQ] = isEdge[p] || isEdge[rootQ];
        }
        public void union(int p, int q) { // p树嫁接到q树
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { return; } // 已经属于同一棵树
            uf[rootP] = rootQ;
            isEdge[rootQ] = isEdge[rootP] || isEdge[rootQ];
        }
    }

    public static class SolutionV4 {
        private static class Point {
            private int row;
            private int col;
            private Point(int row, int col) { this.row = row; this.col = col; }
        }
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            for (int i = 0; i < width-1; i++) { // top line
                if (board[0][i] == 'O') { board[0][i] = 'F'; harvestPoints(board,new Point(1,i)); }
            }
            for (int i = 0; i < height-1; i++) { // right col
                if (board[i][width-1] == 'O') { board[i][width-1] = 'F'; harvestPoints(board,new Point(i,width-2)); }
            }
            for (int i = width-1; i > 0; i--) { // buttom line
                if (board[height-1][i] == 'O') { board[height-1][i] = 'F'; harvestPoints(board,new Point(height-2,i)); }
            }
            for (int i = height-1; i > 0; i--) { // left col
                if (board[i][0] == 'O') { board[i][0] = 'F'; harvestPoints(board,new Point(i,1)); }
            }
            replaceAll(board,'O','X'); // kill all captured
            replaceAll(board,'F','O'); // remark all survivers
        }
        public void harvestPoints(char[][] board, Point root) {
            List<Point> buffer = new ArrayList<>();
            buffer.add(root);
            while (!buffer.isEmpty()) {
                Point p = buffer.remove(0);
                if (outOfRange(board.length,board[0].length,p)) { continue; }
                if (board[p.row][p.col] == 'O') {
                    board[p.row][p.col] = 'F';
                    buffer.add(new Point(p.row-1,p.col)); // up
                    buffer.add(new Point(p.row+1,p.col)); // down
                    buffer.add(new Point(p.row,p.col-1)); // left
                    buffer.add(new Point(p.row,p.col+1)); // right
                }
            }
        }
        public boolean outOfRange(int height, int width, Point p) {
            return p.row <= 0 || p.row >= height-1 || p.col <= 0 || p.col >= width-1;
        }
        public void replaceAll(char[][] board,char oldC, char newC) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == oldC) {
                        board[i][j] = newC;
                    }
                }
            }
        }
    }

    public static class SolutionV5 {
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            for (int i = 0; i < width-1; i++) { // top line
                if (board[0][i] == 'O') { board[0][i] = 'F'; dfs(board,1,i); }
            }
            for (int i = 0; i < height-1; i++) { // right col
                if (board[i][width-1] == 'O') { board[i][width-1] = 'F'; dfs(board,i,width-2); }
            }
            for (int i = width-1; i > 0; i--) { // buttom line
                if (board[height-1][i] == 'O') { board[height-1][i] = 'F'; dfs(board,height-2,i); }
            }
            for (int i = height-1; i > 0; i--) { // left col
                if (board[i][0] == 'O') { board[i][0] = 'F'; dfs(board,i,1); }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'O') { board[i][j] = 'X'; }
                    if (board[i][j] == 'F') { board[i][j] = 'O'; }
                }
            }
        }
        public void dfs(char[][] board, int row, int col) {
            List<Integer> buffer = new LinkedList<>();
            buffer.add(row); buffer.add(col);
            while (!buffer.isEmpty()) {
                int r = buffer.remove(0);
                int c = buffer.remove(0);
                if (r <= 0 || r >= board.length-1 || c <= 0 || c >= board[0].length-1) { continue; }
                if (board[r][c] == 'O') {
                    board[r][c] = 'F';
                    buffer.add(r-1); buffer.add(c);
                    buffer.add(r+1); buffer.add(c);
                    buffer.add(r); buffer.add(c-1);
                    buffer.add(r); buffer.add(c+1);
                }
            }
        }
    }


    public static class SolutionV6 {
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            //check first and last col
            for(int i=0;i<height;i++) {
                if(board[i][0]=='O') { board[i][0]='F'; dfs(board,i,1); }
                if(board[i][width-1]=='O') { board[i][width-1]='F'; dfs(board,i,width-2); }
            }
            //check first and last  row
            for(int i=0;i<width;i++) {
                if(board[0][i]=='O') { board[0][i]='F'; dfs(board,1,i); }
                if(board[height-1][i]=='O') { board[height-1][i]='F'; dfs(board,height-2,i); }
            }
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (board[i][j] == 'O') { board[i][j] = 'X'; }
                    if (board[i][j] == 'F') { board[i][j] = 'O'; }
                }
            }
        }
        public void dfs(char[][] board, int row, int col) {
            List<Integer> buffer = new LinkedList<>();
            buffer.add(row); buffer.add(col);
            while (!buffer.isEmpty()) {
                int r = buffer.remove(0);
                int c = buffer.remove(0);
                if (r <= 0 || r >= board.length-1 || c <= 0 || c >= board[0].length-1) { continue; }
                if (board[r][c] == 'O') {
                    board[r][c] = 'F';
                    buffer.add(r-1); buffer.add(c);
                    buffer.add(r+1); buffer.add(c);
                    buffer.add(r); buffer.add(c-1);
                    buffer.add(r); buffer.add(c+1);
                }
            }
        }
    }

    public static class Solution {
        public void solve(char[][] board) {
            if (board.length < 3 || board[0].length < 3) { return; }
            int height = board.length;
            int width = board[0].length;
            for (int i = 0; i < width-1; i++) { // top line
                if (board[0][i] == 'O') { board[0][i] = 'F'; dfs(board,1,i); }
            }
            for (int i = 0; i < height-1; i++) { // right col
                if (board[i][width-1] == 'O') { board[i][width-1] = 'F'; dfs(board,i,width-2); }
            }
            for (int i = width-1; i > 0; i--) { // buttom line
                if (board[height-1][i] == 'O') { board[height-1][i] = 'F'; dfs(board,height-2,i); }
            }
            for (int i = height-1; i > 0; i--) { // left col
                if (board[i][0] == 'O') { board[i][0] = 'F'; dfs(board,i,1); }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'O') { board[i][j] = 'X'; }
                    if (board[i][j] == 'F') { board[i][j] = 'O'; }
                }
            }
        }
        public void dfs(char[][] board, int row, int col) {
            if(row>=board.length-1 || row<=0 || col>=board[0].length-1 || col<=0) { return; } // 外圈和越界都不允许
            if(board[row][col]=='O') {
                board[row][col]='F';
                dfs(board,row+1,col);
                dfs(board,row,col+1);
                dfs(board,row-1,col);
                dfs(board,row,col-1);
            }
        }
    }

    /**
     * 测试区
     */
    private static SurroundedRegions test = new SurroundedRegions();
    private static char[][][] matrixs = new char[][][]{
        {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        },
        {
            {'O','O','O'},
            {'O','O','O'},
            {'O','O','O'}
        },
        {
            {'O','O','O','O','X','X'},
            {'O','O','O','O','O','O'},
            {'O','X','O','X','O','O'},
            {'O','X','O','O','X','O'},
            {'O','X','O','X','O','O'},
            {'O','X','O','O','O','O'}
        },
        {
            {'X','X','X'},
            {'X','O','X'},
            {'X','X','X'}
        },
        {
            {'O','X','X','X','X','X','O','O'},
            {'O','O','O','X','X','X','X','O'},
            {'X','X','X','X','O','O','O','O'},
            {'X','O','X','O','O','X','X','X'},
            {'O','X','O','X','X','X','O','O'},
            {'O','X','X','O','O','X','X','O'},
            {'O','X','O','X','X','X','O','O'},
            {'O','X','X','X','X','O','X','X'}
        }
    };
    private static void printUnionFind(Point[][] m) {
        if (m.length == 0) { System.out.println("This Matrix is Empty!"); return; }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] != null) {
                    System.out.print("[" + m[i][j].row + "," + m[i][j].col + "]" + "\t");
                } else {
                    System.out.print("[null]\t");
                }
            }
            System.out.print("\n\n");
        }
    }
    private static void testSolve() {
        for (char[][] m : matrixs) {
            System.out.println("####### Before #######");
            Matrix.print(m);
            test.solve(m);
            System.out.println("####### After #######");
            Matrix.print(m);
        }
    }
    private static void testSolve2() { // 专为Solution Class 设计
        Solution s = new Solution();
        for (char[][] m : matrixs) {
            System.out.println("####### Before #######");
            Matrix.print(m);
            s.solve(m);
            System.out.println("####### After #######");
            Matrix.print(m);
        }
    }
    private static void testSolveTime() {
        long sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i >= 100) {
                long start = System.nanoTime();
                for (char[][] m : matrixs) {
                    //test.solveV8(m);
                    test.solve(m);
                }
                long end = System.nanoTime();
                sum += end - start;
            }
        }
        sum /= 1000;
        System.out.println(sum);
    }
    private static void testMatrix() {
        Matrix.print(matrixs[0]);
    }
    public static void main(String[] args) {
        //testMatrix();
        //testSolve();
        //testSolveTime();
        testSolve2();
    }
}
