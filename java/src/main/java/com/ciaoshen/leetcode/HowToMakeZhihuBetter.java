/**
 * stackoverflow sucks
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class HowToMakeZhihuBetter {

    private static final int HUMAN_PATIENCE = 10;
    private List<Member> members = new ArrayList<>();
    private int atmosphere = -10;
    private Random r = new Random();
    public HowToMakeZhihuBetter(int size) {
        for (int i = 0; i < size; i++) { members.add(new Member()); }
    }
    public Member pick() { return members.get(r.nextInt(members.size())); }

    public class Member {
        private int patience = HUMAN_PATIENCE;
        private Question question = null;
        public Member() { patience = r.nextInt(patience+1) + atmosphere; }
        public void vote(Question q) {
            if (patience >= 0) {
                voteUp(q);
            } else {
                voteDown(q);
            }
        }
        public void ask() {
            question = new Question();
            for (Member member : members) {
                member.vote(question);
            }
        }
        private void voteUp(Question q) { ++q.vote; }
        private void voteDown(Question q) { --q.vote; }
        public String toString() {
            return (question.vote >= 0)? "Happy!" : "Suck!";
        }
    }

    public class Question { private int vote; }

    public static void main(String[] args) {
        HowToMakeZhihuBetter zhihu = new HowToMakeZhihuBetter(100);
        Member someone = zhihu.pick();
        someone.ask();
        System.out.println(someone);
    }
}
