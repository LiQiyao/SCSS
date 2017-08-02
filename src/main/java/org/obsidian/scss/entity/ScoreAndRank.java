package org.obsidian.scss.entity;

/**
 * Created by hp on 2017/8/1.
 */
public class ScoreAndRank {
    public int serviceId;
    public double grades;
    public int gradesrank;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public int getGradesrank() {
        return gradesrank;
    }

    public void setGradesrank(int gradesrank) {
        this.gradesrank = gradesrank;
    }
}
