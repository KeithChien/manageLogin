package com.example.user.managelogin.Data;



public class GetData {

    private String username;
    private String score;
    private long endDateInterval;
    private long startDateInterval;
    private String scores;

    public GetData()
    {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getEndDateInterval() {
        return endDateInterval;
    }

    public void setEndDateInterval(long endDateInterval) {
        this.endDateInterval = endDateInterval;
    }

    public long getStartDateInterval() {
        return startDateInterval;
    }

    public void setStartDateInterval(long startDateInterval) {
        this.startDateInterval = startDateInterval;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }



}
