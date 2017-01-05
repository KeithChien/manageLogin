package com.example.user.managelogin.Data;

/**
 * Created by USER on 2017/1/3.
 */

public class TopScore {
    String node;
    String endDateInterval;
    String startDateInterval;

    public TopScore(){

    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getStartDateInterval() {
        return startDateInterval;
    }

    public void setStartDateInterval(String startDateInterval) {
        this.startDateInterval = startDateInterval;
    }

    public String getEndDateInterval() {
        return endDateInterval;
    }

    public void setEndDateInterval(String endDateInterval) {
        this.endDateInterval = endDateInterval;
    }
}
