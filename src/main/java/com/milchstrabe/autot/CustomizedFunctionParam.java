package com.milchstrabe.autot;

public class CustomizedFunctionParam{
        private int targetTimes;
        private long timestamp;

    public CustomizedFunctionParam(int targetTimes, long timestamp) {
        this.targetTimes = targetTimes;
        this.timestamp = timestamp;
    }

    public int getTargetTimes() {
        return targetTimes;
    }

    public void setTargetTimes(int targetTimes) {
        this.targetTimes = targetTimes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}