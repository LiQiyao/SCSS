package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Lee on 2017/7/8.
 */
public class RobotChat {

    private String answer;

    private List<String> questionPsuh;

    private long time;

    public RobotChat(String answer, List<String> questionPsuh, long time) {
        this.answer = answer;
        this.questionPsuh = questionPsuh;
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

    public List<String> getQuestionPsuh() {
        return questionPsuh;
    }

    public void setQuestionPsuh(List<String> questionPsuh) {
        this.questionPsuh = questionPsuh;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
