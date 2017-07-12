package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Lee on 2017/7/8.
 */
public class RobotAns {

    private String ans;

    private List<String> pushQuestion;

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public List<String> getPushQuestion() {
        return pushQuestion;
    }

    public void setPushQuestion(List<String> pushQuestion) {
        this.pushQuestion = pushQuestion;
    }

    public RobotAns(String ans, List<String> pushQuestion) {
        this.ans = ans;
        this.pushQuestion = pushQuestion;
    }

    public RobotAns() {
    }
}
