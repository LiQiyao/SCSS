package org.obsidian.scss.service;

import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Lee on 2017/7/8.
 */
@Component
public class InitBean implements Serializable {

    private Long startTime;

    private Long endTime;

    private String CompanyName;

    public InitBean(Long startTime, Long endTime, String companyName) {
        this.startTime = startTime;
        this.endTime = endTime;
        CompanyName = companyName;
    }

    public InitBean() {
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", CompanyName='" + CompanyName + '\'' +
                '}';
    }
}
