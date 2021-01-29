package com.milchstrabe.autot;

public class CustomizedFunctionResult extends Result{


    private long nextTimestamp;

    public long getNextTimestamp() {
        return nextTimestamp;
    }

    public void setNextTimestamp(long nextTimestamp) {
        this.nextTimestamp = nextTimestamp;
    }

    public CustomizedFunctionResult(boolean isRun, long nextTimestamp) {
        super(isRun);
        this.nextTimestamp = nextTimestamp;
    }
}
