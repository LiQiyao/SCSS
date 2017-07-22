package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Lee on 2017/7/8.
 */
public class RobotChat {

    private String answer;

    private List<String> questionPush;

    private long time;

    @Override
    public String toString() {
        return "RobotChat{" +
                "answer='" + answer + '\'' +
                ", questionPush=" + questionPush +
                ", time=" + time +
                '}';
    }

    public List<String> getQuestionPush() {
        return questionPush;
    }

    public void setQuestionPush(List<String> questionPush) {
        this.questionPush = questionPush;
    }

    public RobotChat(String answer, List<String> questionPush, long time) {
        this.answer = answer;
        this.questionPush = questionPush;
        this.time = time;
    }

    public RobotChat() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
