/**
 * Leetcode - Algorithm - DesignTwitter
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DesignTwitter implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DesignTwitter() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        //题目要求的接口
        abstract public void init();
        abstract public void postTweet(int userId, int tweetId);
        abstract public List<Integer> getNewsFeed(int userId);
        abstract public void follow(int followerId, int followeeId);
        abstract public void unfollow(int followerId, int followeeId);
        //我自己的测试函数
        abstract public List<Integer> getPosts(int userId);
        abstract public void printDataBase();
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        /**
         * 初始化数据库
         */
        public void init() {
            userPostsTable = new HashMap<Integer,LinkedList<Tweet>>();
            userNewsFeedTable = new HashMap<Integer,LinkedList<Tweet>>();
            userFollowersTable = new HashMap<Integer,Set<Integer>>();
            userFollowingTable = new HashMap<Integer,Set<Integer>>();
        }
        /**
         * 用户每发布一篇新文章，系统要做3件事，
         *      1. 更新自己的原创文章列表
         *      2. 更新自己的最近提要列表
         *      3. 更新所有粉丝的最近提要列表
         *
         * 假设系统使用的userId和tweetId不会重复，我不做这方面的检查
         */
        public void postTweet(int userId, int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            //更新原创文章
            updatePostsList(userId,tweet);
            //更新最近提要
            updateNewsFeedList(userId,tweet);
            //更新所有粉丝的最近提要
            if (userFollowersTable.containsKey(userId)) {
                for (Integer follower : userFollowersTable.get(userId)) {
                    updateNewsFeedList(follower,tweet);
                }
            }
        }
        /**
         * 返回news feed视图
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<Integer>();
            if (userNewsFeedTable.containsKey(userId)) {
                for (Tweet tweet : userNewsFeedTable.get(userId)) {
                    res.add(tweet.tweetId);
                }
            }
            return res;
        }
        /**
         * 粉丝关注一个偶像，系统会尝试将这个偶像的文章推送到粉丝的最近提要列表
         */
        public void follow(int followerId, int followeeId) {
            boolean newFollowee = false;
            //给粉丝更新偶像列表
            if (!userFollowingTable.containsKey(followerId)) {
                userFollowingTable.put(followerId,new HashSet<Integer>());
            }
            newFollowee = userFollowingTable.get(followerId).add(followeeId);   //已关注的偶像不重复关注
            //给偶像更新粉丝列表
            if (!userFollowersTable.containsKey(followeeId)) {
                userFollowersTable.put(followeeId,new HashSet<Integer>());
            }
            userFollowersTable.get(followeeId).add(followerId);
            //更新粉丝最近提要（重复关注已有偶像不更新）
            if (newFollowee && userPostsTable.containsKey(followeeId)) {
                for (Tweet tweet : userPostsTable.get(followeeId)) {
                    updateNewsFeedList(followerId, tweet);
                }
            }
        }

        /**
         * 粉丝取关一个偶像，他的最近提要列表也会被更新
         */
        public void unfollow(int followerId, int followeeId) {
            boolean unfollowed = false;
            //给粉丝更新偶像列表
            if (userFollowingTable.containsKey(followerId)) {
                unfollowed = userFollowingTable.get(followerId).remove(followeeId);
            }
            //给偶像更新粉丝列表
            if (userFollowersTable.containsKey(followeeId)) {
                userFollowersTable.get(followeeId).remove(followerId);
            }
            if (unfollowed) {
                //先清空所有最近提要
                userNewsFeedTable.put(followerId,new LinkedList<Tweet>());
                //重新添加粉丝自己的原创文章
                if (userPostsTable.containsKey(followerId)) {
                    for (Tweet tweet : userPostsTable.get(followerId)) {
                        updateNewsFeedList(followerId,tweet);
                    }
                }
                //重新添加所有偶像的文章
                if (userFollowingTable.containsKey(followerId)) {
                    for (Integer followee : userFollowingTable.get(followerId)) {
                        if (userPostsTable.containsKey(followee)) {
                            for (Tweet tweet : userPostsTable.get(followee)) {
                                updateNewsFeedList(followerId,tweet);
                            }
                        }
                    }
                }
            }
        }


        /*==================== 【私有成员】 =======================*/

        private final int MAX = 10;                                     //最近提要最大文章数

        //模拟关系型数据库的映射表
        private Map<Integer,LinkedList<Tweet>> userPostsTable;          //用户原创文章列表的集合（无需按时间戳排序）
        private Map<Integer,LinkedList<Tweet>> userNewsFeedTable;       //用户头最近提要列表的集合（按时间戳排序）
        private Map<Integer,Set<Integer>> userFollowersTable;           //用户粉丝列表的集合（去重）
        private Map<Integer,Set<Integer>> userFollowingTable;           //用户关注对象集合（去重）


        //更新不按时间戳排序的原创文章列表
        private void updatePostsList(int userId, Tweet tweet) {
            if (userPostsTable.containsKey(userId) && !userPostsTable.get(userId).contains(tweet)) { //不重复发布同一篇文章
                userPostsTable.get(userId).add(tweet);
            } else {
                userPostsTable.put(userId,new LinkedList<Tweet>(Arrays.asList(new Tweet[]{tweet})));
            }
        }
        //更新按时间戳排序的最近提要列表
        private void updateNewsFeedList(int userId, Tweet tweet) {
            if (userNewsFeedTable.containsKey(userId)) {
                LinkedList<Tweet> newsFeed = userNewsFeedTable.get(userId);
                if (!newsFeed.contains(tweet)) {    //已经有的文章不重新添加
                    boolean updated = false;
                    for (int i = 0; i < newsFeed.size(); i++) {
                        if (newsFeed.get(i).compareTo(tweet) < 0) { // 时间戳大的在前
                            newsFeed.add(i,tweet);
                            updated = true;
                            break;
                        }
                    }
                    if (!updated) { newsFeed.addLast(tweet); }
                    if (newsFeed.size() > MAX) { newsFeed.removeLast(); }
                }
            } else {
                userNewsFeedTable.put(userId,new LinkedList<Tweet>(Arrays.asList(new Tweet[]{tweet})));
            }
        }
        /**
         * 带时间戳的文章对象
         */
        private class Tweet implements Comparable<Tweet> {
            private int tweetId;
            private long timeStamp; //每篇文章都有个时间戳

            private Tweet(int tweetId) {
                this.tweetId = tweetId;
                timeStamp = System.nanoTime();
            }
            //假设tweetId具有唯一性。区分不同的Tweet个体不依赖时间戳。时间戳仅用来按时间排序。
            public boolean equals(Object another) {
                return this.tweetId == ((Tweet)another).tweetId;
            }
            private int hash = -1;
            public int hashCode() {
                if (hash < 0) { //惰性初始化
                    hash = tweetId;
                }
                return hash;
            }
            //以时间戳降序排序，时间戳相同以id升序排序（时间戳越大，文章越新）
            public int compareTo(Tweet another) {
                if (this.timeStamp != another.timeStamp) {
                    return (int) (this.timeStamp - another.timeStamp);
                } else {
                    return this.tweetId - another.tweetId;
                }
            }
            public String toString() {
                return "ID = " + tweetId + ",  Time Stamp = " + timeStamp;
            }
        }


        /** ====================【单元测试专用函数】==================== */

        //查看某个用户所有的原创文章
        public List<Integer> getPosts(int userId) {
            List<Integer> res = new ArrayList<>();
            if (userPostsTable.containsKey(userId)) {
                for (Tweet tweet : userPostsTable.get(userId)) {
                    res.add(tweet.tweetId);
                }
            }
            return res;
        }
        //打印整个数据库
        public void printDataBase() {
            //原创文章表
            System.out.println("\n>>> User-Posts Table: ");
            System.out.println(userPostsTable);
            //新闻提要表
            System.out.println("\n>>> User-News Feed Table: ");
            System.out.println(userNewsFeedTable);
            //关注表
            System.out.println("\n>>> User-Following Table: ");
            System.out.println(userFollowingTable);
            //被关注表
            System.out.println("\n>>> User-Followers Table: ");
            System.out.println(userFollowersTable);
        }

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void init() {
            time = 0;
            users = new HashMap<Integer,User>();
        }
        public void postTweet(int userId, int tweetId) {
            User u = confirmUser(userId);
            Tweet t = new Tweet(userId,tweetId);
            u.posts.add(t);                     //更新自己原创列表
            insertIntoNewsFeed(u,t);            //更新自己摘要
            for (User follower : u.followers) { //更新粉丝摘要
                insertIntoNewsFeed(follower,t);
            }
        }
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            User u = confirmUser(userId);
            Iterator<Tweet> ite = u.newsFeed.iterator();
            for (int remain = MAX; ite.hasNext() && remain > 0; remain--) {
                res.add(ite.next().tweetId);
            }
            return res;
        }
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) { return; }
            User follower = confirmUser(followerId);
            User followee = confirmUser(followeeId);
            if (!followee.followers.contains(follower)) {
                followee.followers.add(follower);   //更新粉丝列表
                for (Tweet t : followee.posts) { //更新摘要
                    insertIntoNewsFeed(follower,t);
                }
            }
        }
        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) { return; }
            User follower = confirmUser(followerId);
            User followee = confirmUser(followeeId);
            followee.followers.remove(follower);                 //更新粉丝列表
            Iterator<Tweet> ite = follower.newsFeed.iterator();  //更新摘要
            while (ite.hasNext()) {
                Tweet t = ite.next();
                if (t.userId == followee.userId) {
                    ite.remove();
                }
            }
        }

        /** ====================【私有成员】==================== */

        //断言用户存在
        private void insertIntoNewsFeed(User u, Tweet t) {
            LinkedList<Tweet> newsFeed = u.newsFeed;
            for (int i = 0; i < newsFeed.size(); i++) {
                if (newsFeed.get(i).compareTo(t) < 0) {
                    newsFeed.add(i,t);
                    return;
                }
            }
            newsFeed.offerLast(t);
        }
        //创建新用户
        private User confirmUser(int userId) {
            if (!users.containsKey(userId)) {
                users.put(userId,new User(userId));
            }
            return users.get(userId);
        }


        private final int MAX = 10; //new feed的大小
        private int time;
        private Map<Integer,User> users;

        private class User {
            private int userId;
            private LinkedList<Tweet> posts;
            private LinkedList<Tweet> newsFeed;
            private Set<User> followers;

            private User(int userId) {
                this.userId = userId;
                posts = new LinkedList<Tweet>();
                newsFeed = new LinkedList<Tweet>();
                followers = new HashSet<User>();
            }
        }
        private class Tweet implements Comparable<Tweet> {
            private int tweetId;
            private int userId;
            private int timeStamp;

            private Tweet(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
                this.timeStamp = ++time;
            }

            public int compareTo(Tweet t) {
                return timeStamp - t.timeStamp; //时间戳越大，文章越新
            }
        }

        /** ====================【单元测试专用函数】==================== */
        public List<Integer> getPosts(int userId) { return null; }
        public void printDataBase() { }

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init() {
            time = 0;
            users = new HashMap<Integer,User>();
        }
        public void postTweet(int userId, int tweetId) {
            User u = confirmUser(userId);
            Tweet t = new Tweet(userId,tweetId);
            u.posts.add(t);                     //更新自己原创列表
            u.newsFeed.add(t);                  //更新自己摘要
            for (User follower : u.followers) { //更新粉丝摘要
                follower.newsFeed.add(t);
            }
        }
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            List<Tweet> removed = new ArrayList<>();
            User u = confirmUser(userId);
            for (int remain = MAX; !u.newsFeed.isEmpty() && remain > 0; remain--) {
                Tweet t = u.newsFeed.poll();
                removed.add(t);
                res.add(t.tweetId);
            }
            for (Tweet t : removed) {
                u.newsFeed.add(t);
            }
            return res;
        }
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) { return; }
            User follower = confirmUser(followerId);
            User followee = confirmUser(followeeId);
            if (!followee.followers.contains(follower)) {
                followee.followers.add(follower);   //更新粉丝列表
                for (Tweet t : followee.posts) { //更新摘要
                    follower.newsFeed.add(t);
                }
            }
        }
        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) { return; }
            User follower = confirmUser(followerId);
            User followee = confirmUser(followeeId);
            followee.followers.remove(follower);                 //更新粉丝列表
            Iterator<Tweet> ite = follower.newsFeed.iterator();  //更新摘要
            while (ite.hasNext()) {
                Tweet t = ite.next();
                if (t.userId == followee.userId) {
                    ite.remove();
                }
            }
        }

        /** ====================【私有成员】==================== */

        //创建新用户
        private User confirmUser(int userId) {
            if (!users.containsKey(userId)) {
                users.put(userId,new User(userId));
            }
            return users.get(userId);
        }


        private final int MAX = 10; //new feed的大小
        private int time;
        private Map<Integer,User> users;

        private class User {
            private int userId;
            private LinkedList<Tweet> posts;
            private PriorityQueue<Tweet> newsFeed;
            private Set<User> followers;

            private User(int userId) {
                this.userId = userId;
                posts = new LinkedList<Tweet>();
                newsFeed = new PriorityQueue<Tweet>();
                followers = new HashSet<User>();
            }
        }
        private class Tweet implements Comparable<Tweet> {
            private int tweetId;
            private int userId;
            private int timeStamp;

            private Tweet(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
                this.timeStamp = ++time;
            }

            public int compareTo(Tweet t) {
                return t.timeStamp - timeStamp; //时间戳越大，文章越新
            }
        }

        /** ====================【单元测试专用函数】==================== */
        public List<Integer> getPosts(int userId) { return null; }
        public void printDataBase() { }

    }



    /**
     * 全抽取策略
     * 每个用户完全不维护new feed列表。只维护一个他的偶像列表。
     * 每次调用getNewsFeed()函数都到每个偶像的原创文章列表里去抽取。
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        public void init() {
            time = 0;
            users = new HashMap<Integer,User>();
        }
        public void postTweet(int userId, int tweetId) {
            User u = confirmUser(userId);
            Tweet t = new Tweet(userId,tweetId);
            u.posts.add(t);
        }
        public List<Integer> getNewsFeed(int userId) {
            User u = confirmUser(userId);
            PriorityQueue<Tweet> candidates = new PriorityQueue<>();
            Map<Integer,List<Tweet>> selected = new HashMap<>();
            List<Integer> result = new ArrayList<>();
            for (Map.Entry<Integer,PriorityQueue<Tweet>> idol : u.idols.entrySet()) { //先每个作者选一篇最新的
                if (!idol.getValue().isEmpty()) {
                    candidates.add(idol.getValue().poll());
                }
            }
            for (int remain = MAX; remain > 0 && candidates.size() > 0; remain--) {
                Tweet newest = candidates.poll();
                int idolId = newest.userId;
                System.out.println("作者#[" + idolId + "]的作品#[" + newest.tweetId + "]被抽出来了");
                if (!u.idols.get(idolId).isEmpty()) {
                    candidates.add(u.idols.get(idolId).poll()); //摘录某偶像的一篇文章，候选列表里马上补上下一篇
                }
                if (!selected.containsKey(newest.userId)) {
                    selected.put(idolId,new ArrayList<Tweet>());
                }
                selected.get(idolId).add(newest); //把Tweet对象存起来，准备还给每个作者的原创列表
                result.add(newest.tweetId); //记录结果
            }
            for (Map.Entry<Integer,List<Tweet>> selectedEntry : selected.entrySet()) { //把取出来的文章塞回作者的原创列表
                int selectedIdol = selectedEntry.getKey();
                for (Tweet t : selectedEntry.getValue()) {
                    System.out.println("作者#[" + selectedIdol + "]的作品#[" + t.tweetId + "]被还回去了");
                    u.idols.get(selectedIdol).add(t);
                }
            }
            for (Tweet candidate : candidates) { //把未入选的候选文章也还回去
                u.idols.get(candidate.userId).add(candidate);
            }
            System.out.println("作者#[" + userId + "]的原创作品有：" + u.posts);
            return result;
        }
        //往粉丝的偶像列表里添加新偶像
        public void follow(int followerId, int followeeId) {
            User fans = confirmUser(followerId);
            User idol = confirmUser(followeeId);
            fans.idols.put(followeeId,idol.posts);
        }
        //从粉丝的偶像列表中删去某个偶像
        public void unfollow(int followerId, int followeeId) {
            if (followerId != followeeId) { //不能取关自己
                User fans = confirmUser(followerId);
                fans.idols.remove(followeeId);
            }
        }

        /** ====================【私有成员】==================== */

        //创建新用户
        private User confirmUser(int userId) {
            if (!users.containsKey(userId)) {
                users.put(userId,new User(userId));
            }
            return users.get(userId);
        }


        private final int MAX = 10; //new feed的大小
        private int time;
        private Map<Integer,User> users;

        private class User {
            private int userId;
            private PriorityQueue<Tweet> posts;
            private Map<Integer,PriorityQueue<Tweet>> idols;

            private User(int userId) {
                this.userId = userId;
                posts = new PriorityQueue<Tweet>();
                idols = new HashMap<Integer,PriorityQueue<Tweet>>();
                idols.put(this.userId,this.posts); //每个人首先都订阅自己的原创文章
            }
        }
        private class Tweet implements Comparable<Tweet> {
            private int tweetId;
            private int userId;
            private int timeStamp;

            private Tweet(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
                this.timeStamp = ++time;
            }
            public int compareTo(Tweet t) {
                return t.timeStamp - timeStamp; //时间戳越大，文章越新
            }
            public String toString() {
                return "Tweet#[" + tweetId + "]";
            }
        }

        /** ====================【单元测试专用函数】==================== */
        public List<Integer> getPosts(int userId) { return null; }
        public void printDataBase() { }

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
        private DesignTwitter problem = new DesignTwitter();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] posts, int[][] follows, int userId, String answer1, String answer2) {
            solution.init();
            for (int[] post : posts) {
                solution.postTweet(post[0],post[1]);
            }
            System.out.println("user [#" + userId + "] has news feed: " + solution.getNewsFeed(userId));
            System.out.println("Answer should be: " + answer1);
            for (int[] follow : follows) {
                solution.follow(follow[0],follow[1]);
            }
            System.out.println("user [#" + userId + "] has news feed: " + solution.getNewsFeed(userId));
            System.out.println("Answer should be: " + answer2);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] posts1 = new int[][]{{1,5},{2,6}};
            int[][] follows1 = new int[][]{{1,2}};
            int userId1 = 1;
            String answer11 = "[5]";
            String answer12 = "[5,6]";

            /** involk call() method HERE */
            call(posts1,follows1,userId1,answer11,answer12);
        }

        /**
         * 这题测试比较复杂，单拎出来做个单元测试。
         */
        public void specialTest(int id) {
            solution = problem.solution(id);
            solution.init();
            //测试post，独立的用户更新他们的文章，不涉及关注关系
            int[][] posts1 = new int[][]{{3,4},{2,3},{4,7},{2,9},{1,11},{1,2},{5,5},{4,6},{3,10},{1,1},{2,8},{3,12}};
            for (int[] post : posts1) {
                solution.postTweet(post[0],post[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            String[] answer1 = new String[]{"1\t->\t[1,2,11]", "2\t->\t[8,9,3]", "3\t->\t[12,10,4]", "4\t->\t[6,7]", "5\t->\t[5]"};
            for (String s : answer1) {
                System.out.println(s);
            }
            solution.printDataBase();
            //测试follow。保持刚才发布的文章不变，尝试加入几条关注关系，看看会发生什么。
            int[][] follows1 = new int[][]{{2,5},{1,3},{1,4}};
            for (int[] follow : follows1) {
                solution.follow(follow[0],follow[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            String[] answer2 = new String[]{"1\t->\t[12,1,10,6,2,11,7,4]", "2\t->\t[8,5,9,3]", "3\t->\t[12,10,4]", "4\t->\t[6,7]", "5\t->\t[5]"};
            for (String s : answer2) {
                System.out.println(s);
            }
            solution.printDataBase();
            //再注销刚才的关注关系。看数据库是否恢复未添加关注之前的状态。
            for (int[] follow : follows1) {
                solution.unfollow(follow[0],follow[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            for (String s : answer1) {
                System.out.println(s);
            }
            solution.printDataBase();
        }
        public void specialTest2(int id) {
            solution = problem.solution(id);
            solution.init();
            //测试post，独立的用户更新他们的文章，不涉及关注关系
            int[][] posts1 = new int[][]{
                {1,6765},{5,671},{3,2868},{4,8148},{4,386},{3,6673},{3,7946},
                {3,1445},{4,4822},{1,3781},{4,9038},{1,9643},{3,5917},{2,8847}
            };
            for (int[] post : posts1) {
                solution.postTweet(post[0],post[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            // String[] answer1 = new String[]{"1\t->\t[1,2,11]", "2\t->\t[8,9,3]", "3\t->\t[12,10,4]", "4\t->\t[6,7]", "5\t->\t[5]"};
            // for (String s : answer1) {
            //     System.out.println(s);
            // }
            //测试follow。保持刚才发布的文章不变，尝试加入几条关注关系，看看会发生什么。
            int[][] follows1 = new int[][]{{1,3},{1,4},{4,2},{4,1},{3,2},{3,5},{3,1},{2,3},{2,1},{2,5},{5,1},{5,2}};
            for (int[] follow : follows1) {
                solution.follow(follow[0],follow[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            String[] answer2 = new String[]{"[5917,9643,9038,3781,4822,1445,7946,6673,386,8148],[8847,5917,9643,3781,1445,7946,6673,2868,671,6765],[8847,5917,9643,3781,1445,7946,6673,2868,671,6765],[8847,9643,9038,3781,4822,386,8148,6765],[8847,9643,3781,671,6765]]"};
            for (String s : answer2) {
                System.out.println(s);
            }
        }
        public void generalSpecialTest(int id, int[][] posts, String[] answerBefore, int[][] follows, String[] answerAfter) {
            solution = problem.solution(id);
            solution.init();
            //测试post，独立的用户更新他们的文章，不涉及关注关系
            for (int[] post : posts) {
                solution.postTweet(post[0],post[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            for (String s : answerBefore) {
                System.out.println(s);
            }
            //测试follow。保持刚才发布的文章不变，尝试加入几条关注关系，看看会发生什么。
            for (int[] follow : follows) {
                solution.follow(follow[0],follow[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            for (String s : answerAfter) {
                System.out.println(s);
            }
            //再注销刚才的关注关系。看数据库是否恢复未添加关注之前的状态。
            for (int[] follow : follows) {
                solution.unfollow(follow[0],follow[1]);
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("User #[" + i + "] posts: " + solution.getPosts(i));
                System.out.println("User #[" + i + "] news feed: " + solution.getNewsFeed(i));
            }
            for (String s : answerBefore) {
                System.out.println(s);
            }
        }
        private int[][] posts1 = new int[][]{{1,5},{2,3},{1,101},{2,13},{2,10},{1,2},{1,94},{2,505},{1,333},{2,22},{1,11},{1,205},{2,203},{1,201},{2,213},{1,200},{2,202},{1,204},{2,208},{2,233},{1,222},{2,211}};
        private String[] answer1 = new String[]{"[222,204,200,201,205,11,333,94,2,101]"};
        private int[][] follows1 = new int[][]{{1,2}};
        private String[] answer2 = new String[]{"[211,222,233,208,204,202,200,213,201,203]"};
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.specialTest(1);
        // test.specialTest(2);
        // test.specialTest(3);
        // test.specialTest2(3);
        test.generalSpecialTest(4,test.posts1,test.answer1,test.follows1,test.answer2);
    }
}
